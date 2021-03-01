package com.hckj.privilege.web;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.hckj.privilege.dao.RecordDao;
import com.hckj.privilege.model.Book;
import com.hckj.privilege.model.Record;
import com.hckj.privilege.model.Status;
import com.hckj.privilege.model.Varity;
import com.hckj.privilege.service.api.BookService;
import com.hckj.privilege.service.api.VarityService;
import com.hckj.privilege.service.impl.BookServiceImpl;
import com.hckj.privilege.service.impl.VarityServiceImpl;
import com.hckj.privilege.utils.ServiceProxyFactory;

@WebServlet("/bookAction.action")
public class BookAction extends BasicServlet{
	//为图书管理绑定事件
	private BookService bookService = ServiceProxyFactory.getService(new BookServiceImpl());
	//为图书管理的分类绑定事件
	private VarityService varityService = ServiceProxyFactory.getService(new VarityServiceImpl());
	//为图书列表加载数据库数据，并定向到图书列表界面
	public void listUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		List<Book> result = bookService.queryAll();
		//通过封装的方法实现图书信息的完善
		result = improveBookMess(result);
		req.setAttribute("books", result);
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/book/table.jsp").forward(req, resp);
	}
	//为图书列表加载数据库数据，并定向到图书列表界面
	public void listLUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		List<Book> result = bookService.queryAll();
		//通过封装的方法实现图书信息的完善
		result = improveBookMess(result);
		req.setAttribute("books", result);
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/lend/table.jsp").forward(req, resp);
	}
	//跳转到图书添加界面
	public void addUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		List<Varity> varities = varityService.queryAllVarities();
		req.setAttribute("varities", varities);
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/book/add.jsp").forward(req, resp);
	}
	//启动图书添加服务，成功后重定向到图书列表
	public void add(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		Map<String, String[]> maps = req.getParameterMap();
		Book book = new Book();
		BeanUtils.populate(book, maps);
		bookService.add(book);
		resp.sendRedirect(req.getContextPath()+"/bookAction.action?method=listUI");
	}
	//启动删除图书服务，成功后重定向到图书列表
	public void del(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		int id = new Integer(req.getParameter("id"));
		bookService.delBookById(id);
		resp.sendRedirect(req.getContextPath()+"/bookAction.action?method=listUI");
	}
	//跳转到图书信息修改界面
	public void editUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		int id = new Integer(req.getParameter("id"));
		Book book = bookService.queryBookById(id);
		List<Varity> varities = varityService.queryAllVarities();
		List<Status> status = bookService.queryAllStatus();
		req.setAttribute("book", book);
		req.setAttribute("varities", varities);
		req.setAttribute("status", status);
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/book/modify.jsp").forward(req, resp);
	}
	//启动图书信息修改服务，成功后重定向到图书列表
	public void edit(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		Map<String, String[]> maps = req.getParameterMap();
		Book book = new Book();
		BeanUtils.populate(book, maps);
		bookService.modifyBookMess(book);
		resp.sendRedirect(req.getContextPath()+"/bookAction.action?method=listUI");
	}
	//启动图书搜索服务，成功后重定向到图书列表
	public void search(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		String mess = req.getParameter("mess");
		List<Book> books = bookService.queryBookByMess(mess);
		//完善图书的信息
		books = improveBookMess(books);
		req.setAttribute("books", books);
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/book/table.jsp").forward(req, resp);
	}
	//启动图书搜索服务，成功后重定向到图书列表
	public void lsearch(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		String mess = req.getParameter("mess");
		List<Book> books = bookService.queryBookByMess(mess);
		//完善图书的信息
		books = improveBookMess(books);
		req.setAttribute("books", books);
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/lend/table.jsp").forward(req, resp);
	}
	//定向到图书分类界面
	public void varityUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/book/varTab.jsp").forward(req, resp);
	}
	//加载图书分类信息，并转换为json数据，实现异步传输
	public void varityShow(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		List<Varity> list = varityService.queryAllVarity();
		//设置响应格式:响应json编码utf-8
		resp.setContentType("application/json;charset=utf-8");
		//将集合数据转json数据
		Gson gson = new Gson();
		String json = gson.toJson(list);
		//响应json数据
		resp.getWriter().print(json);
	}
	//定向到分类管理界面
	public void varityManageUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{		
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/book/varityManage.jsp").forward(req, resp);
	}
	//定向到图书预借页面
	public void lendUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{	
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/lend/varTab.jsp").forward(req, resp);
	}
	//修改图书借阅状态，并记录图书预借信息及预借者信息，再重定向到图书借阅的table界面。
	public void reserve(HttpServletRequest req,HttpServletResponse resp) throws Exception{	
		int bid = new Integer(req.getParameter("bid"));
		int uid = new Integer(req.getParameter("uid"));
		int period = new Integer(req.getParameter("period"));
		//将预借记录保存到数据库
		bookService.addRecord(bid,uid,period);
		//将图书状态设置为被预借
		bookService.changeStatusByBid(bid,1);
		resp.getWriter().print("true");
	}
	//adjustBStatus调整图书借阅时间
	public void adjustBStatus(HttpServletRequest req,HttpServletResponse resp) throws Exception{	
		int bid = new Integer(req.getParameter("bid"));
		int uid = new Integer(req.getParameter("uid"));
		int period = new Integer(req.getParameter("period"));
		//将预借记录的借阅时间修改为新时间
		bookService.adjustRecordPeriod(bid,uid,period);
		resp.getWriter().print("true");
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

	//根据登录用户的id查询借书记录，并重定向到借书记录的列表页面
	public void recordUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		int id = new Integer(req.getParameter("id"));
		List<Record> records = bookService.queryRecordByUid(id);
		req.setAttribute("records", records);
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/record/table.jsp").forward(req, resp);
	}
	
	//根据登录用户的id查询预借的图书，并重定向到图书预借的列表
	public void reserveUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		int id = new Integer(req.getParameter("id"));
		List<Record> records = bookService.queryBookByUidAndStatus(id);
		req.setAttribute("records", records);
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/reserve/table.jsp").forward(req, resp);
	}
	
	public void cancelReserve(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		int bid = new Integer(req.getParameter("bid"));
		int rid = new Integer(req.getParameter("rid"));
		int uid = new Integer(req.getParameter("uid"));
		//取消预借，修改book表的status为0可借阅预借
		bookService.changeStatusByBid(bid,0);
		//修record改表的status为 7.预借取消
		bookService.changeRecordStatusByRid(rid,7);
		//重定向到图书列表
		resp.sendRedirect(req.getContextPath()+"/bookAction.action?method=reserveUI&id="+uid);
	}
	//图书预借管理UI
	public void reserveManageUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		List<Record> records = bookService.queryReserve();
		req.setAttribute("records", records);
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/reserveManage/table.jsp").forward(req, resp);
	}
	//取书
	public void takeBook(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		int bid = new Integer(req.getParameter("bid"));
		int rid = new Integer(req.getParameter("rid"));
		//取消预借，修改book表的status为2.已被借阅
		bookService.changeStatusByBid(bid,2);
		//修record改表的status为 2.已被借阅
		bookService.changeRecordStatusByRid(rid,2);
		resp.getWriter().print("true");
	}
	//图书预借管理UI
	public void recordManageUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		List<Record> records = bookService.queryRecord();
		req.setAttribute("records", records);
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/recordManage/table.jsp").forward(req, resp);
	}
	//启动图书搜索服务，成功后重定向到图书列表
	public void rsearch(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		String mess = req.getParameter("mess");
		List<Record> records = bookService.queryReserveByMess(mess);
		req.setAttribute("records", records);
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/recordManage/table.jsp").forward(req, resp);
	}
	//启动还书操作
	public void returnBook(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		int rid = new Integer(req.getParameter("rid"));
		int bid = new Integer(req.getParameter("bid"));
		bookService.returnBook(rid,bid);
		//重定向到图书借阅记录列表
		resp.sendRedirect(req.getContextPath()+"/bookAction.action?method=recordManageUI");
	}
}
