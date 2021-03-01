package com.hckj.privilege.web;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hckj.privilege.dao.BookDao;
import com.hckj.privilege.model.Book;
import com.hckj.privilege.model.Varity;
import com.hckj.privilege.service.api.BookService;
import com.hckj.privilege.service.api.VarityService;
import com.hckj.privilege.service.impl.BookServiceImpl;
import com.hckj.privilege.service.impl.VarityServiceImpl;
import com.hckj.privilege.utils.ServiceProxyFactory;

@WebServlet("/bookTabAction.action")
public class BookTabAction extends BasicServlet{
	BookService bookService = ServiceProxyFactory.getService(new BookServiceImpl());
	VarityService varityService = ServiceProxyFactory.getService(new VarityServiceImpl());
	public void tableUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		resp.sendRedirect(req.getContextPath()+"/bookAction.action?method=listUI");
	}
	public void varityUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/book/varity.jsp").forward(req, resp);
	}
	public void tableLUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		resp.sendRedirect(req.getContextPath()+"/bookAction.action?method=listLUI");
	}
	public void varityLUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/lend/varity.jsp").forward(req, resp);
	}
	public void jumpToVar(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		int vid = new Integer(req.getParameter("vid"));
		List<Book> books = bookService.queryBookByVar(vid);
		books = improveBookMess(books);
		req.setAttribute("books", books);
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/book/table.jsp").forward(req, resp);
	}
	//完善图书的信息
	public List<Book> improveBookMess(List<Book> books) throws Exception {
		for(Book book : books){
			String typeStr = bookService.queryVarityByVid(book.getType());
			String statusStr = bookService.queryStatusBySid(book.getStatus());
			book.setTypeStr(typeStr);
			book.setStatusStr(statusStr);
		}
		return books;		
	}
}
