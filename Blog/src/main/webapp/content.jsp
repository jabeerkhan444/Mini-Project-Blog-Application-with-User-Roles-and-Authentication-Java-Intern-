<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${post.title}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        h1 {
            color: #333;
        }
        .post-container {
            background-color: white;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        img {
            max-width: 100%;
            height: auto;
            border-radius: 5px;
        }
        .footer {
            margin-top: 20px;
            text-align: right;
        }
    </style>
</head>
<body>

<div class="post-container">
    <h1>${post.title}</h1>
    <c:if test="${not empty post.filePath}">
        <img src="${pageContext.request.contextPath}/uploads/${post.filePath}" alt="${post.title}">
    </c:if>
    <p>${post.content}</p>
    

    <div class="footer">
        <a href="viewAllPosts.jsp">Back to Posts</a>
    </div>
</div>

</body>
</html>
