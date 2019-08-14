<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <title>List All Lifts</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <div id="welcome"><a href="/">Home</a></div>
        <h2>Lift Summary in Ski Paradise</h2>
    </div>
    <h3>Found all lifts:</h3>
    <table class="table table-striped">
        <tr>
            <th>lift name</th>
            <th>connected lifts</th>
        </tr>
        <c:forEach items="${liftgraph}" var="liftentry" >
            <tr>
                <td>
                    <c:out value="${liftentry.key}" />
                </td>
                <td>
                    <c:forEach items="${liftentry.value}" var="connectedlift">
                        <%--${connectedlift}<br>--%>
                        <c:if test="${connectedlift != liftentry.key}">
                            <li>${connectedlift}</li>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>