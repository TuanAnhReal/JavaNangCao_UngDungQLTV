/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author PC
 */
public class SachDAO {

    //khởi tạo ds
    public List<Sach> getAll() {
        List<Sach> list = new ArrayList<>();
        String sql = "SELECT MaSach, TenSach, TacGia, NamXuatBan, TheLoai, SoLuong FROM Sach";

        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Sach s = new Sach(
                        rs.getInt("MaSach"),
                        rs.getString("TenSach"),
                        rs.getString("TacGia"),
                        rs.getInt("NamXuatBan"),
                        rs.getString("TheLoai"),
                        rs.getInt("SoLuong")
                );
                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.toString());
        }

        return list;
    }
    
    //thêm sách
    public boolean insertSach(Sach s) {
        String sql = "INSERT INTO Sach (TenSach, TacGia, NamXuatBan, TheLoai, SoLuong) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, s.getTenSach());
            ps.setString(2, s.getTacGia());
            ps.setInt(3, s.getNamxuatban());
            ps.setString(4, s.getTheLoai());
            ps.setInt(5, s.getSoLuong());
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        s.setMaSach(generatedKeys.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm sách:");
            e.printStackTrace();
        }

        return false;
    }

    //sửa sách
    public boolean updateSach(Sach s) {
        String sql = "UPDATE Sach SET TenSach = ?, TacGia = ?, NamXuatBan = ?, TheLoai = ?, SoLuong = ? WHERE MaSach = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getTenSach());
            ps.setString(2, s.getTacGia());
            ps.setInt(3, s.getNamxuatban());
            ps.setString(4, s.getTheLoai());
            ps.setInt(5, s.getSoLuong());

            ps.setInt(6, s.getMaSach());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.toString());
            return false;
        }
    }

    public boolean deleteSach(int maSach) {
        String sql = "DELETE FROM Sach WHERE MaSach = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maSach);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.toString());
            return false;
        }
    }

    public List<Sach> searchSach(String keyword) {
        List<Sach> list = new ArrayList<>();
        String sql = "SELECT * FROM Sach WHERE TenSach LIKE ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sach s = new Sach(
                            rs.getInt("MaSach"),
                            rs.getString("TenSach"),
                            rs.getString("TacGia"),
                            rs.getInt("NamXuatBan"),
                            rs.getString("TheLoai"),
                            rs.getInt("SoLuong")
                    );
                    list.add(s);
                }
            }

        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.toString());
        }

        return list;
    }

    //Tìm sách theo Id
    public Sach getSachById(int id) {
        String sql = "SELECT * FROM Sach WHERE MaSach = ?";
        Sach s = null;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s = new Sach(
                            rs.getInt("masach"),
                            rs.getString("tensach"),
                            rs.getString("tacgia"),
                            rs.getInt("namxuatban"),
                            rs.getString("theloai"),
                            rs.getInt("soluong")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.toString());
        }

        return s;
    }
        // tổng số sách
        public int getTotalSach() {
        String sql = "SELECT COUNT(*) AS total FROM Sach";
        int total = 0;

        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi" + e.toString());
        }
        return total;
    }
}