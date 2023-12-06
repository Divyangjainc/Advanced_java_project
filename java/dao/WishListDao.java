package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import model.product;
import model.WishList;

public class WishListDao {
	public static void insertIntoWishList(WishList w) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "insert into wishlist(cusid,pid,pprice,pname,pimage,pdesc) values(?,?,?,?,?,?)";
			PreparedStatement pst  = conn.prepareStatement(sql);
			pst.setInt(1, w.getCus_id());
			pst.setInt(2, w.getPid());
			pst.setInt(3,w.getPprice());
			pst.setString(4, w.getPname());
			pst.setString(5, w.getPimage());
			pst.setString(6, w.getPdesc());
			pst.executeUpdate();
			System.out.println("product added to wishlist");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<WishList> getWishListByCusid(int id){
		List<WishList> list = new ArrayList<WishList>();
		try {
			Connection conn = DBConnection.createConnection();
			String sql ="select * from wishlist where cusid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				WishList w = new WishList();
				w.setWid(rs.getInt("wid"));
				w.setCus_id(rs.getInt("cusid"));
				w.setPid(rs.getInt("pid"));
				w.setPprice(rs.getInt("pprice"));
				w.setPname(rs.getString("pname"));
				w.setPimage(rs.getString("pimage"));
				w.setPdesc(rs.getString("pdesc"));
				list.add(w);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<WishList> getAllWishList(){
		List<WishList> list = new ArrayList<WishList>();
		try {
			Connection conn = DBConnection.createConnection();
			String sql ="select * from wishlist";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				WishList w = new WishList();
				w.setWid(rs.getInt("wid"));
				w.setCus_id(rs.getInt("cusid"));
				w.setPid(rs.getInt("pid"));
				w.setPprice(rs.getInt("pprice"));
				w.setPname(rs.getString("pname"));
				w.setPimage(rs.getString("pimage"));
				w.setPdesc(rs.getString("pdesc"));
				list.add(w);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void removeFromWishList(int id) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql ="delete from wishlist where wid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			System.out.println("removed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}