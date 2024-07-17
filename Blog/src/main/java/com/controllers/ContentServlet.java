package com.controllers;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.DatabaseConnection;
import com.model.Post;

@WebServlet("/ContentServlet")
public class ContentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postId = request.getParameter("id");

System.out.println(postId);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM posts WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, postId);
            rs = ps.executeQuery();

            if (rs.next()) {
                Post post = new Post(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("fileName"),rs.getInt("userId"));
                request.setAttribute("post", post);
                request.getRequestDispatcher("content.jsp").forward(request, response);
            } else {
                response.sendRedirect("viewAllPosts.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("viewAllPosts.jsp");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

