package servlet.property;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PropertyDao;
import pojo.Property;

public class editProperty extends HttpServlet{

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//必须用Integer.parseInt去获取正数，不然返回的是空指针
		int id = Integer.parseInt(request.getParameter("id"));

		PropertyDao propertyDao = new PropertyDao();		
		Property property = propertyDao.getOne(id);
		
	    request.setAttribute("property", property);
		request.getRequestDispatcher("admin/editProperty.jsp").forward(request, response);
	}
}
