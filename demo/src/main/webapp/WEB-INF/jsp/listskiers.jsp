<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <title>List All Lifts</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <h3>Found ten skiers:</h3>
    <table class="table table-striped">
        <tr>
            <th>skier ID</th>
        </tr>
        <c:forEach items="${skiers}" var="skier" >
            <tr>
                <td>
                    <c:set var="string1" value="${skier}" />
                    <c:set var="string2" value="${fn:split(string1, '=')}" />
                    <c:set var="string3" value="${string2[fn:length(string2)-1]}" />
                    <c:set var="string4" value="${fn:split(string3, '}')}" />
                    <c:set var="lastString" value="${string4[0]}" />

                    <c:out value="${lastString}" />
                </td>
                <%--<td><a href="moviedelete?imdbId=<c:out value="${movie.getImdbId()}"/>">Delete</a></td>--%>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>