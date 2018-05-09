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
	    	
	    	connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	return total;
    }
    
    
    //根据id获得一个Category
    public Category getOne(int id){
    	Category category = new Category();
    	String sql = "select * from category where id = ?";
    	
    	try {
			Connection connection = this.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			category.setId(rs.getInt(1));
			category.setName(rs.getString(2));
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return category;
    }
    
    
    //更新一个Category
    public void update(Category category){
    	String sql = "update category set name=? where id=? ";
    	
    	try {
			Connection connection = this.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, category.getName());
			ps.setInt(2, category.getId());
            ps.executeUpdate();
            
            connection.close();
            
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    }
    
    //删除一个Category
    public void delete(int id){
    	String sql = " delete from category where id= ?";
    	try {
			Connection connection = this.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //增加一个Category
    public void add(Category category){
    	String sql = "insert into category (name) values (?)";
    	
    	try {
			Connection connection = this.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, category.getName());			
			ps.execute();
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
}
