package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDAO;


@WebServlet("/delete.do")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("list");
		int bid = Integer.parseInt(id);
		
		String systemfilename = request.getParameter("systemfilename");
		String realpath = request.getSession().getServletContext().getRealPath("/img/uploads/");
		File file = new File(realpath+systemfilename);
		
		BoardDAO dao = new BoardDAO();

		if(dao.boardDel(bid) == 1) {
			file.delete();
		}
		
		response.sendRedirect("list");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
