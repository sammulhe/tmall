package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.Category;

public class CategoryDao {
	private static final String Class_Name = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:D:\\download\\Job\\tmall.db";

    
    public CategoryDao(){
		try {
			Class.forName(Class_Name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL);
	
    }
    
    
    //分页获取Category
    public List<Category> list(int start, int count){
    	List<Category> categorys = new ArrayList<>(); //必须初始化，不然是空指针
    	String sql = "select * from category limit ?,?";     //获取	

		try {
			Connection connection = this.getConnection();
			PreparedStatement s = connection.prepareStatement(sql);
			s.setInt(1, start);
	    	s.setInt(2, count);  	
	    	ResultSet rs = s.executeQuery();
	    	
	    	while(rs.next()){
	    		Category c = new Category();
	    		c.setId(rs.getInt(1));
	    		c.setName(rs.getString(2));
	    		c.setImage(rs.getString(3));
	    		
	            categorys.add(c);
	    	}
	    	
	    	connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    	return categorys;
    }
    
    
    //获取总数
    public int getTotal(){
    	String sql = "select count(*) from category";
    	int total = 0;
    	
		try {
	    	Connection connection = this.getConnection();
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(sql);
	    	total = rs.getInt(1);
	    	System.out.println(total);
	    	connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	return total;
    }
}
