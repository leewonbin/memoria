package product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import common.JDBCConnect;

public class ProductDAO extends JDBCConnect{
	PreparedStatement psmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public ProductDAO() {
		con = getConnection();
	}
	public ProductDTO getproduct(String id) {
		ProductDTO dto = new ProductDTO();
		String sql = "select * from product where productid=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setP_id(rs.getString(1));
				dto.setP_kname(rs.getString(2));
				dto.setP_ename(rs.getString(3));
				dto.setP_price(rs.getInt(4));
				dto.setP_stock(rs.getInt(5));
				dto.setP_brand(rs.getString(6));
				dto.setScentid(rs.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	public ArrayList<ProductDTO> getProductList() {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql = "select * from product";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setP_id(rs.getString(1));
				dto.setP_kname(rs.getString(2));
				dto.setP_ename(rs.getString(3));
				dto.setP_price(rs.getInt(4));
				dto.setP_stock(rs.getInt(5));
				dto.setP_brand(rs.getString(6));
				dto.setScentid(rs.getString(7));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int productCount(Map<String, Object> map) {

    	int totalcount = 0;
    	
    	String query = "select count(*) from member";
    	
    	try {
    		stmt = con.createStatement();
    		rs = stmt.executeQuery(query);
    		rs.next();
    		totalcount = rs.getInt(1);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return totalcount;
    	
    }
    
    
    
	public List<ProductDTO> selectProductInfo(Map<String, Object> map) {
    	
    	List<ProductDTO> bbs = new Vector<ProductDTO>();
    	
    	String query = "SELECT * FROM product";
    	if (map.get("searchWord") != null) {
    	    query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
    	}
    	query += " ORDER BY productid ASC";
    		  try {
    			  stmt = con.createStatement();
    			  rs = stmt.executeQuery(query);
    			  
    			  while (rs.next()) {
    				  ProductDTO dto = new ProductDTO();
    				  
    				  dto.setP_id(rs.getString(1));
    				  dto.setP_kname(rs.getString(2));
    				  dto.setP_ename(rs.getString(3));
    				  dto.setP_price(rs.getInt(4));
    				  dto.setP_stock(rs.getInt(5));
    				  dto.setP_brand(rs.getString(6));
    				  dto.setScentid(rs.getString(7));
    				  
    				 bbs.add(dto);
    			  }
    		  } catch(Exception e) {
    			  e.printStackTrace();
    		  }
    		  return bbs;
    }

	
	public boolean deleteProduct(String id) {
		getConnection();
		String sql = "DELETE FROM product WHERE productid = ?";

		try  {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			int rowsAffected = psmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public void close() { 
		try {            
			if (rs != null) rs.close(); 
			if (stmt != null) stmt.close();
			if (psmt != null) psmt.close();
			if (con != null) con.close(); 

			System.out.println("JDBC 자원 해제");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
