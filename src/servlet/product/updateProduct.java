package servlet.product;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import pojo.Product;

public class updateProduct extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Product product = new Product();		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String subTitle = request.getParameter("subTitle");
		float originalPrice = Float.parseFloat(request.getParameter("originalPrice"));
		float promotePrice = Float.parseFloat(request.getParameter("promotePrice"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		
		product.setId(id);
		product.setName(name);
		product.setSubTitle(subTitle);
		product.setOriginalPrice(originalPrice);
		product.setPromotePrice(promotePrice);
		product.setStock(stock);
		product.setCid(cid);
		
		ProductDao productDao = new ProductDao();
		productDao.update(product);
		
		response.sendRedirect("admin_product_list?cid="+cid+"");
		
	}

}
