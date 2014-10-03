<!DOCTYPE html>
<html lang="en">
<head>

    <%@ include file="/components/header.jsp" %>

</head>

<body>

    <%@ include file="/components/navbar.jsp" %>

<div class="container">

    <h1>Data Health Check</h1>
    <p>Please select a program and file you wish to check<br> </p>

    <p>Use <a href="https://s3-eu-west-1.amazonaws.com/elasticbeanstalk-eu-west-1-574987690163/people.xlsx">this</a> XLSX file as a test<br> </p>

    <form method="POST" action="health_check_result.do" id="HealthCheckSubmit" enctype="multipart/form-data">

        <select name="program" form="HealthCheckSubmit" style="width:200px">
            <option></option>
            <option value="TEST">Client 1 - Test Program</option>
        </select>

        <br>
        <br>

        <div class="fileupload fileupload-new" data-provides="fileupload">
            <div class="input-append">
                <div class="uneditable-input span3">
                    <i class="icon-file fileupload-exists"></i>
                    <span class="fileupload-preview"></span>
                </div>
                        <span class="btn btn-file">
                            <span class="fileupload-new">Select file
                        </span>
                            <span class="fileupload-exists">Change
                            </span>
                            <input name="file" type="file" />
                        </span>
                <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">Remove</a>
            </div>
        </div>

        <label class="radio inline">
            <input type="radio" name="fileformat" id="fileformat2" value="XLSX" checked>
            XLSX
        </label>
        <label class="radio inline">
            <input type="radio" name="fileformat" id="fileformat1" value="CSV">
            CSV
        </label>

        <br>
        <br>

        <button class="btn btn-large btn-primary" type="submit">Submit</button>

    </form>
    <!-- /container -->

    <br>

    <%@ include file="/components/footer.jsp" %>


</body>
</html>