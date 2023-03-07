package p3;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.sound.midi.SysexMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.xml.bind.annotation.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXParseException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.*;
public class AuxBean13 { 

ArrayList <Song> mylist = new ArrayList<Song>();
public String generos ; 
private String msg;
private String msg1;
public void AuxBean13() { 
    
}

public ArrayList <Song>  getSongs() { 
    try {
        mylist = DataModel.getQ1Songs(msg, msg1);                                                // call the function to parse everything once
       
     } catch (Exception e) {
         System.out.println("error when parsing");
     }
  
    return mylist;
}
public String getgenress(int j) { 
    StringBuilder sb = new StringBuilder();
    try {
        mylist = DataModel.getQ1Songs(msg, msg1);                                                // call the function to parse everything once
       
     } catch (Exception e) {
         System.out.println("error when parsing");
     }
     for (int i =0; i< mylist.get(j).getGenres().size();i ++) {
        generos = sb.append(mylist.get(j).getGenres().get(i)).append(",").toString();
     }

    return generos;
}

public void setMsg(String m, String a) {
     msg=m;
     msg1=a;
}
public String getMsg() {
    return msg;
}
public String getMsg1() {
    return msg1;
}
}
