<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library Software</title>
</head>
<style>
    .topnav {
        background-color: #333;
        overflow: hidden;
    }

    .topnav a {
        float: left;
        color: #f2f2f2;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 17px;
    }

    .topnav a:hover {
        background-color: #ddd;
        color: black;
    }

    .topnav a.active {
        background-color: #04AA6D;
        color: white;
    }

    .container-addForm {
        text-align: center;
        font-size: 25px;
        position: absolute;
        top: 30%;
        left: 45%;
    }
</style>
<body>
<div class="topnav">
    <a class="active" href="index.jsp">Home</a>
    <a href="search.jsp">Search Books</a>
</div>
<div class="container-addForm">
    <form id="addForm" action="AddBookServlet" method="get">
        <div class="row">
            <label>Name</label>
            <input type="text" id="bookName" name="name" placeholder="Book Name">
        </div>
        <br>
        <div class="row">
            <label>Author</label>
            <input type="text" id="bookAuthor" name="author" placeholder="Author Name">
        </div>
        <br>
        <div class="row">
            <label>Type</label>
            <input type="text" id="bookType" name="type" placeholder="Fiction or Nonfiction">
        </div>
        <br>
        <div class="row">
            <button type="submit" class="btn" name="submit">Add Book</button>
        </div>
    </form>
</div>
</body>
</html>