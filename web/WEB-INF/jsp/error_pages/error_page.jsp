<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/components/header.jsp" %>
</head>

<body>
    <%@ include file="/components/navbar.jsp" %>

    <div class="container">

        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Error!</strong> An error has occurred. Please try again.
        </div>

        <br>

        <%@ include file="/components/footer.jsp" %>
    </div>

</body>
</html>