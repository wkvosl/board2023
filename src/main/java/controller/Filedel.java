package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;

@WebServlet("/fdel.do")
public class Filedel extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String realpath = request.getSession().getServletContext().getRealPath("/img/uploads/");
		int maxSize = 1024*1024*10;
		String enctype = "UTF-8";
		
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, realpath, maxSize, enctype, new DefaultFileRenamePolicy());
		
		String id = multi.getParameter("list");
		int bid = Integer.parseInt(id);
		String systemfilename = multi.getParameter("systemfilename");
		
		File file = new File(realpath+systemfilename);
		BoardDAO dao = new BoardDAO();
		
		if(dao.modiFileDel_but(bid)) {
			file.delete();
		}
		
		response.sendRedirect("modi.do?list="+bid);
		
		
	}
		

}
