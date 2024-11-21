import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SoftwareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String accessLevels = String.join(",", request.getParameterValues("accessLevels"));

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/yourdb", "user", "password");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, accessLevels);
            stmt.executeUpdate();

            response.sendRedirect("createSoftware.jsp?success=Software added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("createSoftware.jsp?error=Database error");
        }
    }
}
