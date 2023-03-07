<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="p3.AuxBean12"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html><head><title>filewitherrors</title></head>
<body>
    <% AuxBean12 d = (AuxBean12)request.getAttribute("d"); %>
    <h2> Music Information service  </h2>

    <b> Query 1: Phase 2   </B> <c:out   value="(Country = ${d.getMsg()} ) " >  </c:out>  <br><br>
   
   
    <b>Please, select an Album: </b> <br><br>
    
     <c:forEach var="fileerr" items="${d.getAlbums()}" varStatus ="loop">
        <c:out   value="${loop.index} ." >  </c:out>  <a href="?p=naoufal777&pphase=13&pcountry=${fileerr.getcountry()}&paid=${fileerr.getaid()}">   <c:out   value="Album =${fileerr.getTitle()}" >  </c:out> </a> <c:out   value="--- Year =${fileerr.getyear()} ---Performer = ${fileerr.getperformer()} --- Review = ${fileerr.getreview()} " >  </c:out>  <br>
        </c:forEach>

     
        
    

        <form name='Back'>
            <input type='hidden' name='p' value='naoufal777'>
            <input type='hidden' name='pphase' value='11'>
            <h2><input type='submit' value = 'Back'> </h2><h2></form>
         <form name='Home'>
            <input type='hidden' name='p' value='naoufal777'>
            <input type='hidden' name='pphase' value='01'>
            <h2><input type='submit' value = 'Home'> </h2><h2></form> 
               <br><br><br><br><br><br><br><br>&copy Naoufal Elazzouzi
</body>
</html>