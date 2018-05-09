package servlet.category;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import pojo.Category;

public class updateCategory extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String name = request.getParameter("name");
		System.out.println(name);
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.update(category);
		
		response.sendRedirect("admin_category_list");
	}

}
