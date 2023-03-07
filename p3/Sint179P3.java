package p3;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import javafx.stage.Screen;
import jdk.nashorn.internal.ir.CatchNode;
import jakarta.servlet.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.io.*; 
import jakarta.servlet.*;
import jakarta.servlet.http.*; 

import p3.AuxBean;
@WebServlet ("/P3M")
public class Sint179P3 extends HttpServlet { 
    private final static String password = "naoufal777";   
    AuxBean01 a;
    AuxBean02 b;
    AuxBean11 c;
    AuxBean12 d;
    AuxBean13 e;

    public void init( ) {                                                       // initial function that gets executed first
       
        try {
           DataModel.getinit();                                                  // call the function to parse everything once
          
        } catch (Exception e) {
            System.out.println("error when parsing");
        }
       

    }

protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String phase;
    String country;
    String aid ;

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String strFase = request.getParameter("pphase");                                        //parameters that we get from requests
    String strcountry = request.getParameter("pcountry");
    String straid = request.getParameter("paid");
    String passwd = request.getParameter("p"); 
    String autothing = request.getParameter("auto");
if (strFase == null || Integer.parseInt(strFase)  == 01) {
a = new AuxBean01();
a.setMsg(passwd);
request.setAttribute("a",a);
ServletContext sc = getServletContext();
RequestDispatcher rd = sc.getRequestDispatcher("/page1.jsp"); 
rd.forward(request,response);
}
else { 
    switch (strFase) {
        
        case "02": 

        b = new AuxBean02();
        request.setAttribute("b",b);                                                                           // error files screen
        ServletContext asc = getServletContext();
        RequestDispatcher ard = asc.getRequestDispatcher("/page2.jsp"); 
        ard.forward(request,response);

        break;


        case "11":                                                                              // country list screen
        c = new AuxBean11();
        request.setAttribute("c",c);                                                                           // error files screen
        ServletContext scc = getServletContext();
        RequestDispatcher rdd = scc.getRequestDispatcher("/page3.jsp"); 
        rdd.forward(request,response);
        break;


        case "12":                                                                          // album list screen
        d = new AuxBean12();
        d.setMsg(strcountry);
        request.setAttribute("d",d);                                                                           // error files screen
        ServletContext sccc = getServletContext();
        RequestDispatcher rddd = sccc.getRequestDispatcher("/page4.jsp"); 
        rddd.forward(request,response);
        break;
       
        case "13":                                                                          // song list screen
        e = new AuxBean13();
        e.setMsg(strcountry,straid);
        request.setAttribute("e",e);                                                                           // error files screen
        ServletContext scccc = getServletContext();
        RequestDispatcher rdddd = scccc.getRequestDispatcher("/page5.jsp"); 
        rdddd.forward(request,response);
        break;
        }
    }
  
}


//protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//Date date = new Date(); ab.setMsg(date.toString()); request.setAttribute("theBean", ab);
//ServletContext sc = getServletContext();
//RequestDispatcher rd = sc.getRequestDispatcher("/page1.jsp"); rd.forward(request,response);

//}
}