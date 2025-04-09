import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DenemeServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";
    private static Connection conn;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL Driver not found", e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
        String action = request.getParameter("action");
    
        try {
            if ("register".equals(action)) registerStudent(request, response);
            else if ("print".equals(action)) printStudents(request, response);
            else if ("delete".equals(action)) delStudent(request, response);
            else if ("update".equals(action)) updateStudent(request, response);
              
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
        String action = request.getParameter("action");
    
        try {
            if ("register".equals(action)) registerStudent(request, response);
            else if ("print".equals(action)) printStudents(request, response);
            else if ("delete".equals(action)) delStudent(request, response);
            else if ("update".equals(action)) updateStudent(request, response);
              
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    private void registerStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html");

        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement pS = conn.prepareStatement("INSERT INTO INFO VALUES (?, ?, ?, ? )");

        pS.setInt(1, Integer.parseInt(request.getParameter("ID")));
        pS.setString(2, request.getParameter("Name"));
        pS.setFloat(3, Float.parseFloat(request.getParameter("GPA")));
        pS.setInt(4, Integer.parseInt(request.getParameter("Age")));
        
        pS.executeUpdate();
        response.sendRedirect("index.jsp");

}

    private void printStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rS = stmt.executeQuery("SELECT * FROM INFO");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Name</th><th>Age</th><th>GPA</th></tr>");
    
        while (rS.next()) {
            out.println("<tr>");
            out.println("<td>" + rS.getInt("ID") + "</td>");
            out.println("<td>" + rS.getString("Name") + "</td>");
            out.println("<td>" + rS.getInt("Age") + "</td>");
            out.println("<td>" + rS.getFloat("GPA") + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        
        rS.close();
        stmt.close();
        out.println("</body></html>");
    }

    private void delStudent(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, IOException{
        response.setContentType("text/html");

        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement pS = conn.prepareStatement("DELETE FROM INFO WHERE ID = ?");
        pS.setInt(1, Integer.parseInt(request.getParameter("ID")));

        pS.executeUpdate();
        
        pS.close();
        conn.close();
        response.sendRedirect("index.jsp");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, IOException{
        response.setContentType("text/html");

        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement pS = conn.prepareStatement("UPDATE INFO SET " + request.getParameter("Column") + " = ? WHERE ID = ?");
        pS.setString(1, request.getParameter("Value"));
        pS.setInt(2, Integer.parseInt(request.getParameter("ID")));

        pS.executeUpdate();

        pS.close();
        conn.close();
        response.sendRedirect("index.jsp");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
