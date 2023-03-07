package p2;
import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import javafx.stage.Screen;
import jdk.nashorn.internal.ir.CatchNode;
import jakarta.servlet.annotation.*;


// @WebServlet ("/S2_InfoExchange")
public class FrontEnd extends HttpServlet {
    
    public static void doxmlerror1(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException 
    {
        res.setContentType("application/xml");
        PrintWriter out = res.getWriter();
      out.println("<?xml version='1.0' encoding='utf-8'?>");
      out.println("<wrongRequest>no passwd</wrongRequest>");
     
    }
    public static void doxmlerror2(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException 
    {
        res.setContentType("application/xml");
        PrintWriter out = res.getWriter();
      out.println("<?xml version='1.0' encoding='utf-8'?>");
      out.println("<wrongRequest>bad passwd</wrongRequest>");
     
    }
    public static void doxmlPhase01(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException 
    {
        res.setContentType("application/xml");
        PrintWriter out = res.getWriter();
      out.println("<?xml version='1.0' encoding='utf-8'?>");
      out.println("<service>");
      out.println("<status>OK</status>");
      out.println("</service>");
    }

public static void doxmlPhase02(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException 
        {
        ArrayList<String> filewithfatalerror = DataModel.getfatalerrorfile();               //getting the fatalerror files
        ArrayList<String> filewitherror = DataModel.geterrorfile();                         //getting the error files
        res.setContentType("application/xml");
        PrintWriter out = res.getWriter();
        out.println("<?xml version='1.0' encoding='utf-8'?>");

          out.println("<wrongDocs>");
          out.println("<errors>");
        for (int i=0; i<filewitherror.size(); i++ ) {                                      //loop to go through all the error files
        out.println("<error><file>"+filewitherror.get(i).substring(filewitherror.get(i).lastIndexOf("/")+1)+"</file></error>");
        }
        //get(i).substring(filewitherror.get(i).length(), 12)
        out.println("</errors>");
     
        out.println("<fatalerrors>");
        for (int i=0; i<filewithfatalerror.size(); i++ ) {                                // loop to go through all the fatal error files
        out.println("<fatalerror><file>"+filewithfatalerror.get(i).substring(filewithfatalerror.get(i).length()-12, filewithfatalerror.get(i).length())+"</file></fatalerror>");
        }
        out.println("</fatalerrors>");
        
        out.println("</wrongDocs>");
        }
    
public static void doxmlPhase11(HttpServletRequest req, HttpServletResponse res,ArrayList<String> countrylist)
        throws IOException, ServletException 
            {
                
                res.setContentType("application/xml");
             PrintWriter out = res.getWriter();
              out.println("<?xml version='1.0' encoding='utf-8'?>");
              out.println("<countries>");
               for (int i=0; i<countrylist.size(); i++) {           // loop to print all countries from country list 
                 out.println("<country>"+countrylist.get(i)+"</country>");
                }
             out.println("</countries>");
          
            }
            
public static void doxmlPhase12(HttpServletRequest req, HttpServletResponse res,ArrayList<Album> Albums)
                throws IOException, ServletException 
                {
                    String strcountry = req.getParameter("pcountry");
                    String country =strcountry;
                    
                    res.setContentType("application/xml");
                PrintWriter out = res.getWriter();
                if (strcountry == null) {                                             // we check if a  parameter is missing to show the error meessage 
                    out.println("<?xml version='1.0' encoding='utf-8'?>");
                    out.println("<wrongRequest>no param:pcountry</wrongRequest>"); 
                     }
                else {                                                              //otherwise we print the response
                  out.println("<?xml version='1.0' encoding='utf-8'?>");
                  out.println("<albums>");
                  
                    for (int i=0; i<Albums.size(); i++) {                        // loop to go through all albums and print it's elements
                     out.println("<album year='"+Albums.get(i).getyear()+"' performer='"+Albums.get(i).getperformer()+"'");
                     out.println("review='"+Albums.get(i).getreview()+"'>"+Albums.get(i).getTitle()+"</album>");
                    }
                  out.println("</albums>");
                }
                }


public static void doxmlPhase13(HttpServletRequest req, HttpServletResponse res,ArrayList<Song> Songs)
                throws IOException, ServletException 
                {
                    
                    String strcountry = req.getParameter("pcountry");
                    String country =strcountry;
                    String straid = req.getParameter("paid");
                    String aid =straid;
                    res.setContentType("application/xml");
                     PrintWriter out = res.getWriter();
                    
                if (strcountry == null) {                                                      // we check if a  parameter is missing to show the error meessage 
                    out.println("<?xml version='1.0' encoding='utf-8'?>");
                    out.println("<wrongRequest>no param:pcountry</wrongRequest>"); 
                     }
                else {
                    if (straid == null) {                                                      // we check if a  parameter is missing to show the error meessage 
                        out.println("<?xml version='1.0' encoding='utf-8'?>");
                        out.println("<wrongRequest>no param:paid</wrongRequest>"); 
                    }
                else {                                                                      // otherwise print the response
                  out.println("<?xml version='1.0' encoding='utf-8'?>");
                  out.println("<songs>");
                 
                    
                    for (int i=0; i<Songs.size(); i++) {                                  // loop to go through all songs
                        int j = Songs.get(i).getGenres().size();
                        out.println("<song lang='"+Songs.get(i).getLang()+"' genres ='");
                        for(int x=0;x<j;x++){                                             // loop to print all genres
                            out.print(""+Songs.get(i).getGenres().get(x)+"," ); 
                             }
                        out.print("' composer='"+Songs.get(i).getComposer()+"'>");         
                        out.println(""+Songs.get(i).getTitle()+"</song>"); 
                       
                            }
                  out.println("</songs>");
                }
                }
                }






public static void doGetPhase01(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException 
    {
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h2>Welcome to this service</h2><br><br> Please select a query:<br> <p><a href='?p=naoufal777&pphase=02'>show error files</a><p><br><p><a href='?p=naoufal777&pphase=11'>Query 1:Pop songs of an Album of a Country</a><p>");
    out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>&copy Naoufal Elazzouzi");
    }




    public static void doGetPhase02(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException 
    {
        ArrayList<String> filewithfatalerror = DataModel.getfatalerrorfile();
        ArrayList<String> filewitherror = DataModel.geterrorfile();
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h2> file error page </h2> <br>");

        out.println("<h2>Files with errors : "+filewitherror.size() +"  </h2>");
        for (int i=0; i<filewitherror.size(); i++ ) {
        out.println("-"+filewitherror.get(i)+"<br>");
        }

        out.println("<h2>Files with fatal errors : "+filewithfatalerror.size() +"  </h2>");
        for (int i=0; i<filewithfatalerror.size(); i++ ) {
        out.println("-"+filewithfatalerror.get(i)+"<br>");
        }

        out.println("<form name='Home'>");
        out.println("<input type='hidden' name='p' value='naoufal777'>");
        out.println("<input type='hidden' name='pphase' value='01'>");
        out.println("<h2><input type='submit' value = 'Home'> </h2><h2></form> ");
    }




    public static void doGetPhase11(HttpServletRequest req, HttpServletResponse res,ArrayList<String> countrylist)
    throws IOException, ServletException 
    {
       
        int i;
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
       out.println("<h2>Query1:Phase 1</h2><br>Please,select a country :<br></h2>");
       
       for (i=0; i<countrylist.size(); i++) {
        out.println("<h2><p><a href='?p=naoufal777&pphase=12&pcountry="+countrylist.get(i)+"'> "+countrylist.get(i)+"</a><p>");
       }
   
       
    out.println("<form name='Home'>");
    out.println("<input type='hidden' name='p' value='naoufal777'>");
    out.println("<input type='hidden' name='pphase' value='01'>");
    out.println("<h2><input type='submit' value = 'Home'> </h2><h2></form> ");
    out.println("<br><br><br><br><br><br><br>&copy Naoufal Elazzouzi");
    }




    public static void doGetPhase12(HttpServletRequest req, HttpServletResponse res,ArrayList<Album> Albums)
    throws IOException, ServletException 
    {
        int i;
        
        String strcountry = req.getParameter("pcountry");
        String country =strcountry;
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h2> Query1:Phase 2(Country="+country+") </h2><br>Please, select an album <br>");
        
            for (i=0; i<Albums.size(); i++) {
             out.println("<h2><p>"+i+".<a href='?p=naoufal777&pphase=13&pcountry="+country+"&paid="+Albums.get(i).getaid()+"'>Album="+Albums.get(i).getTitle()+"</a>:--Year="+Albums.get(i).getyear()+"--Performer = "+Albums.get(i).getperformer()+" --Review = "+Albums.get(i).getreview()+"<p></h2>");
            }
         
         out.println("<form name='Back'>");
        out.println("<input type='hidden' name='p' value='naoufal777'>");
        out.println("<input type='hidden' name='pphase' value='11'>");
        out.println("<h2><input type='submit' value = 'Back'> </h2><h2></form> ");
        out.println("<form name='Home'>");
        out.println("<input type='hidden' name='p' value='naoufal777'>");
        out.println("<input type='hidden' name='pphase' value='01'>");
        out.println("<h2><input type='submit' value = 'Home'> </h2><h2></form> ");
        out.println("<br><br><br><br><br><br><br>&copy Naoufal Elazzouzi");
    }



    
    public static void doGetPhase13(HttpServletRequest req, HttpServletResponse res,ArrayList<Song> Songs)
    throws IOException, ServletException 
    {   int i;
        
        String strcountry = req.getParameter("pcountry");
        String country =strcountry;
        String straid = req.getParameter("paid");
        String aid =straid;
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h2> Query1:Phase 3(Country="+country+",album="+aid+") </h2> <br>This is the query result : <br>");
        
        
        for (i=0; i<Songs.size(); i++) {                                         // loop to go througgh all songs 
            int j = Songs.get(i).getGenres().size();
            out.println("<h2><p>"+i+".Title ='"+Songs.get(i).getTitle()+"':--Language="+Songs.get(i).getLang()+"--Genres ="); 
            for(int x=0;x<j;x++){
           out.print(""+Songs.get(i).getGenres().get(x)+"," ); }
           out.print(" --Composer = "+Songs.get(i).getComposer()+"<p></h2>");
           }
        
        out.println("<form name='Back'>");
        out.println("<input type='hidden' name='p' value='naoufal777'>");
        out.println("<input type='hidden' name='pphase' value='12'>");
        out.println("<input type='hidden' name='pcountry' value="+country+">");
        out.println("<h2><input type='submit' value = 'Back'> </h2><h2></form> ");
        out.println("<form name='Home'>");
        out.println("<input type='hidden' name='p' value='naoufal777'>");
        out.println("<input type='hidden' name='pphase' value='01'>");
        out.println("<h2><input type='submit' value = 'Home'> </h2><h2></form> ");
        out.println("<br><br><br><br><br><br>&copy Naoufal Elazzouzi");
    }
}
