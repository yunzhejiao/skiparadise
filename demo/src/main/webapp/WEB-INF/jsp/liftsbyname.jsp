<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <title>List Connected Lifts for a Lift</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
    <div id="welcome"><a href="/">Home</a></div>
    <h2>Find a lift's Connectivity</h2>
    </div>

    <div class="alert alert-info" role="alert">
    <span id="successMessage"><b>${messages.success}</b></span>
    </div>
    <%--<form action="/liftsbyname" method="get">--%>
        <%--<div class="jumbotron">--%>
            <%--<div id="welcome"><a href="/">Home</a></div>--%>
            <%--<h2>Find a lift's Connectivity</h2>--%>
        <%--</div>--%>

        <%--<p>--%>
            <%--<label for="liftName">LiftName</label>--%>
            <%--<input id="liftName" name="liftName">--%>
        <%--</p>--%>
        <%--</br>--%>
        <%--<p>--%>
            <%--<button type="submit" class="btn btn-sm btn-primary">Submit</button>--%>
            <%--<br/><br/>--%>
        <%--<div class="alert alert-info" role="alert">--%>
            <%--<span id="successMessage"><b>${messages.success}</b></span>--%>
        <%--</div>--%>
        <%--</p>--%>
    <%--</form>--%>

    <c:if test="${param.liftName != null}">
    <h3>Found connected lifts for lift {${liftentry.key}}:</h3>
    <table class="table table-striped">
        <tr>
            <th>lift name</th>
            <th>connected lifts</th>
        </tr>
        <td>
            <c:out value="${liftentry.key}" />
        </td>
        <td>
            <c:forEach items="${liftentry.value}" var="connectedlift">
                <c:if test="${connectedlift != liftentry.key}">
                    <li>${connectedlift}</li>
                </c:if>
            </c:forEach>
        </td>
    </table>
    </c:if>
</div>
</body>
</html>