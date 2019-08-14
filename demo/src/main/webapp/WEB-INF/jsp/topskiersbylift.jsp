<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <title>List Top Skiers For A Lift</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
    <div id="welcome"><a href="/">Home</a></div>
    <h2>List Top Ten Skiers For A Lift</h2>
    </div>
    <div class="alert alert-info" role="alert">
    <span id="successMessage"><b>${messages.success}</b></span>
    </div>

    <c:if test="${param.liftName != null}">
    <h3>Found lift's top 10 active skiers:</h3>
    <table class="table table-striped">
        <tr>
            <th>skier ID</th>
            <th>runs</th>
            <th>link to skier's favorite lifts</th>

        </tr>
        <c:forEach items="${skiers}" var="skier" >
            <tr>
                <td>
                    <c:out value="${skier.key}" />
                </td>
                <td>
                    <c:out value="${skier.value}" />
                </td>

                <td>
                    <c:url var="myURL" value="/popularliftbyskier">
                        <c:param name="skierId" value="${fn:replace(skier.key,'\"','')}"/>
                        <c:param name="season" value="${season}"/>
                        <c:param name="day" value="${day}"/>
                        <c:param name="weather" value="${weather}"/>
                    </c:url>
                    <a href="${myURL}">skier preference</a>

                </td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
</div>
</body>
</html>