package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Category;
import pojo.Product;
import pojo.Property;

public class ProductDao {
	private static final String Class_Name = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:D:\\download\\Job\\tmall.db";

    
    public ProductDao(){
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
    
    //分页查询
    public List<Product> list(int cid,int start, int count){
    	List<Product> products = new ArrayList<>(); //必须初始化，不然是空指针
    	String sql = "select * from product where cid = ? limit ?,?";   //获取	
   
    	try {
			Connection connection = this.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setSubTitle(rs.getString(3));
				product.setOriginalPrice(rs.getFloat(4));
				product.setPromotePrice(rs.getFloat(5));
				product.setStock(rs.getInt(6));
				product.setCreateDate(rs.getString(7));
				product.setCid(rs.getInt(8));
				
				products.add(product);
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return products;
    }

    //获取总数
    public int getTotal(int cid){
    	int total = 0;
    	String sql = "select count(*) from product where cid = ?";
    	try {
			Connection connection = this.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, cid);
			ResultSet rs = ps.executeQuery();
			total = rs.getInt(1);
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return total;
    }
    
    //获得一个product
    public Product getOne(int id){
    	Product product = new Product();
    	String sql = "select * from product p left join category c on p.cid=c.id where p.id=?";
    	try {
			Connection connection = this.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			product.setId(rs.getInt(1));
			product.setName(rs.getString(2));
			product.setSubTitle(rs.getString(3));
			product.setOriginalPrice(rs.getFloat(4));
			product.setPromotePrice(rs.getFloat(5));
			product.setStock(rs.getInt(6));
			product.setCreateDate(rs.getString(7));
			product.setCid(rs.getInt(8));
			Category category = new Category();
			category.setId(rs.getInt(9));
			category.setName(rs.getString(10));
			product.setCategory(category);
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return product;
    }
    
    //更新
    public void update(Product product){
    	String sql = "update product set name=?,subTitle=?,originalPrice=?,promotePrice=?,stock=?,cid=? where id = ?";
    	try {
			Connection connection = this.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setString(2, product.getSubTitle());
			ps.setFloat(3, product.getOriginalPrice());
			ps.setFloat(4, product.getPromotePrice());
			ps.setInt(5, product.getStock());
			ps.setInt(6, product.getCid());
			ps.setInt(7, product.getId());
			ps.executeUpdate();
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //删除
    public void delete(int id){
    	String sql = "delete from product where id = ?";
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
    
    //添加
    public void add(Product product){
    	String sql = "insert into product (name,subTitle,originalPrice,promotePrice,stock,cid) values (?,?,?,?,?,?)";
    	
    	try {
			Connection connection = this.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setString(2, product.getSubTitle());
			ps.setFloat(3, product.getOriginalPrice());
			ps.setFloat(4, product.getPromotePrice());
			ps.setInt(5, product.getStock());
			ps.setInt(6, product.getCid());
			ps.execute();
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
