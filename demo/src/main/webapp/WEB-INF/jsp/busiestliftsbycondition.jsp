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
    <title>List Lifts by Lift Name</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <form action="/busiestlifts" method="get">
        <div class="jumbotron">
            <div id="welcome"><a href="/">Home</a></div>
            <h2>Search for Top Five Busiest Lifts by Condition</h2>
        </div>

        <p>
            <select name="season">
                <option value="REGULAR">REGULAR</option>
                <option value="EARLY">EARLY</option>
                <option value="LATE">LATE</option>
                <option value="PEAK">PEAK</option>
            </select>
            <select name="day">
                <option value="MONDAY">MONDAY</option>
                <option value="TUESDAY">TUESDAY</option>
                <option value="WEDNESDAY">WEDNESDAY</option>
                <option value="THURSDAY">THURSDAY</option>
                <option value="FRIDAY">FRIDAY</option>
                <option value="SATURDAY">SATURDAY</option>
                <option value="SUNDAY">SUNDAY</option>
            </select>
            <select name="weather">
                <option value="STORMY">STORMY</option>
                <option value="POWDER">POWDER</option>
                <option value="AVERAGE">AVERAGE</option>
            </select>
        </p>
        </br>
        <p>
            <button type="submit" class="btn btn-sm btn-primary">Submit</button>
            <br/><br/>
        <div class="alert alert-info" role="alert">
            <span id="successMessage"><b>${messages.success}</b></span>
        </div>
        </p>
    </form>

    <c:if test="${param.season != null}">
    <h3>Found top 5 busiest lifts:</h3>
    <table class="table table-striped">
        <tr>
            <th>lift name</th>
            <th>daily load</th>
            <th>link to lift's active skiers</th>
        </tr>
        <c:forEach items="${lifts}" var="lift" >
            <tr>
                <td>
                    <c:out value="${lift.key}" />
                </td>
                <td>
                    <c:out value="${lift.value}" />
                </td>
                <td>
                    <c:url var="myURL" value="/topskiersbylift">
                        <c:param name="liftName" value="${fn:replace(lift.key,'\"','')}"/>
                        <c:param name="season" value="${season}"/>
                        <c:param name="day" value="${day}"/>
                        <c:param name="weather" value="${weather}"/>
                    </c:url>
                    <a href="${myURL}">top 10 active skiers</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
</div>
</body>
</html>