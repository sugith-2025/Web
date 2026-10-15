import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DatabaseServlet: reads all rows of the Employees table (database "test")
 * and displays them as an HTML table when the form on index.html is submitted.
 */
public class DatabaseServlet extends HttpServlet {

    // ---- CHANGE THESE to match your MySQL setup ----
    private static final String DB_URL  = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "SBK1010";   // the password you set during MySQL install

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Employee Records</title></head><body>");
        out.println("<h2>Records of the Employees table</h2>");

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load the JDBC driver (new connector first, old 5.x connector as fallback)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                Class.forName("com.mysql.jdbc.Driver");
            }

            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT id, age, first, last FROM Employees");

            out.println("<table border='1' cellpadding='6' cellspacing='0'>");
            out.println("<tr><th>ID</th><th>Age</th><th>First</th><th>Last</th></tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getInt("age") + "</td>");
                out.println("<td>" + rs.getString("first") + "</td>");
                out.println("<td>" + rs.getString("last") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (Exception e) {
            out.println("<p style='color:red'>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); }   catch (Exception ignored) { }
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) { }
            try { if (con != null) con.close(); }  catch (Exception ignored) { }
        }

        out.println("<br><a href='index.html'>Back</a>");
        out.println("</body></html>");
    }
}
