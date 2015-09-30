<%-- 
    Document   : add
    Created on : Sep 29, 2015, 4:10:31 PM
    Author     : Kallie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add</title>
    </head>
    <body>
        <form method="POST" action="AuthorController">
            <table>
                <c:choose>
                    <c:when test="${author}">
                        <tr>
                            <td>ID</td>
                            <td><input type="text" value="${author.authorId}" name="authorId" readonly/>
                        </tr>
                    </c:when>
                </c:choose>
                        <tr>
                            <td>Name</td>
                            <td><input type="text" value="${author.authorName}" name="authorName" readonly/></td>
                        </tr>
                        <c:choose>
                            <c:when test="${author}">
                                <tr>
                                    <td>Date added</td>
                                    <td><input type="text" value="${author.dateAdded}" name="dateAdded" readonly/>
                                </tr>
                            </c:when>
                        </c:choose>
                                <tr>
                                    <input type="submit" value="Save" name="action"/>
                                </tr>
            </table>
        </form>
    </body>
</html>
