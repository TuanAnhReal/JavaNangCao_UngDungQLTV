/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class DocGiaDAO {

    // Lấy toàn bộ danh sách độc giả
    public List<DocGia> getAll() {
        List<DocGia> list = new ArrayList<>();
        String sql = "SELECT * FROM DocGia";

        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                DocGia dg = new DocGia(
                        rs.getInt("maDocGia"),
                        rs.getString("HoTen"),
                        rs.getDate("NgaySinh"),
                        rs.getString("DiaChi"),
                        rs.getString("SoDienThoai"),
                        rs.getString("Email")
                );
                list.add(dg);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách độc giả:");
            e.printStackTrace();
        }

        return list;
    }

    // Thêm độc giả mới
    public boolean insertDocGia(DocGia dg) {
        String sql = "INSERT INTO DocGia(HoTen, NgaySinh, DiaChi, SoDienThoai, Email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dg.getHoTen());
            ps.setDate(2, new java.sql.Date(dg.getNgaySinh().getTime()));
            ps.setString(3, dg.getDiaChi());
            ps.setString(4, dg.getSoDienThoai());
            ps.setString(5, dg.getEmail());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        dg.setMaDocGia(generatedKeys.getInt(1)); // Gán mã được sinh tự động
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm độc giả:");
            e.printStackTrace();
        }

        return false;
    }

    // Cập nhật thông tin độc giả
    public boolean updateDocGia(DocGia dg) {
        String sql = "UPDATE DocGia SET HoTen = ?, NgaySinh = ?, DiaChi = ?, SoDienThoai = ?, Email = ? WHERE maDocGia = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dg.getHoTen());
            ps.setDate(2, new java.sql.Date(dg.getNgaySinh().getTime()));
            ps.setString(3, dg.getDiaChi());
            ps.setString(4, dg.getSoDienThoai());
            ps.setString(5, dg.getEmail());
            ps.setInt(6, dg.getMaDocGia());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật độc giả:");
            e.printStackTrace();
            return false;
        }
    }

    // Xóa độc giả theo mã
    public boolean deleteDocGia(int maDocGia) {
        String sql = "DELETE FROM DocGia WHERE maDocGia = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDocGia);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa độc giả:");
            e.printStackTrace();
            return false;
        }
    }

    // Tìm độc giả theo tên
    public List<DocGia> searchDocGia(String keyword) {
        List<DocGia> list = new ArrayList<>();
        String sql = "SELECT * FROM DocGia WHERE HoTen LIKE ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DocGia dg = new DocGia(
                            rs.getInt("maDocGia"),
                            rs.getString("HoTen"),
                            rs.getDate("NgaySinh"),
                            rs.getString("DiaChi"),
                            rs.getString("SoDienThoai"),
                            rs.getString("Email")
                    );
                    list.add(dg);
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm độc giả:");
            e.printStackTrace();
        }

        return list;
    }

    //  tìm độc giả theo mã
    public DocGia searchDocGiaforID(int maDocGia) {
        String sql = "SELECT * FROM DocGia WHERE MaDocGia = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDocGia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new DocGia(
                            rs.getInt("maDocGia"),
                            rs.getString("HoTen"),
                            rs.getDate("NgaySinh"),
                            rs.getString("DiaChi"),
                            rs.getString("SoDienThoai"),
                            rs.getString("Email")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm độc giả theo ID:");
            e.printStackTrace();
        }
        return null;
    }
//    tổng số độc giả 
    public int getTotalDocGia() {
        String sql = "SELECT COUNT(*) AS total FROM DocGia";
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
