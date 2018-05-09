package servlet.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import pojo.Category;

@SuppressWarnings("serial")
public class listCategoryServlet extends HttpServlet{
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		CategoryDao categoryDao = new CategoryDao();
		int start = 0;
		int count = 5;
		int last = categoryDao.getTotal();
		
		//检查是否传过来了start和count
		try{
		    start = Integer.parseInt(request.getParameter("start"));
		}catch(NumberFormatException e){
			start = 0;
		}
		
		//计算最后一页的start
		if(last % count == 0){
			last = last - count;
		}else{
			last = last - last%count;
		}
		
		List<Category> categorys = categoryDao.list(start, count);
		
		request.setAttribute("categorys", categorys);
		request.setAttribute("start", start);
		request.setAttribute("last", last);
		request.setAttribute("count", count);
		
		request.getRequestDispatcher("admin/listCategory.jsp").forward(request, response);;
	}

}
