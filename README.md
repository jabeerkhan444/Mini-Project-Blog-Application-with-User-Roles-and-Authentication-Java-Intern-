# Mini-Project-Blog-Application-with-User-Roles-and-Authentication-Java-Intern-

The Blog Application is a dynamic web project designed to manage and display blog posts. It includes features such as user authentication, post creation, post viewing, and search functionality. The application is built using Java Servlets, JSP, and a MySQL database for data storage.

## Features
- User Authentication: Allows users to register and log in.
- Admin Dashboard: Admin users can create and manage posts.
- Post Display: Posts are displayed in a grid format with images and text.
- Search Functionality: Users can search posts by title or content.
- Responsive Design: Ensures the application is accessible on various devices.
## Technologies Used
- Backend: Java Servlets, JSP
- Frontend: HTML, CSS, JSP
- Database: MySQL
- Libraries: JSTL
- Server: Apache Tomcat
## Project Structure
- src/com/controllers: Contains the servlet classes for handling requests.
- src/com/model: Contains the Post model class.
- webapp: Contains the JSP files and static assets (CSS, images).

## Configure Database:

- Create a MySQL database named blog.

- Run the following SQL script to create the necessary tables:

CREATE TABLE users ( </br>
    id INT AUTO_INCREMENT PRIMARY KEY, </br>
    name VARCHAR(100) NOT NULL, </br>
    email VARCHAR(100) NOT NULL UNIQUE, </br>
    password VARCHAR(255) NOT NULL, </br>
    role ENUM('Admin', 'Viewer') NOT NULL, </br>
);


CREATE TABLE posts ( </br>
    id INT AUTO_INCREMENT PRIMARY KEY, </br>
    title VARCHAR(255) NOT NULL, </br>
    content TEXT NOT NULL, </br>
    fileName VARCHAR(255)</br>
);

## Update Database Configuration:

Open src/com/jdbc/DatabaseConnection.java and update the database URL, username, and password.
## Deploy on Tomcat:

Build the project using your IDE.
Deploy the war file to the Apache Tomcat server.
## Access the Application:

Open your web browser and navigate to http://localhost:8080/Blog.
## Usage
1. Register and Log In:

- Register as a new user and log in to access the admin dashboard.
2. Create Post:

- As an admin, create new posts with title, content, and an optional image.
3. View Posts:

- All posts are displayed in a grid format on the main page.
- Click on a post to view more details.
4. Search Posts:

- Use the search bar to find posts by title or content.
## Screenshots
- Home Page
  ![image](https://github.com/user-attachments/assets/5a133d46-e872-4239-b84c-1b3a05cc6e07)
- Register
  As Viewer
  ![Screenshot 2024-07-17 195607](https://github.com/user-attachments/assets/8b36c8e6-d025-4465-abf2-eae123c8bbb3)


