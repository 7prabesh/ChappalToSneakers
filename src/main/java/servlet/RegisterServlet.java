package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


import util.DBConnection;

import javax.servlet.annotation.WebServlet;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        System.out.println("Registration attempt: Email=" + email + ", Role=" + role);

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            System.out.println("Database connection successful");

            // Explicitly include user_id with NULL to let AUTO_INCREMENT handle it
            String sql = "INSERT INTO users (user_id, email, password, role, full_name) VALUES (NULL, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.setString(4, fullName);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User registered: Email=" + email);
                request.setAttribute("success", "Registration successful! Please login.");
            } else {
                System.out.println("Registration failed for email=" + email);
                request.setAttribute("error", "Registration failed. Please try again.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            if (e.getMessage().contains("Duplicate entry")) {
                request.setAttribute("error", "Email already exists. Please use a different email.");
            } else {
                request.setAttribute("error", "Database error: " + e.getMessage());
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        rd.forward(request, response);
    }
}