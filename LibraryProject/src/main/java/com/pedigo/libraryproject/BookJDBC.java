package com.pedigo.libraryproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookJDBC {
    Connection connection;

    /**
     * Constructs the BookJDBC class.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public BookJDBC() throws SQLException, ClassNotFoundException {
        initializeDB();
    }

    /**
     * Initializes the connection the databse.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void initializeDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/library", "root", "root");
    }

    /**
     * Adds a book to the database. It should be noted that when a book is added,
     * it is stored in the database as "I" for checked in by default.
     * @param title The title of the Book to be added
     * @param author The author of the Book to be added
     * @param type The type of the Book to be added
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addBook(String title, String author, String type) throws SQLException, ClassNotFoundException {
        String queryString = "INSERT INTO books (uuid, title, author, bstatus, btype) VALUES (?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(queryString);

        preparedStatement.setString(1, String.valueOf(UUID.randomUUID()));
        preparedStatement.setString(2, title);
        preparedStatement.setString(3, author);
        preparedStatement.setString(4, "I");
        preparedStatement.setString(5, type);
        preparedStatement.executeUpdate();
    }

    /**
     * Returns a list of book objects from the database by the title of the book.
     * @param title The title of the book to be returned.
     * @return A list of Book objects.
     * @throws SQLException
     */
    public List<Book> getBooksByTitle(String title) throws SQLException {
        String queryString = "SELECT * FROM books WHERE title = '" + title + "';";

        Statement statement = connection.createStatement();

        List<Book> books = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery(queryString);
        while (resultSet.next()) {
            books.add(new Book(UUID.fromString(resultSet.getString(1)),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)));
        }
        return books;
    }

    /**
     * Returns a list of book objects from the database by the author of the book.
     * @param author The author of the book to be returned.
     * @return A list of Book objects.
     * @throws SQLException
     */
    public List<Book> getBooksByAuthor(String author) throws SQLException {
        String queryString = "SELECT * FROM books WHERE author = '" + author + "';";

        Statement statement = connection.createStatement();

        List<Book> books = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery(queryString);
        while (resultSet.next()) {
            books.add(new Book(UUID.fromString(resultSet.getString(1)),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)));
        }
        return books;
    }

    /**
     * Returns a list of book objects from the database by the type of the book.
     * @param type The type of the book to be returned.
     * @return A list of Book objects.
     * @throws SQLException
     */
    public List<Book> getBooksByType(String type) throws SQLException {
        if (type == null || type == "") {
            type = "Fiction";
        }

        String queryString = "SELECT * FROM books WHERE btype = '" + type + "';";

        Statement statement = connection.createStatement();

        List<Book> books = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery(queryString);
        while (resultSet.next()) {
            books.add(new Book(UUID.fromString(resultSet.getString(1)),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)));
        }
        return books;
    }

    /**
     * Searches the database for books with the given information.
     * @param title The title of the books to be returned
     * @param author The author of the books to be returned
     * @param type The type of books to be returned
     * @return A list of Book objects
     * @throws SQLException
     */
    public List<Book> searchBooks(String title, String author, String type) throws SQLException {
        if (title != null && title != "" && (author == "" || author == null) && (type == "" || type == null)) {
            return getBooksByTitle(title);
        } else if ((title == null || title == "") && (author != null && author != "") && (type == null || type == "")) {
            return getBooksByAuthor(author);
        } else {
            return getBooksByType(type);
        }
    }

    /**
     * This method updates a given book in the database by its UUID. The update just changes the bstatus column to "O".
     * @param uuid The UUID of the book to be updated
     * @throws SQLException
     */
    public void checkoutBook(UUID uuid) throws SQLException {
        String queryString = "UPDATE books SET bstatus = 'O' WHERE uuid = '" + uuid.toString() + "';";

        PreparedStatement preparedStatement = connection.prepareStatement(queryString);

        preparedStatement.executeUpdate();
    }

    /**
     * This method updates a given book in the database by its UUID. The update just changes the bstatus column to "I".
     * @param uuid The UUID of the book to be updated
     * @throws SQLException
     */
    public void checkinBook(UUID uuid) throws SQLException {
        String queryString = "UPDATE books SET bstatus = 'I' WHERE uuid = '" + uuid.toString() + "';";

        PreparedStatement preparedStatement = connection.prepareStatement(queryString);

        preparedStatement.executeUpdate();
    }

    /**
     * Removes a book from the databse.
     * @param uuid The UUID of the book to be removed
     * @throws SQLException
     */
    public void removeBook(UUID uuid) throws SQLException {
        String queryString = "DELETE FROM books WHERE uuid = '" + uuid.toString() + "';";

        PreparedStatement preparedStatement = connection.prepareStatement(queryString);

        preparedStatement.executeUpdate();
    }

    /**
     * Edits the database columns of a book by the UUID of the book.
     * @param uuid The UUID of the book to be updated
     * @param title The updated title of the book
     * @param author The updated author of the book
     * @param type The updated type of the book
     * @param status The updated status of the book
     * @throws SQLException
     */
    public void editBook(UUID uuid, String title, String author, String type, String status) throws SQLException {
        String queryString = "UPDATE books SET title = '" + title + "', author = '" + author + "', bstatus = '" + status + "', btype = '" + type + "' WHERE uuid = '" + uuid.toString() + "';";

        PreparedStatement preparedStatement = connection.prepareStatement(queryString);

        preparedStatement.executeUpdate();
    }
}
