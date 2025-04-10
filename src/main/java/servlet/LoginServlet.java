package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import util.DBConnection;

import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Login attempt: Email=" + email + ", Password=" + password);

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            System.out.println("Database connection successful");

            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("User found: Role=" + rs.getString("role"));
                HttpSession session = request.getSession();
                session.setAttribute("userEmail", email);
                session.setAttribute("userRole", rs.getString("role"));
                
                if ("admin".equals(rs.getString("role"))) {
                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    response.sendRedirect("customerDashboard.jsp");
                }
            } else {
                System.out.println("No user found for email=" + email);
                request.setAttribute("error", "Invalid email or password");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            request.setAttribute("error", "Database error: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
