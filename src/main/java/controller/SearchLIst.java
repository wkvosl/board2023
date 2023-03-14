package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDTO;
import dao.SearchDAO;


@WebServlet("/list")
public class SearchLIst extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//페이징
			String basenum = request.getParameter("p");
			if(basenum == null) basenum = "1";
			int baseNum = Integer.parseInt(basenum);

			int viewRow = 10; 
			int min = (baseNum-1) * viewRow ;
				if(min < 0 ) min = 0;
			int max = viewRow;
		
			String searchTitle     = request.getParameter("t")  == null? "" : request.getParameter("t");
			String searchUserName  = request.getParameter("u")	== null? "" : request.getParameter("u");
			String searchFirstDate = request.getParameter("fd") == null? "" : request.getParameter("fd");
			String searchLastDate  = request.getParameter("ld") == null? "" : request.getParameter("ld");
			
			SearchDAO dao = new SearchDAO();
			List<BoardDTO> searchlist = 
					dao.getSearchList(searchTitle, searchUserName, searchFirstDate, searchLastDate, min, max);

			int count = dao.getSearchCount(searchTitle, searchUserName, searchFirstDate, searchLastDate);
			int totalPage = ((count-1)/viewRow)+1;

		request.setAttribute("boardlist", searchlist);
		request.setAttribute("viewRow", viewRow);
		request.setAttribute("baseNum", baseNum);
		request.setAttribute("TotalCount", count);
		request.setAttribute("TotalPage", totalPage);
		
		RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/boardSearchList.jsp");
		dis.forward(request, response);
		
	}
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.setCharacterEncoding("UTF-8"); //검색으로 들어온 input 키워드.. 한글깨짐현상 때문에
			doGet(request, response);
			
		}

	}