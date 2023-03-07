<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html><head><title>filewitherrors</title></head>
<body>
    

   
   
    <b>Files with errors:</b><c:out value="${b.getErrorfile().size()}"></c:out> <br><br>
    
     <c:forEach var="fileerr" items="${b.getErrorfile()}">
        <c:out value=". ${fileerr}"></c:out><br>
        </c:forEach>

     <br>
     <br>

    <b>Files with fatalerrors:</b><c:out value="${b.getFatalerrorfile().size()}"></c:out><br> <br>
    <c:forEach var="fatal" items="${b.fatalerrorfile}">
        <c:out value=". ${fatal}"></c:out><br>
        </c:forEach>


        <form name='Home'>
           <input type='hidden' name='p' value='naoufal777'>
            <input type='hidden' name='pphase' value='01'>
           <h2><input type='submit' value = 'Home'> </h2><h2></form> 

            <br><br><br><br><br><br><br><br><br><br><br><br><br>&copy Naoufal Elazzouzi
            
</body>
</html>