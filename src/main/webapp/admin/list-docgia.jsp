<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Trang Quản trị Thư viện</title>
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

                        <!-- Bảng danh sách độc giả -->
                        <div class="card shadow-sm">
                            <div class="card-header bg-white d-flex justify-content-between align-items-center">
                                <h5 class="mb-0"><i class="bi bi-people"></i> Quản lý độc giả</h5>
                                <button class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#modalAddReader">
                                    <i class="bi bi-plus-circle"></i> Thêm độc giả
                                </button>
                            </div>

                            <div class="card-body">
                                <!-- Form tra cứu -->
                                <form class="row g-3 mb-3" action="docgia" method="post">
                                    <input type="hidden" name="action" value="search" />
                                    <div class="col-md-6">
                                        <input type="text" name="hoten" value="" class="form-control" placeholder="Nhập tên độc giả cần tra cứu...">
                                    </div>
                                    <div class="col-md-3">
                                        <button type="submit" class="btn btn-primary">
                                            <i class="bi bi-search"></i> Tra cứu
                                        </button>
                                    </div>
                                </form>

                                <!-- Bảng danh sách độc giả -->
                                <div class="table-responsive">
                                    <table class="table table-striped align-middle" id="tableDocGia">
                                        <thead class="table-primary">
                                            <tr>
                                                <th>Mã độc giả</th>
                                                <th>Họ tên</th>
                                                <th>Ngày sinh</th>
                                                <th>Địa chỉ</th>
                                                <th>Số điện thoại</th>
                                                <th>Email</th>
                                                <th>Thao tác</th>
                                            </tr>
                                        </thead>
                                        <tbody>                                         
                                            <tr>
                                                <td>1</td>
                                                <td>Nguyễn Văn A</td>
                                                <td>2001-05-12</td>
                                                <td>123 Trần Hưng Đạo, Q.1</td>
                                                <td>0912345678</td>
                                                <td>a.nguyen@gmail.com</td>
                                                <td>
                                                    <button class="btn btn-warning btn-sm btnEdit" data-bs-toggle="modal" data-bs-target="#modalEditReader">
                                                        <i class="bi bi-pencil-square"></i>
                                                    </button>
                                                    <button class="btn btn-danger btn-sm">
                                                        <i class="bi bi-trash"></i>
                                                    </button>
                                                </td>
                                            </tr>                                        
                                                                                        
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <!-- Modal: Thêm độc giả -->
                        <div class="modal fade" id="modalAddReader" tabindex="-1" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <form action="docgia" method="post">
                                        <input name="action" value="add" type="hidden" />
                                        <div class="modal-header bg-primary text-white">
                                            <h5 class="modal-title"><i class="bi bi-person-plus me-2"></i>Thêm độc giả</h5>
                                            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label class="form-label">Họ tên</label>
                                                <input type="text" class="form-control" name="HoTen" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Ngày sinh</label>
                                                <input type="date" class="form-control" name="NgaySinh">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Địa chỉ</label>
                                                <input type="text" class="form-control" name="DiaChi">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Số điện thoại</label>
                                                <input type="text" class="form-control" name="SoDienThoai">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Email</label>
                                                <input type="email" class="form-control" name="Email">
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

                        <!-- Modal: Sửa độc giả -->
                        <div class="modal fade" id="modalEditReader" tabindex="-1" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <form>
                                        <div class="modal-header bg-warning text-dark">
                                            <h5 class="modal-title"><i class="bi bi-pencil-square me-2"></i>Sửa thông tin độc giả</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label class="form-label">Mã độc giả</label>
                                                <input type="text" class="form-control" id="editMaDocGia" readonly>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Họ tên</label>
                                                <input type="text" class="form-control" id="editHoTen" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Ngày sinh</label>
                                                <input type="date" class="form-control" id="editNgaySinh">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Địa chỉ</label>
                                                <input type="text" class="form-control" id="editDiaChi">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Số điện thoại</label>
                                                <input type="text" class="form-control" id="editSoDienThoai">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Email</label>
                                                <input type="email" class="form-control" id="editEmail">
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

        <%@ include file="layout/footer.jsp" %>

    </body>
</html>
