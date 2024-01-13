package com.pedigo.libraryproject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name = "RemoveBookServlet", value = "/RemoveBookServlet")
public class RemoveBookServlet extends HttpServlet {
    BookJDBC jdbc;
    private String message;

    public RemoveBookServlet() {
    }

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (jdbc == null) {
            try {
                jdbc = new BookJDBC();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        response.setContentType("text/html");

        UUID uuid = UUID.fromString(request.getParameter("uuid"));

        try {
            jdbc.removeBook(uuid);
        } catch (SQLException e) {
            System.out.println("ERROR CHECKING OUT BOOK");
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.print("<html>\n" +
                "<head>\n" +
                "    <title>Book Removed</title>\n" +
                "</head>\n" +
                "<style>\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>");
        out.print("<div id=\"table\">\n" +
                "    <h1>Book Removed</h1>\n" +
                "    <table class=\"table\">\n" +
                "        <tr>\n" +
                "            <th scope=\"col\">UUID</th>\n" +
                "            <th scope=\"col\">Author</th>\n" +
                "            <th scope=\"col\">Type</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>" + uuid + "</td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "    <form action = \"index.jsp\">\n" +
                "        <button>Home</button>\n" +
                "    </form>\n" +
                "</div>");
        out.println("</body></html>");
        out.close();
    }

    public void destroy() {
    }
}