package com.pedigo.libraryproject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name = "CheckinBookServlet", value = "/CheckinBookServlet")
public class CheckinBookServlet extends HttpServlet {
    BookJDBC jdbc;
    private String message;

    public CheckinBookServlet() {
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
            jdbc.checkinBook(uuid);
        } catch (SQLException e) {
            System.out.println("ERROR CHECKING OUT BOOK");
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.print("<html>\n" +
                "<head>\n" +
                "    <title>Checkin book</title>\n" +
                "</head>\n" +
                "<style>\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>");
        out.print("<div id=\"table\">\n" +
                "    <h1>Book successfully checked in!</h1>\n" +
                "    <form action = \"search.jsp\">\n" +
                "        <button>Back</button>\n" +
                "    </form>\n" +
                "</div>");
        out.println("</body></html>");
        out.close();
    }

    public void destroy() {
    }
}