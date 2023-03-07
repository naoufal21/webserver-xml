<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html><head><title>filewitherrors</title></head>
<body>
    
    <h2> Music Information service  </h2>

        <b> Query 1: Phase 1    </B> <br><br>
   
    <b>Please, select a Country: </b> <br><br>
    
     <c:forEach var="fileerr" items="${c.getcountries()}" varStatus = "loop">
        <c:out   value="${loop.index}." ></c:out> <a href="?p=naoufal777&pphase=12&pcountry=${fileerr}">   <c:out   value=" ${fileerr}" >  </c:out> </a> <br>
        </c:forEach>

     
        
    


        <form name='Home'>
            <input type='hidden' name='p' value='naoufal777'>
             <input type='hidden' name='pphase' value='01'>
            <h2><input type='submit' value = 'Home'> </h2><h2></form> 
                <br><br><br><br><br><br><br><br><br><br><br><br><br><br>&copy Naoufal Elazzouzi
</body>
</html>