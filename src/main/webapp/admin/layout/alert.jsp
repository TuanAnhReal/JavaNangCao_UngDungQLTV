<%-- 
    Document   : alert
    Created on : Oct 15, 2025, 12:41:14 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
if(request.getAttribute("success") != null ){
%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        Swal.fire({
            position: "top-end",
            icon: "success",
            title: '${success}',
            showConfirmButton: false,
            timer: 1500
        });
    </script>    
<%
    }
%>
