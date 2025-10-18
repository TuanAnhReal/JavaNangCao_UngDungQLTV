<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản lý Sách - Thư viện</title>
        <%@ include file="layout/header.jsp" %>
    </head>
    <body>
        <%@ include file="layout/nav.jsp" %>

        <div class="container-fluid">
            <div class="row">
                <div class="col-2">
                    <%@ include file="layout/sidebar.jsp" %>
                </div>

                <div class="col-10 mt-3">
                    <div class="container mt-4">

                        <!-- Bảng danh sách sách -->
                        <div class="card shadow-sm">
                            <div class="card-header bg-white d-flex justify-content-between align-items-center">
                                <h5 class="mb-0"><i class="bi bi-book"></i> Quản lý sách</h5>
                                <button class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#modalAddBook">
                                    <i class="bi bi-plus-circle"></i> Thêm sách
                                </button>
                            </div>

                            <div class="card-body">
                                <!-- Form tra cứu -->
                                <form class="row g-3 mb-3" action="sach" method="post">
                                    <input type="hidden" name="action" value="search" />
                                    <div class="col-md-6">
                                        <input type="text" name="tensach" value="${param.tensach}" class="form-control" placeholder="Nhập tên sách cần tra cứu...">
                                    </div>
                                    <div class="col-md-3">
                                        <button type="submit" class="btn btn-primary">
                                            <i class="bi bi-search"></i> Tra cứu
                                        </button>
                                    </div>
                                </form>

                                <!-- Bảng danh sách -->
                                <div class="table-responsive">
                                    <table class="table table-striped align-middle" id="tableSach">
                                        <thead class="table-primary">
                                            <tr>
                                                <th>Mã sách</th>
                                                <th>Tên sách</th>
                                                <th>Tác giả</th>
                                                <th>Năm XB</th>
                                                <th>Thể loại</th>
                                                <th>Số lượng</th>
                                                <th>Thao tác</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <!-- truyền lstSach -->
                                            <c:forEach var="s" items="${lstSach}">
                                                <tr>
                                                    <td>${s.maSach}</td>
                                                    <td>${s.tenSach}</td>
                                                    <td>${s.tacGia}</td>
                                                    <td>${s.namxuatban}</td>
                                                    <td>${s.theLoai}</td>
                                                    <td>${s.soLuong}</td>
                                                    <td>
                                                        <a href="sach?action=showEdit&maSach=${s.maSach}" class="btn btn-warning btn-sm">
                                                            <i class="bi bi-pencil-square"></i>
                                                        </a>
                                                        <a href="sach?action=alertDel&maSach=${s.maSach}" class="btn btn-danger btn-sm">
                                                            <i class="bi bi-trash"></i>
                                                        </a>
                                                    </td>
                                                </tr>  
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <!-- Modal: Thêm sách -->
                        <div class="modal fade" id="modalAddBook" tabindex="-1" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <form action="sach" method="post">
                                        <input type="hidden" name="action" value="insert" />
                                        <div class="modal-header bg-primary text-white">
                                            <h5 class="modal-title"><i class="bi bi-plus-circle me-2"></i>Thêm sách mới</h5>
                                            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label class="form-label">Tên sách</label>
                                                <input type="text" name="TenSach" class="form-control" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Tác giả</label>
                                                <input type="text" name="TacGia" class="form-control">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Năm xuất bản</label>
                                                <input type="number" name="NamXuatBan" class="form-control">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Thể loại</label>
                                                <input type="text" name="TheLoai" class="form-control">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Số lượng</label>
                                                <input type="number" name="SoLuong" class="form-control" min="1" value="1">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                            <button type="submit" class="btn btn-primary">Lưu</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <!-- Modal: Sửa sách -->
                        <div class="modal fade" id="modalEditBook" tabindex="-1" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <form action="sach" method="post">
                                        <input type="hidden" name="action" value="update">
                                        <div class="modal-header bg-warning text-dark">
                                            <h5 class="modal-title"><i class="bi bi-pencil-square me-2"></i>Sửa thông tin sách</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label class="form-label">Mã sách</label>
                                                <input type="text" class="form-control" name="MaSach" value="${editSach.maSach}" id="editMaSach" readonly>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Tên sách</label>
                                                <input type="text" class="form-control" name="TenSach" value="${editSach.tenSach}" id="editTenSach" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Tác giả</label>
                                                <input type="text" class="form-control" name="TacGia" value="${editSach.tacGia}" id="editTacGia">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Năm xuất bản</label>
                                                <input type="number" class="form-control" name="NamXuatBan" value="${editSach.namxuatban}" id="editNamXuatBan">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Thể loại</label>
                                                <input type="text" class="form-control" name="TheLoai" value="${editSach.theLoai}" id="editTheLoai">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Số lượng</label>
                                                <input type="number" class="form-control" name="SoLuong" value="${editSach.soLuong}" id="editSoLuong" min="1">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                            <button type="submit" class="btn btn-warning text-white">Cập nhật</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>      
            </div>
        </div>

        <!-- Modal: Xác nhận xóa -->
        <div class="modal fade" id="delAlertForm" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header bg-danger text-white">
                        <h5 class="modal-title"><i class="bi bi-exclamation-triangle me-2"></i> Xác nhận xóa</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        Bạn có chắc chắn muốn <b>xóa sách ${delSach.tenSach}</b> không?
                    </div>
                    <div class="modal-footer">
                        <a href="sach?action=delete&maSach=${delSach.maSach}" class="btn btn-danger">Xóa</a>
                        <button class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    </div>
                </div>
            </div>
        </div>                                            

        <%@ include file="layout/footer.jsp" %>
        <%@include file="layout/alert.jsp" %>
        <c:if test="${not empty editSach}">
            <script>
                window.onload = function () {
                    var editSachModal = new bootstrap.Modal(document.getElementById('modalEditBook'));
                    editSachModal.show();
                }
            </script>
        </c:if>
        <!--  nếu servlet có attribute deleteSach  -->
        <c:if test="${not empty delSach}"> 
            <script>
                var deleteAlert = new bootstrap.Modal(document.getElementById('delAlertForm'), {});<!-- trỏ form delete vào deleteAlert -->
                window.onload = function () {
                    deleteAlert.show();   //show ra thông báo
                };
            </script>
        </c:if>
    </body>
</html>
