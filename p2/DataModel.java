//everything is in package p2
package p2;  

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

public class DataModel {
	private final static String initialURL = "http://alberto.gil.webs.uvigo.es/SINT/22-23/muml2001.xml";    //this is the initial URL that we know 
	private final static String commonURL = "http://alberto.gil.webs.uvigo.es/SINT/22-23/";					// this is the common URL between them
	
	private static HashMap <String,Document> mapDocs = new HashMap<String,Document>();						// a map where we save all the parsed documents and their years as a key
	
	private static ArrayList <String> fatalerrorfile = new ArrayList<String>();								// an array where we save  the fatal error files
	private static ArrayList <String> errorfile = new ArrayList<String>();									// an array where we save the error files
	
	public static ArrayList <String> getfatalerrorfile() {													// method that returns the array of the fatal error files
		
		return fatalerrorfile;
	}


	public static ArrayList <String> geterrorfile() {														// method that returns the array of the error files
		System.out.println(errorfile.size());
		return errorfile;
	}

	public static void getinit() throws ParserConfigurationException, SAXException, IOException{			// method to parse all documents 
	LinkedList<String> waitlist = new LinkedList<String>(); 												// an array of the URLs waiting to be parsed
	LinkedList<String> done = new LinkedList<String>(); 													// an array of the URLs already parsed
 	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder docbuilder = dbf.newDocumentBuilder();
    docbuilder.setErrorHandler(null);   																// error handler that calls an exception when parsing a document with a fatal error 
    waitlist.add(initialURL);																				// we add the initial URl to the waitlist 
	
	String serverDoc = null; 																				//string to use to pop the docs that we have saved in the waitlist
    String year = null;																						// String for the year
	
	while(waitlist.isEmpty()==false) {																		// loop to go through all the documents in the waitlist
		NodeList Mumls = null;																				//Nodelist of the mumls that we find inside the xml files
		Document doc = null;	
		int flag = 0;																			// String where we put the parsed doc temporarly
		try {
			serverDoc = waitlist.pop();																		// we pop a file name from the waitlist into the string 
			doc = docbuilder.parse(serverDoc); 																// we parse it  and save it in doc
			done.add(serverDoc); 																			// we add it to the list of already parsed

			 Mumls = doc.getElementsByTagName("MuML");												// we get the Muml 	Nodelist
			NodeList yearr = doc.getElementsByTagName("Year");										//we get the year  		Nodelist
			Element elemyear = (Element)yearr.item(0);												// element of the year nodelist
			 year = elemyear.getTextContent() ;																// we get the actual year			
			// System.out.println(year); 
			 	if (Integer.parseInt(year) < 1980 || Integer.parseInt(year)> 2021){                         // we check if the year is valid 
			//		System.out.println("im here");
						errorfile.add(serverDoc);	
						flag = 1;														// if not add it to the list of errorfiles
			 	}
		}
		 catch (SAXParseException ex) {																		// we catch the exceptions here when parsing
		fatalerrorfile.add(serverDoc);																		// if there is an exception we add it to the fatal error files
		 done.add(serverDoc);
		 flag = 1;
		 continue; 
			}
		mapDocs.put(year, doc);																				//we put the parsed doc and its year in the map 
   
			if (flag == 0 ) {
		
		for (int i = 0; i < Mumls.getLength(); i++) {														// loop to get all muml elements

			String newmumlfound = null;
			String newURLfound = null;
			
			try {
				Element elemmuml = (Element)Mumls.item(i);
				newmumlfound = elemmuml.getTextContent();													// the actual muml string 
				newURLfound = commonURL + newmumlfound;														// new URL is the common URL concatenated with the new muml found
			} catch(Exception ex) {
				continue; 
			}

			if ((done.contains(newURLfound)==false) && (waitlist.contains(newURLfound)==false)) {			// check if we didnt already use that URL or it's in the waitlist 
				waitlist.add(newURLfound); 																	//we add a new URL to the waitlist
			}
		}
	}
	}
	
	}
    public static ArrayList getQ1Countries() throws ParserConfigurationException, SAXException, IOException {
        ArrayList <String> mylist = new ArrayList<String>();									// list to store the countriea

		for (Map.Entry <String, Document> entry : mapDocs.entrySet()) {							// loop to check all the parsed documents
			String key = entry.getKey();														// getting the year
			Document value = entry.getValue();													// getting the document
	
	Node root = value.getDocumentElement();

	NodeList nl = value.getElementsByTagName("Country");								// getting the country Nodelist 
	Element elem;
	String name;

	for (int x = 0; x < nl.getLength(); x++) {													// a loop to get all the countires in a document
	    elem = (Element)nl.item(x);
	    name = elem.getTextContent();															// getting the country's actual text
	    
		if (!mylist.contains(name)){															// check if we already have that country in our list 
		mylist.add(name);																		// otherwise add it to the list 
		System.out.println(name);
		}
	}
	}
	Collections.sort(mylist, new Comparator<String>(){
		public int compare(	String s1, String s2) {												// sort the countries in reverse alphabetical order
			
				return  s2.compareTo(s1);
			}
		});
	return mylist;
    }




