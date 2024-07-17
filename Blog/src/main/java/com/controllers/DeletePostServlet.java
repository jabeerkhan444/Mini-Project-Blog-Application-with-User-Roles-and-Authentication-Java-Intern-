package com.controllers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.DatabaseConnection;

import java.sql.*;
@WebServlet("/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM posts WHERE id=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            response.sendRedirect("adminPosts");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("adminPosts");
        }
    }
}