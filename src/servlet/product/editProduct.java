package servlet.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import pojo.Product;

public class editProduct extends HttpServlet{
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		
		ProductDao productDao = new ProductDao();
		Product product = productDao.getOne(id);
		
		request.setAttribute("product", product);
		
		request.getRequestDispatcher("admin/editProduct.jsp").forward(request, response);
	}

}
