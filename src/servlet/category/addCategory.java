package servlet.category;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import pojo.Category;

public class addCategory extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String name = request.getParameter("name");
		
		Category category = new Category();
		category.setName(name);
		
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.add(category);
		
		response.sendRedirect("admin_category_list");
	}

}
