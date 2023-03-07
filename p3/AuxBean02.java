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

public class AuxBean02 { 


public static ArrayList <String> fatalerrorfile = new ArrayList<String>();								// an array where we save  the fatal error files
public static ArrayList <String> errorfile = new ArrayList<String>();	

public void AuxBean02()  { 
 
}

public ArrayList <String> getErrorfile() { 
   
   errorfile = DataModel.geterrorfile();
    return errorfile;
}
public ArrayList <String> getFatalerrorfile() { 
    
    fatalerrorfile = DataModel.getfatalerrorfile();
    return fatalerrorfile;
}

}