<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <title>Ski Paradise</title>
</head>

<body>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
    <h2 class="welcome-title">Welcome to our ski resort, ${name}!</h2>
    </div>
    <div class="view" style="background-image: url('https://images.unsplash.com/photo-1529669851596-ba9a5549af95?ixlib=rb-1.2.1&auto=format&fit=crop&w=1789&q=80');
    background-repeat: no-repeat; background-size: cover; background-position: center center;">
    <div class="btn-group-vertical">
        <a href="/listlifts" class="btn btn-primary btn-lg">List All Lifts</a>
        <br /><br /><br /><br />
        <%--<a href="/listskiers" class="btn btn-primary">List Ten Skiers</a>--%>
        <%--<br />--%>
        <%--<a href="/liftsbyname" class="btn btn-primary">Find Lift By Name</a>--%>
        <%--<br />--%>
        <%--<a href="/topskiersbylift" class="btn btn-primary">Lift's Top Skiers</a>--%>
        <%--<br />--%>
        <a href="/busiestlifts" class="btn btn-primary btn-lg">Daily Busiest Lifts</a>
        <br /><br /><br /><br />
        <a href="/busiestliftsbyhour" class="btn btn-primary btn-lg">Hourly Busiest Lifts</a>
        <br /><br /><br /><br />
        <a href="/leastbusiestliftsbyhour" class="btn btn-primary btn-lg">Hourly Least Busiest Lifts</a>
        <%--<a href="/popularliftbyskier" class="btn btn-primary btn-lg">Daily Favorite Lift for Skier</a>--%>
    </div>
</div>
</div>
</body>

</html>