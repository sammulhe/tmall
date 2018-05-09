package servlet.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import pojo.Product;

public class deleteProduct extends HttpServlet{

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		
	    ProductDao productDao = new ProductDao();
	    Product product = productDao.getOne(id);
	    int cid = product.getCid();
	    productDao.delete(id);
	    
	    response.sendRedirect("admin_product_list?cid="+cid+"");
	}
}
