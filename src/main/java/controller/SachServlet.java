/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Sach;
import model.SachDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SachServlet", urlPatterns = {"/sach"})
public class SachServlet extends HttpServlet {

    SachDAO sDAO;

    @Override
    public void init() throws ServletException {
        sDAO = new SachDAO();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                var lstSach = sDAO.getAll();
                request.setAttribute("lstSach", lstSach);
                request.getRequestDispatcher("/admin/list-sach.jsp").forward(request, response);
                break;
            case "search":
                String tensach = request.getParameter("tensach");
                var searchS = sDAO.searchSach(tensach);
                request.setAttribute("lstSach", searchS);
                request.getRequestDispatcher("/admin/list-sach.jsp").forward(request, response);
                break;
            case "insert":
                xuLyThem(request, response);
                break;
            case "showEdit":
                int maSach = Integer.parseInt(request.getParameter("maSach"));
                var editS = sDAO.getSachById(maSach);
                request.setAttribute("lstSach", sDAO.getAll());
                request.setAttribute("editSach", editS);
                request.getRequestDispatcher("/admin/list-sach.jsp").forward(request, response);
                break;
            case "update":
                xuLyCapNhat(request, response);
                break;
            case "alertDel":
                int maSach_del = Integer.parseInt(request.getParameter("maSach"));
                Sach sach_del = sDAO.getSachById(maSach_del);
                request.setAttribute("lstSach", sDAO.getAll());
                request.setAttribute("delSach", sach_del);
                request.getRequestDispatcher("/admin/list-sach.jsp").forward(request, response);
                break;
            case "delete":
                xuLyXoa(request, response);
                break;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void xuLyThem(HttpServletRequest request, HttpServletResponse response) {
        try {
            // lấy dữ liệu từ web
            String tensach = request.getParameter("TenSach");
            String tacgia = request.getParameter("TacGia");
            int namxuatban = Integer.parseInt(request.getParameter("NamXuatBan"));
            String theloai = request.getParameter("TheLoai");
            int soluong = Integer.parseInt(request.getParameter("SoLuong"));

            // thêm sách vào sql
            Sach insertS = new Sach(tensach, tacgia, namxuatban, theloai, soluong);
            sDAO.insertSach(insertS);

            //chuyển hướng về list Sách và hiện thông báo
            request.setAttribute("success", "Thêm sách thành công.");
            request.getRequestDispatcher("/sach?action=list").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.toString());
        }
    }

    private void xuLyCapNhat(HttpServletRequest request, HttpServletResponse response) {
        try {
            int masach = Integer.parseInt(request.getParameter("MaSach"));
            String tensach = request.getParameter("TenSach");
            String tacgia = request.getParameter("TacGia");
            int namxuatban = Integer.parseInt(request.getParameter("NamXuatBan"));
            String theloai = request.getParameter("TheLoai");
            int soluong = Integer.parseInt(request.getParameter("SoLuong"));

            Sach updateS = new Sach(masach, tensach, tacgia, namxuatban, theloai, soluong);
            sDAO.updateSach(updateS);

            //thông báo và gọi lại List sách
            request.setAttribute("success", "Sửa sách thành công.");
            request.getRequestDispatcher("/sach?action=list").forward(request, response);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.toString());
        }
    }

    private void xuLyXoa(HttpServletRequest request, HttpServletResponse response) {
        try {
            int maSach = Integer.parseInt(request.getParameter("maSach"));
            sDAO.deleteSach(maSach);

            //thông báo và gọi list sách
            request.setAttribute("success", "Xóa sách thành công");
            request.getRequestDispatcher("/sach?action=list").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.toString());
        }
    }
}