	public static ArrayList  getQ1Albums (String country) throws ParserConfigurationException, SAXException, IOException {
	//	InputStream input = new URL ("http://alberto.gil.webs.uvigo.es/SINT/22-23/muml2001.xml").openStream();
		ArrayList <Album> mylist = new ArrayList<Album>(); 											//where we will store our list of albums
		String thisreview = null ;																	// the review of an album 
	
	for (Map.Entry <String, Document> entry : mapDocs.entrySet()) {									// loop to go through all the documents in the map 
		String key = entry.getKey();																// getting the key element which is the year
		Document value = entry.getValue();
		Node root = value.getDocumentElement();
	    int keyy = Integer.parseInt(key);															// getting the integer value of the year
		System.out.println(key);
		if (Integer.parseInt(key)>1980 && Integer.parseInt(key)<2010 ){								//checking if it's between the valid years

		NodeList nl = value.getElementsByTagName("Album");                                //search for albums									
		
		Element elem;
		String name;																			// text of the albums 
		NodeList countries;																		
		NodeList title;
		NodeList performer;
		NodeList Groupp;
		
	    String thiscountry;
		String thistitle;
		String thisperformer;
		String thisgroup;
		String thisaid;
		
		 for (int x = 0; x < nl.getLength(); x++) {        // loop to go through all albums     
			elem = (Element)nl.item(x);            // get the element of albums 
			Node albumnode = nl.item(x);			// get one album Node
			
			countries = elem.getElementsByTagName("Country");   // get the nodelist of countries
			title = elem.getElementsByTagName("Name");			 // get the nodelist of title
			performer = elem.getElementsByTagName("Singer");		 // get the nodelist of performer
			Groupp = elem.getElementsByTagName("Group");  		 // get the nodelist of groupp
			name = elem.getTextContent();                         // get the text of albums 
			NodeList listofnodes = albumnode.getChildNodes();    // get all the childnodes of an album
			
			for(int j=0; j< listofnodes.getLength();j++) { 		// loop to go through all the childnodes 

				Node reviewcandidate = listofnodes.item(j);     // Node of our potential review

				if (reviewcandidate.getNodeType() == Node.TEXT_NODE && reviewcandidate.getTextContent().length() > 7){  // check if the Node is a text_node type 
					thisreview = reviewcandidate.getTextContent().trim();   // if it is it means it's our review so we save it in the review string
					break;
				}
			}	
			Element elemCountryName = (Element) countries.item(0);   // get the element of countries
			Element elemtitle = (Element) title.item(0);
			Element elemperformer = (Element) performer.item(0);
			Element elemgroup = (Element) Groupp.item(0);
			thisaid = elem.getAttribute("aid");						//get the aid attribute
            thiscountry = elemCountryName.getTextContent();                 // get the text for countries 
			thistitle = elemtitle.getTextContent();							// get the text for title
			
			if (elemgroup == null) {										// check if there is no group 
			   thisperformer = elemperformer.getTextContent(); 				// if not we get the performer text
				if (country.equals(thiscountry)){  							// we check if it's from the country we were requested to 
				
			         mylist.add(new Album(thisaid, thistitle,thiscountry,keyy,thisperformer,thisreview));    //if it is we add the new Album to the list of albums 
					 }
		 	}
			else {
			thisgroup = elemgroup.getTextContent();							// if there is a group then we get the group
			if (country.equals(thiscountry)){                               // check if the country is the one we are looking for
			
				mylist.add(new Album(thisaid, thistitle,thiscountry,keyy,thisgroup,thisreview)); } //if it is we add the new Album to the list of albums
		}
	}
}
}
	Collections.sort(mylist, new Comparator<Album>(){
		public int compare(Album s1, Album s2) {
			if(s1.getyear() == s2.getyear()){
				return  s1.getTitle().compareTo(s2.getTitle());       // if the year of 2 albums is the same we sort them  alphabetically
			}
			else {
			return Integer.valueOf(s1.getyear()).compareTo(s2.getyear()); }			// if not we sort them ascending 
		}
	} );
		return mylist;
		}



