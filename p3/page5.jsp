<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="p3.AuxBean13"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html><head><title>filewitherrors</title></head>
<body>
    
    <% AuxBean13 e = (AuxBean13)request.getAttribute("e"); %>
    <h2> Music Information service  </h2>

    <b> Query 1: Phase 3   </B> <c:out   value="(Country = ${e.getMsg()} , Album = ${e.getMsg1()}  ) " >  </c:out>  <br><br>
   
   
    <b>This is the query result: </b> <br><br>
    
     <c:forEach var="fileerr" items="${e.getSongs()}" varStatus ="loop">
         <c:out   value="Title =${fileerr.getTitle()} --- Language =${fileerr.getLang()} ---Genres =  ${e.getgenress(loop.index)} --- Composer = ${fileerr.getComposer()} " >  </c:out>  <br>
        </c:forEach>

     
        
    


        <form name='Back'>
            <input type='hidden' name='p' value='naoufal777'>
            <input type='hidden' name='pphase' value='12'>
            <input type='hidden' name='pcountry' value=${e.getMsg()}>
            <h2><input type='submit' value = 'Back'> </h2><h2></form>
         <form name='Home'>
            <input type='hidden' name='p' value='naoufal777'>
            <input type='hidden' name='pphase' value='01'>
            <h2><input type='submit' value = 'Home'> </h2><h2></form> 
                <br><br><br><br><br><br><br><br>&copy Naoufal Elazzouzi
</body>
</html>