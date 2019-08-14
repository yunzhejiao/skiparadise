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
    <title>List Top Five Busiest Lifts by Hour on a Ski Day</title>
</head>
<body>

<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <div id="welcome"><a href="/">Home</a></div>
        <h2>List Five Favorite Lifts For A Skier on A Ski Day</h2>
    </div>
    <div class="alert alert-info" role="alert">
        <span id="successMessage"><b>${messages.success}</b></span>
    </div>

    <c:if test="${param.skierId != null}">
    <h3>Found the top 5 favorite lifts for skier:</h3>
    <table class="table table-striped">
        <tr>
            <th>lift name</th>
            <th>count</th>
            <th>link to connected lifts</th>
            <th>link to lift's daily active skiers</th>

        </tr>
        <c:forEach items="${liftCnts}" var="liftCnt" >
            <tr>
                <td>
                    <c:out value="${liftCnt.key}" />
                </td>
                <td>
                    <c:out value="${liftCnt.value}" />
                </td>
                <td>
                    <c:url var="myURL" value="/liftsbyname">
                        <c:param name="liftName" value="${fn:replace(liftCnt.key,'\"','')}"/>
                    </c:url>
                    <a href="${myURL}">connected lifts</a>
                </td>
                <td>
                    <c:url var="myURL" value="/topskiersbylift">
                        <c:param name="liftName" value="${fn:replace(liftCnt.key,'\"','')}"/>
                        <c:param name="season" value="${season}"/>
                        <c:param name="day" value="${day}"/>
                        <c:param name="weather" value="${weather}"/>
                    </c:url>
                    <a href="${myURL}">lift's daily top 10 active skiers</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
</div>
</body>
</html>