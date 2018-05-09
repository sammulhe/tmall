package servlet.property;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PropertyDao;
import pojo.Property;

public class addProperty extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int cid = Integer.parseInt(request.getParameter("cid"));
		String name = request.getParameter("name");
		
		Property property = new Property();
		property.setCid(cid);
		property.setName(name);
		PropertyDao propertyDao = new PropertyDao();
		propertyDao.add(property);
		
		response.sendRedirect("admin_property_list?cid="+cid+"");
	}

}
