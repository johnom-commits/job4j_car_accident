<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Collection" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>#</th>
            <th>First Name</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="id" scope="session" value="0"/>
        <c:forEach items="${list}" var="item">
            <tr>
                <th scope="row">${id = id + 1}</th>
                <td>${item}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>