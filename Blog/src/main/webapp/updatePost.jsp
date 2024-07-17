<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.jdbc.DatabaseConnection" %>
<%@ page import="com.model.Post" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Post</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .container {
            width: 100%;
            margin: 20px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333333;
            margin-bottom: 20px;
        }
        form {
            max-width: 100%;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #333333;
        }
        input[type="text"], textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #dddddd;
            border-radius: 4px;
            font-size: 16px;
        }
        input[type="file"] {
            margin-bottom: 20px;
        }
        input[type="submit"], .back-link, .delete-link {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
            text-decoration: none;
        }
        input[type="submit"]:hover, .back-link:hover, .delete-link:hover {
            background-color: #0056b3;
        }
        .back-link, .delete-link {
            display: block;
            margin-top: 20px;
            text-align: center;
        }
        .image-preview {
            max-width: 100%;
            height: auto;
            margin-bottom: 20px;
            border: 1px solid #dddddd;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Post</h2>
        <%
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            out.println("Invalid post ID");
            return;
        }

        int id = Integer.parseInt(idParam);
        String title = "";
        String content = "";
        String imagePath = "";

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM posts WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                title = resultSet.getString("title");
                content = resultSet.getString("content");
                imagePath = resultSet.getString("image_path"); // Assuming your image path column is named 'image_path'
            } else {
                out.println("No post found with ID: " + id);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        %>
        <form action="UpdatePostServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<%= id %>">
            <label for="title">Title:</label>
            <input type="text" name="title" id="title" value="<%= title %>" required><br>
            
            <label for="content">Content:</label>
            <textarea name="content" id="content" rows="10" required><%= content %></textarea><br>
            
            <% if (imagePath != null && !imagePath.isEmpty()) { %>
                <label>Current Image:</label>
                <img src="<%= request.getContextPath() + '/' + imagePath %>" alt="Post Image" class="image-preview">
            <% } %>
            
            <label for="image">Upload New Image (optional):</label>
            <input type="file" name="image" id="image"><br>

            <input type="submit" value="Update">
        </form>
        <a href="listPosts" class="back-link">Back to List</a>
        <form action="DeletePostServlet" method="get" class="delete-link">
            <input type="hidden" name="id" value="<%= id %>">
            <input type="submit" value="Delete Post" onclick="return confirm('Are you sure you want to delete this post?');">
        </form>
    </div>
</body>
</html>
