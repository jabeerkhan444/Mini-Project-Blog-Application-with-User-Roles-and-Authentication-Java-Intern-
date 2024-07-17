<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Post</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; padding: 50px; }
        h1 { color: #333; }
        form { background-color: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); display: inline-block; text-align: left; }
        label { display: block; margin: 10px 0 5px; font-weight: bold; }
        input[type="text"], textarea { width: 100%; padding: 10px; margin: 5px 0 10px; border: 1px solid #ccc; border-radius: 5px; }
        input[type="file"] { margin: 10px 0; }
        .button { padding: 10px 20px; margin: 10px 0; background-color: #5cb85c; color: white; border: none; border-radius: 5px; cursor: pointer; }
        .button:hover { background-color: #4cae4c; }
    </style>
</head>
<body>
    <h1>Create New Post</h1>
    <form action="createPost" method="post" enctype="multipart/form-data">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required>

        <label for="content">Content:</label>
        <textarea id="content" name="content" rows="10" required></textarea>

        <label for="file">Upload Image/Video:</label>
        <input type="file" id="file" name="file">

        <input type="submit" value="Create Post" class="button">
    </form>
</body>
</html>
