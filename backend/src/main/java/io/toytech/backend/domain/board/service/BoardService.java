package io.toytech.backend.domain.board.service;

import io.toytech.backend.domain.board.constant.BoardType;
import io.toytech.backend.domain.board.domain.Board;
import io.toytech.backend.domain.board.domain.BoardFile;
import io.toytech.backend.domain.board.dto.BoardDto;
import io.toytech.backend.domain.board.repository.BoardRepository;
import io.toytech.backend.domain.member.constant.Status;
import io.toytech.backend.domain.member.domain.Address;
import io.toytech.backend.domain.member.domain.Member;
import io.toytech.backend.domain.member.repository.MemberRepository;
import io.toytech.backend.global.util.FileStore;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BoardService {

  private final BoardRepository boardRepository;
  private final MemberRepository memberRepository;
  private final FileStore fileStore;
  private final BoardFileService boardFileService;


  @Transactional
  public Board findOneReturnEntity(Long id) {
    return boardRepository.findById(id).get();
  }

  @Transactional
  public BoardDto findOneReturnDto(Long id) { //게시글 조회
    Board board = boardRepository.findById(id).get();
    board.updateView(); //조회수 1 증가

    return new BoardDto(board);
  }

  @Transactional
  public Long createBoard(BoardDto boardDto) throws IOException {   //게시글 생성
    Address address = Address.builder()
        .state("경기도")
        .city("평택시")
        .street("111")
        .zipCode("123-1")
        .build();
    Member member = Member.builder()
        .email("123@naver.com")
        .password("1234")
        .name("Kim")
        .dateBirth(LocalDateTime.now())
        .address(address)
        .status(Status.MEMBER)
        .build(); //가정의 유저 생성

    memberRepository.save(member);

    List<BoardFile> boardFiles = fileStore.storeFiles(boardDto.getMultipartFiles());
    Board board = boardDto.toEntity(member, boardFiles);  //보드 엔티티 생성

    boardRepository.save(board);

    for (BoardFile boardFile : boardFiles) { //boardFile에 board연관 관계 설정 및 저장
      boardFile.connetBoardId(board);
      boardFileService.createBoardFile(boardFile);
    }

    return board.getId();
  }

  @Transactional
  public void updateBoard(Long id, BoardDto boardDto)
      throws IOException {  //게시글 수정 (첨부 파일을 완전 지웠다 새걸로 갈아끼움)
    Board board = boardRepository.findById(id).get();  //보드 가져오기
    List<BoardFile> beforeUpdateBoardFiles = board.getBoardFiles();  //기존 첨부파일 불러오기 (삭제해야함)
    List<BoardFile> boardFiles = fileStore.storeFiles(boardDto.getMultipartFiles());  //수정한 첨부파일
    board.updateBoard(boardDto, boardFiles); //업데이트

    for (BoardFile boardFile : boardFiles) {  //수정한 첨부파일 db에 저장
      boardFile.connetBoardId(board);
    }

    boardFileService.deleteBoardFiles(beforeUpdateBoardFiles); //기존 첨부파일 삭제
  }

  /**
   * 게시글을 삭제한다. (더 생각해야 할 것: 예외처리 (게시글이 존재하지 않는다면, 로그인 유저가 게시글 생성자가 아니라면))
   *
   * @return
   */
  @Transactional
  public void deleteBoard(UserDetails userDetails,
      Long boardId) { //게시글 삭제 (게시글 작성자, 현재 로그인 유저가 같아야 함)

    boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + boardId));

    if (isPostCreatedByCurrentUser(userDetails, boardId)) {
      List<BoardFile> boardFiles = findOneReturnEntity(boardId).getBoardFiles();
      boardFileService.deleteBoardFiles(boardFiles); //디렉토리(로컬)에서 첨부파일 삭제
      boardRepository.deleteById(boardId);
    }
  }

  @Transactional
  public void updateLikeCount(Board board, int num) { //num이 1이면 좋아요 +1, -1이면 좋아요 -1
    board.updateLikeCount(num);
  }

  public Page<Board> getBoards(Pageable pageable) {
    return boardRepository.findAll(pageable);
  }

  public Page<Board> getBoardsByType(BoardType boardType, Pageable pageable) {
    return boardRepository.findByBoardType(boardType, pageable);
  }


  private boolean isPostCreatedByCurrentUser(UserDetails userDetails,
      Long boardId) {
    log.info("userDetails = {}", userDetails);
    String username = userDetails.getUsername();
    Member loginMember = memberRepository.findByName(username).get(0); //로그인 한 멤버
    Member createBoardMember = boardRepository.findById(boardId).get().getMember(); //게시글 작성한 멤버
    return (loginMember == createBoardMember);
  }

}