import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DatabaseServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASS = "sqlroot123";

    private Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // READ
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h2>Employee Records</h2>");
        out.println("<table border='1'><tr><th>ID</th><th>Age</th><th>First</th><th>Last</th></tr>");

        try (Connection con = connect();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Employees")) {

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getInt("age")
                        + "</td><td>" + rs.getString("first") + "</td><td>"
                        + rs.getString("last") + "</td></tr>");
            }
        } catch (Exception e) {
            out.println("<tr><td colspan='4'>Error: " + e.getMessage() + "</td></tr>");
        }
        out.println("</table><br><a href='index.html'>Back</a></body></html>");
    }

    // CREATE, UPDATE, DELETE (chosen by the hidden "action" field in index.html)
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection con = connect()) {
            PreparedStatement ps;
            if ("add".equals(action)) {
                ps = con.prepareStatement("INSERT INTO Employees VALUES (?,?,?,?)");
                ps.setInt(1, id);
                ps.setInt(2, Integer.parseInt(request.getParameter("age")));
                ps.setString(3, request.getParameter("first"));
                ps.setString(4, request.getParameter("last"));
            } else if ("update".equals(action)) {
                ps = con.prepareStatement("UPDATE Employees SET age=?, first=?, last=? WHERE id=?");
                ps.setInt(1, Integer.parseInt(request.getParameter("age")));
                ps.setString(2, request.getParameter("first"));
                ps.setString(3, request.getParameter("last"));
                ps.setInt(4, id);
            } else {
                ps = con.prepareStatement("DELETE FROM Employees WHERE id=?");
                ps.setInt(1, id);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            throw new ServletException(e);
        }
        response.sendRedirect("DatabaseServlet"); // show updated list
    }
}