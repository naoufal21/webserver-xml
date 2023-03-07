<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="p3.AuxBean01"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <% AuxBean01 a = (AuxBean01)request.getAttribute("a"); %>
<title> Date and time </title>
</head>
<body>
   
    
    <c:choose>
    <c:when test="${a.getMsg().equals('naoufal777')}">
        <h2> Music Information service  </h2>
        
        
            <h2>Welcome to this service</h2>
            <br><br> Please, select a query:<br> <p><a href="?p=naoufal777&pphase=02"> . show error files</a><p><br><p><a href="?p=naoufal777&pphase=11">. Query 1:Pop songs of an Album of a Country</a><p>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>&copy Naoufal Elazzouzi
        
        <br />
    </c:when>   
    <c:when test="${!a.getMsg().equals('naoufal777') && a.getMsg()!= null }">
    <h2>Wrong password </h2>
    </c:when>
    

    <c:otherwise>
         No password
        <br />
    </c:otherwise>
</c:choose>


</form>
</body>
</html>