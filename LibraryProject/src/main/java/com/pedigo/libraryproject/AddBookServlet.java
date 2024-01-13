package com.pedigo.libraryproject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "AddBookServlet", value = "/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    BookJDBC jdbc;
    private String message;

    public AddBookServlet() {
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

        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String type = request.getParameter("type");

        try {
            System.out.println("Adding book: " + name + " by " + author + " of type " + type);
            jdbc.addBook(name, author, type);
        } catch (SQLException e) {
            System.out.println("ERROR ADDING BOOK");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR ADDING BOOK");
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.print("<html>\n" +
                "<head>\n" +
                "    <title>Book Added</title>\n" +
                "</head>\n" +
                "<style>\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>");
        out.print("<div id=\"table\">\n" +
                "    <h1>Book Added</h1>\n" +
                "    <table class=\"table\">\n" +
                "        <tr>\n" +
                "            <th scope=\"col\">Name</th>\n" +
                "            <th scope=\"col\">Author</th>\n" +
                "            <th scope=\"col\">Type</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>" + name + "</td>\n" +
                "            <td>" + author + "</td>\n" +
                "            <td>" + type + "</td>\n" +
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