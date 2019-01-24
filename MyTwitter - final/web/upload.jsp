<%-- 
    Document   : upload
    Created on : Oct 25, 2017, 1:14:59 AM
    Author     : macbookair
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>File Upload</title>
    </head>
    <body>
        <center>
            <h1>File Upload</h1>
                <form method="post" action="UploadServlet"
                    enctype="multipart/form-data">
                    Select file to upload: <input type="file" name="file" size="60" /><br />
                    <br /> <input type="submit" value="Upload" />
                </form>
        </center>
    </body>
</html>