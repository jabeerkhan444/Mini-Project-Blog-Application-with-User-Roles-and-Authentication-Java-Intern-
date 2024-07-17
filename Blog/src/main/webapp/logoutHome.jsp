<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Posts</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        header {
            background-color: gray;
            color: orangered;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: sticky;
            top: 0;
            width: 100%;
            z-index: 1000;
        }

        .logo {
            font-size: 50px;
            font-weight: bold;
            animation: glow 1.5s infinite alternate;
        }

        @keyframes glow {
            0% {
                text-shadow: 0 0 5px #fff, 0 0 10px #fff, 0 0 15px #fff, 0 0 20px #4CAF50, 0 0 25px #4CAF50, 0 0 30px #4CAF50, 0 0 35px #4CAF50;
            }
            100% {
                text-shadow: 0 0 10px #fff, 0 0 20px #fff, 0 0 30px #fff, 0 0 40px #4CAF50, 0 0 50px #4CAF50, 0 0 60px #4CAF50, 0 0 70px #4CAF50;
            }
        }

        nav {
            display: flex;
            align-items: center;
            margin: 0 5%;
        }

        nav a {
            background-color: orangered;
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
            text-decoration: none;
            margin: 0 10px;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        nav a:hover {
            background-color: #c73a1f; /* Darker shade on hover */
        }

        .search-bar {
            display: flex;
            align-items: center;
            justify-content: center;
            flex: 1;
        }

        .search-bar input[type="text"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-right: 5px;
            width: 300px; /* Fixed width for the search bar */
        }

        .search-bar button {
            padding: 10px 15px;
            font-size: 16px;
            background-color: orangered;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .search-bar button:hover {
            background-color: #c73a1f;
        }

        .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin: 3% 10%;
        }

        .item {
    margin: 10px;
    border: 5px solid #ccc;
    border-radius: 20px;
    overflow: hidden;
    width: calc(25% - -68px);
    background-color: white;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    box-sizing: border-box;
    transition: transform 0.2s;
    text-decoration: none;
}

        .item:hover {
            transform: scale(1.05);
        }

        .item img {
            width: 100%; /* Fill the width */
            height: 150px; /* Fixed height for standardization */
            object-fit: cover; /* Maintain aspect ratio */
        }

        .text-container {
            padding: 10px;
        }

        h3, p {
            margin: 5px 0;
        }
    </style>
</head>
<body>

<header>
    <div class="logo">Origine</div>
    <div class="search-bar">
        <form action="SearchPostsServlet" method="get">
            <input type="text" name="search" placeholder="Search..." required>
            <button type="submit">Search</button>
        </form>
    </div>
    <nav>
        
        <a href="register.jsp">Register</a>
            <a href="login.jsp">Login</a>
        
    </nav>
</header>
<div class="container">
    <c:forEach var="post" items="${sessionScope.posts}">
        <a href="login.jsp" class="item">
            <c:if test="${not empty post.filePath}">
                <img src="${pageContext.request.contextPath}/uploads/${post.filePath}" alt="${post.title}">
            </c:if>
            <div class="text-container">
                <h3>${post.title}</h3>
                
            </div>
        </a>
    </c:forEach>
</div>

</body>
</html>
