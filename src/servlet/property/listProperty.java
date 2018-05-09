package servlet.property;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import dao.PropertyDao;
import pojo.Category;
import pojo.Property;

//在listCategory中点击属性管理，提交到这个servlet
//然后在这个servlet查询出关于该分类的所有属性，以及该分类，返回到listProperty.jsp
public class listProperty extends HttpServlet{

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int cid = Integer.parseInt(request.getParameter("cid"));		
		PropertyDao propertyDao = new PropertyDao();
		CategoryDao categoryDao = new CategoryDao(); //这些属性是数据哪个Category的
		int start = 0;
		int count = 5;
		int last = propertyDao.getTotal(cid);
		
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
		
		List<Property> properties = propertyDao.list(cid, start, count);
		Category category = categoryDao.getOne(cid);
		
		request.setAttribute("properties", properties);
		request.setAttribute("category", category); //为了显示//这些属性是数据哪个Category的
		request.setAttribute("start", start);
		request.setAttribute("last", last);
		request.setAttribute("count", count);
		
		request.getRequestDispatcher("admin/listProperty.jsp").forward(request, response);;
	}
	
}
