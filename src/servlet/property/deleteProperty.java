package servlet.property;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PropertyDao;
import pojo.Property;

public class deleteProperty extends HttpServlet{
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		
		PropertyDao propertyDao = new PropertyDao();
		Property property = propertyDao.getOne(id); //获取cid，重定向到listProperty页面
		int cid = property.getCid();
		propertyDao.delete(id);
		
		response.sendRedirect("admin_property_list?cid="+cid+"");
	}

}
