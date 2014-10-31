<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/components/header.jsp" %>
</head>

<body>
    <%@ include file="/components/navbar.jsp" %>

    <div class="container">

        <br>
        <h3>Health Check Results for ${filename}</h3>
        <br>

        <table class="table">
            <tr>
                <td>ID</td>
                <td>Error Message</td>
                <td>Value</td>
            </tr>
            <c:forEach items="${testresults}" var="result">
                <tr class="error">
                    <td>${result.ID}</td>
                    <td>${result.message}</td>
                    <td>${result.value}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <br>

    <%@ include file="/components/footer.jsp" %>
</body>
</html>
