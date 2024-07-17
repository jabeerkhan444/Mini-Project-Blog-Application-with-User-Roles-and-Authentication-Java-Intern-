package com.controllers;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.jdbc.DatabaseConnection;
import com.model.Post;
import java.sql.*;

@WebServlet("/UpdatePostServlet")
@MultipartConfig
public class UpdatePostServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM posts WHERE id=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Post post = new Post();
                        post.setId(rs.getInt("id"));
                        post.setTitle(rs.getString("title"));
                        post.setContent(rs.getString("content"));
                        post.setFilePath(rs.getString("image_path")); // Assuming image_path is the correct column name
                        
                        request.setAttribute("post", post);
                        request.getRequestDispatcher("updatePost.jsp").forward(request, response);
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("adminDashboard.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String filePath = ""; // Initialize for file upload

        // Handle file upload
        Part filePart = request.getPart("image"); // Get the file part
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = filePart.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + "uploads"; // Set the path where you want to save the file
            File uploads = new File(uploadPath);
            if (!uploads.exists()) uploads.mkdir(); // Create directory if it doesn't exist

            File file = new File(uploads, fileName);
            filePart.write(file.getAbsolutePath());
            filePath = "uploads/" + fileName; // Set file path to save in DB
        } else {
            // If no new file uploaded, keep the existing file path (you might want to retrieve it from DB)
            try (Connection con = DatabaseConnection.getConnection()) {
                String sql = "SELECT fileName FROM posts WHERE id=?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            filePath = rs.getString("fileName");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "UPDATE posts SET title=?, content=?, fileName=? WHERE id=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, title);
                ps.setString(2, content);
                ps.setString(3, filePath);
                ps.setInt(4, id);
                ps.executeUpdate();
            }
            // Redirect to AdminPostsServlet after successful update
            response.sendRedirect("adminPosts");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("adminPosts?id=" + id);
        }
    }
}
