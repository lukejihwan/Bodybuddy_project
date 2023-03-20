package com.edu.bodybuddy.model.board;

import java.util.List;

public interface BoardCommentDAO {
	/**
	 * @param board_idx 게시판의 게시글 번호
	 * @return 게시글이 가지고 있는 모든 댓글 리스트 반환
	 */
	public List selectAllByBoard(int board_idx);
	/**
	 * @param member_idx 회원 고유 번호
	 * @return 해당 게시판에서 해당 회원이 작성한 모든 댓글 리스트 반환
	 */
	public List selectAllByMember(int member_idx);
	/**
	 * @param comment_idx 해당 게시판에서 댓글 하나의 고유 번호
	 * @return 해당 고유 번호에 해당하는 DTO를 반환
	 */
	public Object select(int comment_idx);
	/**
	 * 댓글 등록
	 * 기본댓글 달기위해 필요한 것 : board_idx=0 ,comment, member_idx, writer
	 * 대댓글 달기위해 필요한 것. 부모댓글의 DTO : comment_idx, board_idx, comment, member_idx, writer, post, step, depth
	 * (대댓글을 insert할 때 들어오는 파라미터중 comment_idx, board_idx, post, step, depth 는 부모댓글의 것을 받는다)
	 * @param object 댓글, 대댓글을 하나 등록하기 위해 필요한 DTO
	 */
	public void insert(Object object);
	/**
	 * 댓글 수정
	 * 필요한 것 : comment_idx, comment, writer
	 * @param object 댓글, 대댓글을 하나 수정하기 위해 필요한 DTO
	 */
	public void update(Object object);
	/**
	 * 댓글 삭제
	 * @param comment_idx 해당 대댓글의 고유 번호
	 */
	public void delete(int comment_idx);
	/**
	 * @param board_idx 게시판의 게시글 번호
	 * @return 해당 게시글이 가진 댓글들의 수를 정수로 반환
	 */
	public int totalCount(int board_idx);
	/**
	 * 게시글에서 대댓글을 insert 하려고 할 때 대댓글이 들어올 자리 위의 모든 step을 + 1하는 메서드
	 * @param object 필요한 것 : board_idx, step
	 */
	public void shiftAboveSteps(Object object);
	/**
	 * 게시글에서 대댓글을 delete 하려고 할 때 대댓글이 삭제된 자리 위의 모든 step을 - 1하는 메서드
	 * @param object 필요한 것 : board_idx, step
	 */
	public void unshiftAboveSteps(Object object);
	/**
	 * 주의) 해당댓글의 부모댓글이 부모댓글의depth에 존재하는 댓글들중 가장 step이 가장 마지막인 경우 에러 발생 
	 * @param object 필요한 것 : board_idx, step, depth
	 * @return 부모댓글의 자식으로 올 수 있는 가장 아래의 step을 정수형으로 반환
	 */
	public int maxStepInChild(Object object);
	/**
	 * @param object 필요한 것 : board_idx, step
	 * @return 해당 post에서 가장 마지막의 step을 정수형으로 반환
	 */
	public int maxStepInPost(Object object);
	/**
	 * @param object 필요한 것 : board_idx, post, depth
	 * @return 해당 post의 해당 depth에서 가장 마지막 step을 반환
	 */
	public int maxStepInDepth(Object object);
}
