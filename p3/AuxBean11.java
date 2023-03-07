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
public class AuxBean11 { 
private String msg;
ArrayList <String> mylist = new ArrayList<String>();	


public void AuxBean11() { 
    
}
public ArrayList <String>  getcountries() { 
    try {
        mylist = DataModel.getQ1Countries();                                                 // call the function to parse everything once
       
     } catch (Exception e) {
         System.out.println("error when parsing");
     }
  
    return mylist;
}
public void setMsg(String m) {
     msg=m;
}
}