		public static ArrayList  getQ1Songs (String country, String Albumaid ) throws ParserConfigurationException, SAXException, IOException {
		//	InputStream input = new URL ("http://alberto.gil.webs.uvigo.es/SINT/22-23/muml2001.xml").openStream();
			ArrayList <Song> mylist = new ArrayList<Song>();									// list  to store songs
		
		for (Map.Entry <String, Document> entry : mapDocs.entrySet()) {							// loop to go through all the documents in the map 				
			String key = entry.getKey();
			Document value = entry.getValue();
			Node root = value.getDocumentElement();
			
		    if (Integer.parseInt(key)>1980 && Integer.parseInt(key)<2010 ){ 					// check if the year is valid 
		
			NodeList nl = value.getElementsByTagName("Album");                        //search for album
			
			Element elem;
			Element elemsong;
			String name;
			NodeList countries;
			NodeList title;
			NodeList Composer;
			NodeList genre;
			String thiscountry;
			String thistitle;
			String thiscomposer;
			 
			//StringBuilder finalgenre = new StringBuilder() ;
			//String thisgenre;
			String thislang;
			String thisaid;
			 for (int x = 0; x < nl.getLength(); x++) {                         // loop to go through all albums 
				
				elem = (Element)nl.item(x);                                    // get the element of albums 
				countries = elem.getElementsByTagName("Country");			// get the Nodelist of country 
				Element elemCountryName = (Element) countries.item(0);
				thiscountry = elemCountryName.getTextContent(); 				// et the country 
				thisaid = elem.getAttribute("aid");                    	// get the attribute aid of album
		    if (country.equals(thiscountry)){  									// check if it's the right country
					if (thisaid.equals(Albumaid)){								// check if it's the right album
						NodeList songs = elem.getElementsByTagName("Song");		// get the song Nodelist
						for (int i = 0; i < songs.getLength(); i++) {        			// loop to go through all songs 
							ArrayList <String> thisgenre = new ArrayList<String>();		// list to store the genres
							thisgenre.clear(); 											// clear it for each loop 
							
							elemsong = (Element)songs.item(i);   
							title = elemsong.getElementsByTagName("Title");			//get nodelist of title
							Composer = elemsong.getElementsByTagName("Composer");		// get nodelist of composer
							genre = elemsong.getElementsByTagName("Genre");			//get nodelist of genre
						
		                    Element elemtitle = (Element) title.item(0);				// get the elements
							Element elemcomposer = (Element) Composer.item(0);
							for (int j =0 ; j<genre.getLength();j++) {						// loop to get all the genres 
							Element elemgenre = (Element) genre.item(j);
							 thisgenre.add(elemgenre.getTextContent());
							 
							// finalgenre.append(elemgenre.getTextContent()).append(","); 
							}
							//thisgenre = finalgenre.toString();
							//finalgenre.delete(0, 20);
							//System.out.println(thisgenre);
							thislang = elemsong.getAttribute("lang");					// get the lan attribute
            				thiscomposer = elemcomposer.getTextContent();  					// get the text 
							               // get the text for countries 
							thistitle = elemtitle.getTextContent();  
						for(int aa= 0; aa< thisgenre.size() ;aa++ ){ 						// loop to check all the genres for pop 
							System.out.println(thisgenre.get(aa));
							if(thisgenre.get(aa).equals("Pop") ){ 				// if pop is on the genres add it 
						 mylist.add(new Song(thistitle,thiscountry,thiscomposer,thislang,thisgenre));
							}
						else{
							continue;
						}
						}
					
					    }
						
					}
			}
		}
	}
			}
			
			Collections.sort(mylist, new Comparator<Song>(){
				public int compare(Song s1, Song s2) {
				//	if( s1.getGenres().size()< s2.getGenres().size()){
				//		int a = 0;
				//		return  a ;
				//	}
					 if ( s1.getGenres().size() == s2.getGenres().size()) {              // if they have the same numbre of genres sort them alphabetically
						
					return  s1.getTitle().compareTo(s2.getTitle()); 
				}
					else {																// else sort them ascending in terms of amount of genres they have 

						int a = s1.getGenres().toString().length();
						int b = s2.getGenres().toString().length(); 
						
						
							 return Integer.compare(a, b) ;
					}
			
			} });
			
			
			return mylist;
		}
    public static void main (String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		
		getinit();
	//	getQ1Songs("USA", "011btr");
    }
}

