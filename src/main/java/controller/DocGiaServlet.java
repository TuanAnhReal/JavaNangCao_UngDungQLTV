/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DocGia;
import model.DocGiaDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DocGiaServlet", urlPatterns = {"/docgia"})
public class DocGiaServlet extends HttpServlet {

    DocGiaDAO dgDAO;

    @Override
    public void init() throws ServletException {
        dgDAO = new DocGiaDAO();
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
                var lstDG = dgDAO.getAll();
                request.setAttribute("lstDG", lstDG);
                request.getRequestDispatcher("/admin/list-docgia.jsp").forward(request, response);
                break;
            case "search":
                String hoten = request.getParameter("hoten");
                var searchDG = dgDAO.searchDocGia(hoten);

                request.setAttribute("lstDG", searchDG);
                request.getRequestDispatcher("/admin/list-docgia.jsp").forward(request, response);
                break;
            case "insert":
                xuLyThem(request, response);
                break;
            case "showEdit":
                int maDocGia = Integer.parseInt(request.getParameter("maDocGia"));
                DocGia dg = dgDAO.searchDocGiaforID(maDocGia);
                request.setAttribute("lstDG", dgDAO.getAll());
                request.setAttribute("editDocGia", dg);
                request.getRequestDispatcher("/admin/list-docgia.jsp").forward(request, response);
                break;
            case "update":
                xuLyCapNhat(request, response);
                break;
            case "alertDelete":
                int maDG = Integer.parseInt(request.getParameter("maDocGia"));
                DocGia dgDel = dgDAO.searchDocGiaforID(maDG);   // lấy ra độc giả để hiện tên trong modal
                request.setAttribute("lstDG", dgDAO.getAll());
                request.setAttribute("delDocGia", dgDel);
                request.getRequestDispatcher("/admin/list-docgia.jsp").forward(request, response);
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
            String hoTen = request.getParameter("HoTen");
            String ngaysinhstr = request.getParameter("NgaySinh");
            String diachi = request.getParameter("DiaChi");
            String sodienthoai = request.getParameter("SoDienThoai");
            String email = request.getParameter("Email");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ngaysinh = new Date(sdf.parse(ngaysinhstr).getTime());

            DocGia dg = new DocGia(hoTen, ngaysinh, diachi, sodienthoai, email);
            dgDAO.insertDocGia(dg);
            //thông báo thành công
            request.setAttribute("success", "Thêm độc giả thành công!");

            // chuyển tiếp về action=list
            request.getRequestDispatcher("/docgia?action=list").forward(request, response);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.toString());
        }
    }

    private void xuLyCapNhat(HttpServletRequest request, HttpServletResponse response) {
        try {
            int maDocGia = Integer.parseInt(request.getParameter("MaDocGia"));
            String hoTen = request.getParameter("HoTen");
            String ngaySinhStr = request.getParameter("NgaySinh");
            String diaChi = request.getParameter("DiaChi");
            String soDienThoai = request.getParameter("SoDienThoai");
            String email = request.getParameter("Email");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ngaySinh = new Date(sdf.parse(ngaySinhStr).getTime());

            DocGia dg = new DocGia(maDocGia, hoTen, ngaySinh, diaChi, soDienThoai, email);
            dgDAO.updateDocGia(dg);

            request.setAttribute("success", "Cập nhật thành công!");

            request.getRequestDispatcher("/docgia?action=list").forward(request, response);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.toString());
        }
    }

    private void xuLyXoa(HttpServletRequest request, HttpServletResponse response) {
        try {
            int maDG_xoa = Integer.parseInt(request.getParameter("maDocGia"));
            dgDAO.deleteDocGia(maDG_xoa);
            
            //thông báo xoá thành công
            request.setAttribute("success", "Xoá độc giả thành công!");

            //gọi về form độc giả
            request.getRequestDispatcher("/docgia?action=list").forward(request, response);
        } catch (Exception e) {
            System.out.println("Lỗi: " + toString());
        }
    }

}
