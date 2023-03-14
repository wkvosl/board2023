package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DB.DBconnection;

//autocloseable ( try with resource statements )


public class BoardDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	private String sql="";
	
	
	


	
	//게시판 생성.
	public int createBoard(BoardDTO dto) {
		
		conn = DBconnection.getConnection();
		
		sql ="insert into board_tbl "
				+ " (username, title, boardtype, boardcategory, usertype, content, "
				+ " writedate, realfilename, systemfilename) "
				+ " values (?, ?, ? ,? , ?, ?, now(), ?, ?) ";
		
		try{
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getUsername());
				pstmt.setString(2, dto.getTitle());
				pstmt.setString(3, dto.getBoardtype());
				pstmt.setString(4, dto.getBoardcategory());
				pstmt.setString(5, dto.getUsertype());
				pstmt.setString(6, dto.getContent());
				pstmt.setString(7, dto.getRealfilename());
				pstmt.setString(8, dto.getSystemfilename());
			pstmt.executeUpdate();
			
			return 1;
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {	pstmt.close();	} catch (SQLException e) {}
			if(conn  != null) try {	conn.close();	} catch (SQLException e) {}
		}
		return 0;
	}
	
	//게시판 상세보기
	public List<BoardDTO> detailBoard(int bid) {
		
		BoardDTO dto = null;
		List<BoardDTO> detail = new ArrayList<>();

		conn = DBconnection.getConnection();
		
		sql ="select * from board_tbl where bid = ?";
		
		try {
		pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setBoardtype(rs.getString("boardtype"));
				dto.setUsername(rs.getString("username"));
				dto.setBoardcategory(rs.getString("boardcategory"));
				dto.setUsertype(rs.getString("usertype"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRealfilename(rs.getString("realfilename"));
				dto.setSystemfilename(rs.getString("systemfilename"));
				detail.add(dto);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {	pstmt.close();	} catch (SQLException e) {}
			if(conn  != null) try {	conn.close();	} catch (SQLException e) {}
		}
		return detail;
	}
	
	//게시판 수정, 파일도 새로 업로드 될때
	public int modiBoard(int bid, String boardtype, String boardcategory, String usertypeArr, 
			String title, String content, String realfilename, String systemfilename, String realpath, String systemfilename_copy) {

		
		conn = DBconnection.getConnection();
		
		//새로 업로드 되기 전의 업로드 파일명과 경로를 가지고 삭제함.
		String path = realpath;
		File file = new File(path+systemfilename_copy);
		if(file.exists()) file.delete();
		
		sql ="update board_tbl set boardtype = ?, boardcategory = ?, usertype = ?, title = ?, content = ?, realfilename = ?, systemfilename = ? where bid = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardtype);
			pstmt.setString(2, boardcategory);
			pstmt.setString(3, usertypeArr);
			pstmt.setString(4, title);
			pstmt.setString(5, content);
			pstmt.setString(6, realfilename);
			pstmt.setString(7, systemfilename);
			pstmt.setInt(8, bid);
			pstmt.executeUpdate();
			
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {	pstmt.close();	} catch (SQLException e) {}
			if(conn  != null) try {	conn.close();	} catch (SQLException e) {}
		}
		return 0;
	}
	
	//게시판 수정, 파일은 그대로 일때,
	public int modiBoardNullFile(int bid, String boardtype, String boardcategory, String usertypeArr, 
			String title, String content) {
		
		conn = DBconnection.getConnection();
		
		sql ="update board_tbl set boardtype = ?, boardcategory = ?, usertype = ?, title = ?, content = ? where bid = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardtype);
			pstmt.setString(2, boardcategory);
			pstmt.setString(3, usertypeArr);
			pstmt.setString(4, title);
			pstmt.setString(5, content);
			pstmt.setInt(6, bid);
			pstmt.executeUpdate();
			
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {	pstmt.close();	} catch (SQLException e) {}
			if(conn  != null) try {	conn.close();	} catch (SQLException e) {}
		}
		return 0;
	}
	
	//수정에서 파일명 옆에 있는 삭제버튼 누를때
	public boolean modiFileDel_but(int bid) {
		
		conn = DBconnection.getConnection();
		
		sql="update board_tbl set realfilename = null, systemfilename = null where bid = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {	pstmt.close();	} catch (SQLException e) {}
			if(conn  != null) try {	conn.close();	} catch (SQLException e) {}
		}
		return false;
	}
	
	public int boardDel(int bid) {
		
		conn = DBconnection.getConnection();
		
		sql = "delete from board_tbl where bid = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			pstmt.executeUpdate();
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {	pstmt.close();	} catch (SQLException e) {}
			if(conn  != null) try {	conn.close();	} catch (SQLException e) {}
		}
		return 0;
	}
	
	//조회 +1
	public void plusHit(int bid) {
		
		conn = DBconnection.getConnection();
		
		sql = "update board_tbl set hit = hit+1 where bid = ?";
		
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bid);
		pstmt.executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)    try { rs.close();		} catch (SQLException e) {}
			if(pstmt != null) try {	pstmt.close();	} catch (SQLException e) {}
			if(conn  != null) try {	conn.close();	} catch (SQLException e) {}
		}
	}
	
	//전체 칼럼의 수
		public int getTotalCount() {
			
			conn = DBconnection.getConnection();
			
			int total = 0;
			
			sql = "select count(bid) count from board_tbl";
			
			try {
				
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				total = rs.getInt("count");
			}
			
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) 	  try {rs.close();		} catch (SQLException e) {}
				if(stmt != null)  try {	stmt.close();	} catch (SQLException e) {}
				if(conn  != null) try {	conn.close();	} catch (SQLException e) {}
			}
			return total;
		}

}
