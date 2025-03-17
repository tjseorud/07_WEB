package com.kh.mfw.board.model.service;

import com.kh.mfw.board.model.dao.BoardDAO;
import com.kh.mfw.board.model.dto.BoardDTO;
import static com.kh.mfw.common.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

public class BoardService {
	private BoardDAO boardDAO = new BoardDAO();

	public void insertBoard(BoardDTO board) {
		SqlSession sqlSession = getSqlSession();		
		//유효성 검증 해야하는데..
		boardDAO.insertBoard(sqlSession, board);
		sqlSession.commit();
		sqlSession.close();		
	}

	public Map<String, Object> selectBoards(int page) {
		SqlSession sqlSession = getSqlSession();
		//Table에서 조회해온 게시글 총 개수
		int boardCount = boardDAO.selectBoardCount(sqlSession);
		//page == 앞단에서 넘어온 요청 페이지
		
		//한 페이지에 보여줄 게시글 개수 :10개
		/*
		 * boardCount / 한 페이지 개수 				/ 마지막 페이지
		 * 100		  / 10		   / 10.0		/ 10
		 * 103		  / 10		   / 10.3		/ 11
		 * 112		  / 10		   / 11.2		/ 12
		 * =>총 게시글 개수/한 페이지 개수를 올림처리할경우 마지막 페이지를 구할 수 있음
		 */
		int boardLimit = 10;
		int maxPage = (int)(Math.ceil(boardCount / (double)boardLimit));
		
		//startBtn :페이지 하단에 보여질 버튼중 시작 값
		/*page 		한 페이지에 몇개의 페이지 버튼을 보일 것인지
		 * 한 페이지에 보여질 개수 : 5개
		 * start : 1, 6, 11, 16... =>n * 5 + 1
		 * 한 페이지에 보여질 개수 : 3개
		 * start : 1, 4, 7, 10... =>n * 3 + 1
		 * startBtn = n * 한 페이지 개수 +1;
		 * page / start
		 *    1 / 1
		 *    5 / 1
		 *    6 / 6
		 *    8 / 6
		 *   10 / 10
		 *   14 / 11
		 * =>  1~5 		: n * 5 + 1 ==> == 0
		 * =>  6~10 	: n * 5 + 1 ==> == 1
		 * => 11~15 	: n * 5 + 1 ==> == 2
		 * 
		 *   1~5  -1(0~4) 	/ 5 == 0
		 *   6~10 -1(5~9) 	/ 5 == 1
		 *  11~15 -1(10~14) / 5 == 2
		 */		
		int btnLimit = 5;
		int startBtn = (page - 1) / boardLimit * btnLimit + 1;
		//endBtn
		/*
		 * startBtn : 1 => 5
		 * endBtn = startBtn + btnLimit -1;
		 */
		int endBtn = startBtn + btnLimit - 1;
		if(endBtn > maxPage) endBtn = maxPage;
		
		/*
		 * MyBatis RowBoards 사용하기
		 * offset과 limit을 생성자 매개변수로 전달해주어야함
		 */
		RowBounds rowBounds = new RowBounds((page - 1) * boardLimit ,boardLimit);		
		// 페이징 처리 이후
		List<BoardDTO> boards = boardDAO.selectBoards(sqlSession, rowBounds);
		sqlSession.close();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boards", boards);
		map.put("boardCount", boardCount);
		map.put("page", page);
		map.put("boardLimit", boardLimit);
		map.put("btnLimit", btnLimit);
		map.put("maxPage", maxPage);
		map.put("startBtn", startBtn);
		map.put("endBtn", endBtn);
		return map;
	}

	public BoardDTO findByBoardNo(int boardNo) {
		SqlSession sqlSession = getSqlSession();
		//조회수 증가 로직		=>UPDATE / COMMIT
		//게시글 정보조회 로직	=>SELECT
		int updateResult = boardDAO.increaseCount(sqlSession, boardNo);
		if(updateResult == 0) {
			sqlSession.close();
			return null;
		}		
		BoardDTO board = boardDAO.findByBoardNo(sqlSession, boardNo);
		if(board != null) {
			sqlSession.commit();
		}
		sqlSession.close();
		return board;
	}

}
