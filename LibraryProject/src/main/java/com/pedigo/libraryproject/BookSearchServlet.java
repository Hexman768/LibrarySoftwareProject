package com.pedigo.libraryproject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookSearchServlet", value = "/BookSearchServlet")
public class BookSearchServlet extends HttpServlet {
    BookJDBC jdbc;
    private String message;

    public BookSearchServlet() {
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

        List<Book> results = new ArrayList<>();

        try {
            results = jdbc.searchBooks(name, author, type);
        } catch (SQLException e) {
            System.out.println("ERROR ADDING BOOK");
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        String html = "<html>\n" +
                "<head>\n" +
                "    <title>Search Results</title>\n" +
                "    <script src=\"sorttable.js\"></script>\n" +
                "</head>\n" +
                "<style>\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
        "<div id=\"table\">\n" +
                "    <h1>Search Results</h1>\n" +
                "    <table class=\"sortable\">\n" +
                "        <thead>\n" +
                "            <tr>\n" +
                "                <th>UUID</th>\n" +
                "                <th>Name</th>\n" +
                "                <th>Author</th>\n" +
                "                <th>Type</th>\n" +
                "                <th>Status</th>\n" +
                "            </tr>\n" +
                "        </thead>";

        for (int i = 0; i < results.size(); i++) {
            html += "        <tbody>\n" +
                    "        <tr>\n" +
                    "            <td>" + results.get(i).getUUID() + "</td>\n" +
                    "            <td>" + results.get(i).getTitle() + "</td>\n" +
                    "            <td>" + results.get(i).getAuthor() + "</td>\n" +
                    "            <td>" + results.get(i).getType() + "</td>\n" +
                    "            <td>" + results.get(i).getStatus() + "</td>\n" +
                    "        </tr>\n" +
                    "        </tbody>";
        }

                html += "    <tfoot></tfoot>\n" +
                        "    </table>\n" +
                "    <form action = \"CheckoutBookServlet\">\n" +
                "        <input type='text' name='uuid' placeholder='UUID of book to checkout'>\n" +
                "        <button type=\"submit\" class=\"btn\" name=\"submit\">Checkout Book</button>\n" +
                "    </form>\n" +
                "    <form action = \"CheckinBookServlet\">\n" +
                "        <input type='text' name='uuid' placeholder='UUID of book to checkin'>\n" +
                "        <button type=\"submit\" class=\"btn\" name=\"submit\">Checkin Book</button>\n" +
                "    </form>\n" +
                "    <form action = \"RemoveBookServlet\">\n" +
                "        <input type='text' name='uuid' placeholder='UUID of book to remove'>\n" +
                "        <button type=\"submit\" class=\"btn\" name=\"submit\">Remove Book</button>\n" +
                "    </form>\n" +
                "    <form action = \"EditBookServlet\">\n" +
                "        <input type='text' name='uuid' placeholder='UUID of book to edit'>\n" +
                "        <input type='text' name='name' placeholder='Title'>\n" +
                "        <input type='text' name='author' placeholder='Author'>\n" +
                "        <input type='text' name='type' placeholder='Type'>\n" +
                "        <input type='text' name='status' placeholder='Status'>\n" +
                "        <button type=\"submit\" class=\"btn\" name=\"submit\">Edit Book</button>\n" +
                "    </form>\n" +
                "</div>";
        out.print(html);
        out.println("</body></html>");
        out.close();
    }

    public void destroy() {
    }
}