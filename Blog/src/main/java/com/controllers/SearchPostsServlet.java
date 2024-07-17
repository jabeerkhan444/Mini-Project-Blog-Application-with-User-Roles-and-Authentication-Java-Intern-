package com.controllers;

import com.jdbc.DatabaseConnection;
import com.model.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchPostsServlet")
public class SearchPostsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        List<Post> posts = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM posts WHERE title LIKE ? OR content LIKE ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, "%" + searchQuery + "%");
                ps.setString(2, "%" + searchQuery + "%");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Post post = new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("fileName"),
                        rs.getInt("userId")
                    );
                    posts.add(post);
                }
                request.setAttribute("posts", posts);
                request.getRequestDispatcher("viewPost.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("viewPost.jsp");
        }
    }
}
