// S2: servlet example to illustrate status info exchange
//     by means of URL rewriting and hidden form fields
package p2;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import javafx.stage.Screen;
import jdk.nashorn.internal.ir.CatchNode;
import jakarta.servlet.annotation.*;
import java.util.ArrayList;


// @WebServlet ("/S2_InfoExchange")
public class Sint179P2 extends HttpServlet {
    private final static String password = "naoufal777";                                                //password
    DataModel a = new DataModel();





    public void init( ) {                                                       // initial function that gets executed first
       
        try {
           DataModel.getinit();                                                  // call the function to parse everything once
          
        } catch (Exception e) {
            System.out.println("error when parsing");
        }
       

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException 
    {
        
        
        String phase;
        String country;
	    String aid ;

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String strFase = req.getParameter("pphase");                                        //parameters that we get from requests
        String strcountry = req.getParameter("pcountry");
	    String straid = req.getParameter("paid");
        String passwd = req.getParameter("p"); 
        String autothing = req.getParameter("auto");
       
        ArrayList<String> countrylist = new ArrayList<String>();                            // list of countries
        ArrayList<Album> Albums = new ArrayList<Album>();                                   // list of albums
        ArrayList<Song> Songs = new ArrayList<Song>();                                      // list of songs
        phase = strFase;


       

        
  
    if (passwd == null ) {                                                      //check if password doesnt exist
        if ((autothing == null) || (autothing.equals(false))) {              //check if it's a normal or auto mode response
            out.println("password missing"); 
           
        }
        else if (autothing.equals("true")) {
            FrontEnd.doxmlerror1(req, res);                                              // if auto ,call the function that answers it
        }
     }

     else  if (!passwd.equals(password)) {                                                  //check if password is wrong
        if ((autothing == null) || (autothing.equals(false))) {                    //check if it's a normal or auto mode response
            out.println("bad password "); 
        }
        else if (autothing.equals("true")) {                                        // if auto , call the function that answers it
            FrontEnd.doxmlerror2(req, res);
         
        }
     }
        
         
     else {
        if (strFase == null) {                                                              // if pphase doesnt exist it's the initial screen 
            if ((autothing == null) || (autothing.equals(false))) {
               
                FrontEnd.doGetPhase01(req, res);
            }
            else if (autothing.equals("true")) {
                FrontEnd.doxmlPhase01(req, res);
            }
          
        }
       else { 
        switch (phase) {
            case "01":                                                                              // initial screen
             
                if ((autothing == null) || (autothing.equals(false))) {
               
                    FrontEnd.doGetPhase01(req, res);
                }
                else if (autothing.equals("true")) {
                    FrontEnd.doxmlPhase01(req, res);
                }
            
            break;
            case "02":                                                                               // error files screen
            if ((autothing == null) || (autothing.equals(false))) {
               
                FrontEnd.doGetPhase02(req, res);
            }
            else if (autothing.equals("true")) {
                FrontEnd.doxmlPhase02(req, res);
            }
            break;


            case "11":                                                                              // country list screen
             try {
                countrylist = DataModel.getQ1Countries();                                             // call function that returns country list 
               }
               catch (Exception  ParserConfigurationException) {
                   
               }
               if ((autothing == null) || (autothing.equals(false))) {
               
                FrontEnd.doGetPhase11(req, res,countrylist);
            }
            else if (autothing.equals("true")) {
                FrontEnd.doxmlPhase11(req, res,countrylist);
            }
            break;


            case "12":                                                                          // album list screen
            try {
                Albums = DataModel.getQ1Albums(strcountry);
               }
               catch (Exception  ParserConfigurationException) {
                   
               }
               if ((autothing == null) || (autothing.equals(false))) {
               
                FrontEnd.doGetPhase12(req, res,Albums);
            }
            else if (autothing.equals("true")) {
                FrontEnd.doxmlPhase12(req, res,Albums);
            }
            break;


            case "13":                                                                          // song list screen
            try {
                Songs = DataModel.getQ1Songs(strcountry, straid);
                }
                catch (Exception  ParserConfigurationException) {
                    
                }
        
        
                if ((autothing == null) || (autothing.equals(false))) {
               
                    FrontEnd.doGetPhase13(req, res,Songs);
                }
                else if (autothing.equals("true")) {
                    FrontEnd.doxmlPhase13(req, res,Songs);
                }
            break;
            }
        }
      
     }




   
}
} 

