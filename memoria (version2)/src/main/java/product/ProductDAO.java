package product;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import common.JDBCConnect;

public class ProductDAO extends JDBCConnect{

	public ProductDAO(ServletContext application) {
		super(application);
		// TODO Auto-generated constructor stub
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

}
