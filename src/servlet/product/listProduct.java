package servlet.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import dao.ProductDao;
import pojo.Category;
import pojo.Product;

@SuppressWarnings("serial")
public class listProduct extends HttpServlet{
	
public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int cid = Integer.parseInt(request.getParameter("cid"));		
		ProductDao productDao = new ProductDao();
		CategoryDao categoryDao = new CategoryDao(); //这些product是数据哪个Category的
		int start = 0;
		int count = 5;
		int last = productDao.getTotal(cid);
		
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
		
		List<Product> products = productDao.list(cid, start, count);
		Category category = categoryDao.getOne(cid);
		
		request.setAttribute("products", products);
		request.setAttribute("category", category); //为了显示//这些属性是数据哪个Category的
		request.setAttribute("start", start);
		request.setAttribute("last", last);
		request.setAttribute("count", count);
		
		request.getRequestDispatcher("admin/listProduct.jsp").forward(request, response);;
	}

}
