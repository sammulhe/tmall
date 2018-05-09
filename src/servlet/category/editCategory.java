package servlet.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import pojo.Category;
//点击编辑，通过获取一个Category，调到Category的编辑页面
public class editCategory extends HttpServlet{
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//必须用Integer.parseInt去获取正数，不然返回的是空指针
		int id = Integer.parseInt(request.getParameter("id"));

		CategoryDao categoryDao = new CategoryDao();		
		Category category = categoryDao.getOne(id);
		
	    request.setAttribute("category", category);
		request.getRequestDispatcher("admin/editCategory.jsp").forward(request, response);
	}
}
