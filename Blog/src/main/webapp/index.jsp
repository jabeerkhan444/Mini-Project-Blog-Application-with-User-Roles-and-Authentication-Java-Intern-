<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Redirect to the servlet URL
    response.sendRedirect(request.getContextPath() + "/LogoutHomeServlet");
%>
