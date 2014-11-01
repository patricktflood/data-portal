<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

        <span class="badge badge-important">!</span>
        <span class="label label-important"> ${fn:length(testresults)} Errors found</span>

        <br><br>

        <table class="table">
            <tr bgcolor="#8dc0d9">
                <td>ID</td>
                <td>ERROR MESSAGE</td>
                <td>VALUE</td>
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
