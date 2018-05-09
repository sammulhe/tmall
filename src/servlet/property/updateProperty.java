package servlet.property;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PropertyDao;
import pojo.Property;

public class updateProperty extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		String name = request.getParameter("name");
		
		Property property = new Property();
		property.setId(id);
		property.setName(name);
		property.setCid(cid);
		
		PropertyDao propertyDao = new PropertyDao();
		propertyDao.update(property);
		
		response.sendRedirect("admin_property_list?cid="+cid+"");
	}

}
