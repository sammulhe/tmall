package servlet.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import pojo.Category;

public class deleteCategory extends HttpServlet{
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//必须用Integer.parseInt去获取正数，不然返回的是空指针
		int id = Integer.parseInt(request.getParameter("id"));

		CategoryDao categoryDao = new CategoryDao();		
        categoryDao.delete(id);
		
		response.sendRedirect("admin_category_list");
	}

}
