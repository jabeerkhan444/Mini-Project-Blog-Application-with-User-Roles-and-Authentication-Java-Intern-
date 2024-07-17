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
import org.mindrot.jbcrypt.BCrypt;

import com.jdbc.DatabaseConnection;
import com.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection(); // Ensure this method returns a proper DB connection
            String sql = "SELECT * FROM users WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    int userId = rs.getInt("id");
                    String userRole = rs.getString("role"); // Retrieve the role

                    HttpSession session = request.getSession();
                    User user = new User(userId, rs.getString("name"), email, userRole);
                    session.setAttribute("user", user);
                    session.setAttribute("userId", userId); // Store userId in session

                    // Conditional redirection based on role
                    if ("Admin".equalsIgnoreCase(role) && role.equalsIgnoreCase(userRole)) {
                        response.sendRedirect("adminPosts");
                    } else if ("Viewer".equalsIgnoreCase(role) && role.equalsIgnoreCase(userRole)) {
                        response.sendRedirect("AllPostsServlet");
                    } else {
                        // Role does not match
                        request.setAttribute("errorMessage", "No such " + role.toLowerCase() + " found");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } else {
                    // Password does not match
                    request.setAttribute("errorMessage", "Invalid email or password");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                // Email does not exist
                request.setAttribute("errorMessage", "No user found with this email");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
