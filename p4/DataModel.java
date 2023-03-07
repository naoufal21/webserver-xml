 //everything is in package p2
 package p4;  
 import p4.Song;
 import org.xml.sax.SAXException;
 import org.xml.sax.Attributes;
 import javax.xml.parsers.ParserConfigurationException;
 import javax.xml.parsers.SAXParser;
 import javax.xml.parsers.SAXParserFactory;
 import java.io.IOException;
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
 import org.xml.sax.helpers.DefaultHandler;
 
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
	 
	 private static HashMap <String,String> mapDocs = new HashMap<String,String>();						// a map where we save all the parsed documents and their years as a key
	 private static HashMap <String,Document> mapDocss = new HashMap<String,Document>();
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
	  ArrayList <String> years = new ArrayList<String>();	
	  ArrayList <String> mumls = new ArrayList<String>();	
		 
	 LinkedList<String> waitlist = new LinkedList<String>(); 												// an array of the URLs waiting to be parsed
	 LinkedList<String> done = new LinkedList<String>(); 													// an array of the URLs already parsed
	 String year = null;
 
	  
	 SAXParserFactory factory = SAXParserFactory.newInstance();
	 SAXParser saxParser = factory.newSAXParser();
 
 
 
	 DefaultHandler handler = new DefaultHandler(){
		 boolean  blYear = false, blmuml = false ; 
		 
		 public void startElement(String uri, 
		 String localName, String qName, Attributes attributes)
			throws SAXException {
 
		 if (qName.equals("Year")) {
			blYear = true;
		 } if (qName.equals("MuML")) {
			 blmuml = true;
		  }
	  }
	
	  @Override
	  public void endElement(String uri, 
		 String localName, String qName) throws SAXException {
		 
	  }
	
	  @Override
	  public void characters(char ch[], 
		 int start, int length) throws SAXException {
		 
		 if (blYear) {
		   // System.out.println("Year: "  + new String(ch, start, length));
			 years.add(new String(ch, start, length));
			blYear = false;
			
		 } else if (blmuml) {
		   // System.out.println("muml: "  + new String(ch, start, length));
		   mumls.add(new String(ch, start, length));
			blmuml = false;
		 }
	 
	  }
 };
 
 
 
 
 
																		// error handler that calls an exception when parsing a document with a fatal error 
	 waitlist.add(initialURL);																				// we add the initial URl to the waitlist 
	 
	 String serverDoc = null; 																				//string to use to pop the docs that we have saved in the waitlist
																							 // String for the year
 
	 while(waitlist.isEmpty()==false) {																		// loop to go through all the documents in the waitlist
		 NodeList Mumls = null;																				//Nodelist of the mumls that we find inside the xml files
		 Document doc = null;
		 Document doc1 = null;
		 int flag = 0;	
		 years.clear();
		 mumls.clear();																		// String where we put the parsed doc temporarly
		 try {
			 
			 serverDoc = waitlist.pop();	
			 saxParser.parse(serverDoc, handler);	
															  
																		 // we parse it  and save it in doc
			 done.add(serverDoc); 																			// we add it to the list of already parsed
			 
			 
														 
			  year = years.get(0);														      		// we get the actual year			
			 // System.out.println(year); 
				  if (Integer.parseInt(year) < 1980 || Integer.parseInt(year)> 2021){                         // we check if the year is valid 
			 //		System.out.println("im here");
						 errorfile.add(serverDoc);	
						 done.add(serverDoc);
						 flag = 1;														// if not add it to the list of errorfiles
				  }
		 }
		  catch (SAXParseException ex) {																		// we catch the exceptions here when parsing
		 fatalerrorfile.add(serverDoc);																		// if there is an exception we add it to the fatal error files
		  done.add(serverDoc);
		  flag = 1 ; 
		  continue; 
			 }
			 if (flag ==0) {
 
		 mapDocs.put(year, serverDoc);																				//we put the parsed doc and its year in the map 
 
		 for (int i = 0; i < mumls.size(); i++) {														// loop to get all muml elements
 
			 String newmumlfound = null;
			 String newURLfound = null;
			 
			 try {
				 
				 newmumlfound = mumls.get(i);													// the actual muml string 
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
 
		 for (Map.Entry <String, String> entry : mapDocs.entrySet()) {							// loop to check all the parsed documents
			 String key = entry.getKey();														// getting the year
			 String value = entry.getValue();													// getting the document
	 
			 SAXParserFactory factory = SAXParserFactory.newInstance();
			 SAXParser saxParser = factory.newSAXParser();
		 
		 
		 
			 DefaultHandler countryhandler = new DefaultHandler(){
				 boolean  blcountry = false ; 
				 
				 public void startElement(String uri, 
				 String localName, String qName, Attributes attributes)
					throws SAXException {
		 
				 if (qName.equals("Country")) {
					blcountry = true;
				 } 
			  }
			
			  @Override
			  public void endElement(String uri, 
				 String localName, String qName) throws SAXException {
				 
			  }
			
			  @Override
			  public void characters(char ch[], 
				 int start, int length) throws SAXException {
				 
				 if (blcountry) {
				   String name = new String(ch, start, length);
					 
					 if (!mylist.contains(name)){															// check if we already have that country in our list 
						 mylist.add(name);																		// otherwise add it to the list 
						 
						 }
					blcountry = false;
					
				 } 
			 
			  }
		 };
	 
 
		 saxParser.parse(value, countryhandler);
 
 
 
	 
	 
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
	 
	 for (Map.Entry <String, String> entry : mapDocs.entrySet()) {									// loop to go through all the documents in the map 
		 String key = entry.getKey();																// getting the key element which is the year
		 String value = entry.getValue();
		 
		 int keyy = Integer.parseInt(key);															// getting the integer value of the year
		 
		 if (Integer.parseInt(key)>1980 && Integer.parseInt(key)<2010 ){								//checking if it's between the valid years
		 SAXParserFactory factory = SAXParserFactory.newInstance();
			 SAXParser saxParser = factory.newSAXParser();
		 
		 
		 
			 DefaultHandler albumhandler = new DefaultHandler(){
				 boolean  blalbum = false, blcountry = false, blname = false, blsinger = false, blgroup=false,blaid = false ; 
				 int flag = 0;
				 int flagsing = 0;
				 String aid;
				 String thisreview;
				 ArrayList <String> countries = new ArrayList<String>();	
				  ArrayList <String> names = new ArrayList<String>();
				  ArrayList <String> singers = new ArrayList<String>();	
				  ArrayList <String> groups = new ArrayList<String>();
				 
				 public void startElement(String uri, 
				 String localName, String qName, Attributes attributes)
					throws SAXException {
		 
				 if (qName.equals("Album")) {
					 flag = 1;
				  aid = attributes.getValue("aid");
			 
				 } 
				 if (qName.equals("Country")) {
					 
					 blcountry = true;
				  } 
				  if (qName.equals("Name")) {
					 
					 blname = true;
				  } 
				  if (qName.equals("Singer")) {
					 flagsing =1;
					 blsinger = true;
				  } 
				  if (qName.equals("Group")) {
					 
					 blgroup = true;
				  } 
			  }
			
			  @Override
			  public void endElement(String uri, 
				 String localName, String qName) throws SAXException {
					 if (flag==1) {
					 if (qName.equals("Album")) {
						 
						 if(flagsing == 1) {
							 mylist.add(new Album(aid, names.get(0) ,countries.get(0),keyy,singers.get(0),thisreview));
							 
							 countries.clear();	
							   names.clear();
							  singers.clear();
							   groups.clear();
							 flag = 0;
							 flagsing=0;
						 }
						 else {
							 
							 mylist.add(new Album(aid, names.get(0) ,countries.get(0),keyy,groups.get(0),thisreview));
							 
							 countries.clear();	
							   names.clear();
							  singers.clear();	
							   groups.clear();
								
						 flag = 0;
						 }
					 }
					 }
					 else if (flag == 0 ){
						 if (qName.equals("Album")) { 
							 
							 
							 countries.clear();	
							   names.clear();
							  singers.clear();	
							   groups.clear();
							 
							 flag =0;
							 flagsing=0;
						 }
					 }
			  }
			
			  @Override
			  public void characters(char ch[], 
				 int start, int length) throws SAXException {
				 
				 if (flag == 1) {
					 if (blcountry) {
						 if (!country.equals(new String(ch, start, length))) {
						 
							 
							 flag = 0;
							 blcountry=false;
						 }
					 else {
						 countries.add(new String(ch, start, length));
						 
						 blcountry=false;
					 }
					 }
					 if (blname) {
						 names.add(new String(ch, start, length));
						 
						 blname = false;
					 }
					 if(blsinger) {
						 
						 singers.add(new String(ch, start, length));
						 
						 blsinger=false;
						 
					 }
					 if(blgroup) {
						 groups.add(new String(ch, start, length));
						 
						 blgroup=false;
					 }
					 else {
						 if (Character.isWhitespace(new String(ch, start, length).charAt(0))) {
							 if(new String(ch, start, length).length()>8) {
							 thisreview = new String(ch, start, length);
 
							 }
						 }
						 
					 }
					 
						 }
						 else {
							 blname = false;
							 blsinger=false;
							 blgroup=false;
						 }
					 
					 
				 
					
				 } 
			 
			  
		 };
	 
		 saxParser.parse(value, albumhandler);
 
 
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
		 
		 for (Map.Entry <String, String> entry : mapDocs.entrySet()) {							// loop to go through all the documents in the map 				
			 String key = entry.getKey();
			 String value = entry.getValue();
			 
			 
			 if (Integer.parseInt(key)>1980 && Integer.parseInt(key)<2010 ){ 					// check if the year is valid 
				 SAXParserFactory factory = SAXParserFactory.newInstance();
				 SAXParser saxParser = factory.newSAXParser();
				 ArrayList <String> genres = new ArrayList<String>();
			 
			 
				 DefaultHandler songhandler = new DefaultHandler(){
					 boolean  blalbum = false, blcountry = false, blname = false, blgenre = false, blcomposer=false,blaid = false ; 
					 int flag = 0;
					 int flagpop = 0;
					 String aid;
					 String lang;
					 
					  ArrayList <String> titles = new ArrayList<String>();
					  ArrayList <String> genres = new ArrayList<String>();
					  StringBuilder finalgenre = new StringBuilder() ;
					  String thisgenre;
					  ArrayList <String> composers = new ArrayList<String>();
					 
					 public void startElement(String uri, 
					 String localName, String qName, Attributes attributes)
						throws SAXException {
			 
					 if (qName.equals("Album")) {
						 flag = 1;
					  aid = attributes.getValue("aid");
					 blalbum=true;
					 } 
					 if (qName.equals("Country")) {
						 if(flag!=0) {
						 blcountry = true;
						 }
					  } 
					  if (qName.equals("Song")) {
						 
					  lang = attributes.getValue("lang");
					 
					 } 
					  if (qName.equals("Title")) {
						 if(flag!=0) {
						 blname = true;
						 }
					  } 
					  if (qName.equals("Genre")) {
						 if(flag!=0) {
						 
						 blgenre = true;
						 }
					  } 
					  if (qName.equals("Composer")) {
						 if(flag!=0) {
						 blcomposer = true;
						 }
					  } 
				  }
				
				  @Override
				  public void endElement(String uri, 
					 String localName, String qName) throws SAXException {
						 if (flag==1) {
							 if (qName.equals("Song")) {
									 for(int ii =0;ii<genres.size();ii++) {
										 
									 if(genres.get(ii).equals("Pop")) {
										 
									 flagpop =1;
									 }
									 }
								 if(flagpop == 1 ) {
								 for (int iii =0; iii<genres.size(); iii++) {
									 finalgenre.append(genres.get(iii)).append(","); 
 
								 }
								 thisgenre=finalgenre.toString();
 
								 mylist.add(new Song(titles.get(0), country ,composers.get(0),lang,thisgenre));
								 genres.removeAll(genres);
								 finalgenre.delete(0, 20);
								 
								 titles.clear();
								 composers.clear();
								 
								 flagpop =0;
								 }
								 else {
								 titles.clear();
								 composers.clear();
								 genres.removeAll(genres);
									 
								 }
							 }
 
						 if (qName.equals("Album")) {
							 titles.clear();
							 composers.clear();
							 
							 flag=0;
						 }
						 }
						 else if (flag == 0 ){
							 if (qName.equals("Song")) { 
								 
								 titles.clear();
								 composers.clear();
								 
								 flag=0;
							 }
							 if (qName.equals("Album")) { 
								 
								 titles.clear();
								 composers.clear();
								 
								 flag=0;
							 }
						 }
				  }
				
				  @Override
				  public void characters(char ch[], 
					 int start, int length) throws SAXException {
					 
					 if (flag == 1) {
						 if (blalbum) {
							 if (!aid.equals(Albumaid)) {
							 
								 
								 flag = 0;
								 blcountry=false;
								 blalbum=false;
							 }
						 else {
							 blalbum=false;
							 
						 }
						 }
						 if (blcountry) {
							 if (!country.equals(new String(ch, start, length))) {
							 
								 
								 flag = 0;
								 blcountry=false;
							 }
						 else {
							 
							 blcountry=false;
						 }
						 }
						 if (blname) {
							 titles.add(new String(ch, start, length));
							 
							 blname = false;
						 }
						 if(blgenre) {
							 
							 genres.add(new String(ch, start, length));
							 
							 blgenre=false;
							 
						 }
						 if(blcomposer) {
							 composers.add(new String(ch, start, length));
							 
							 blcomposer=false;
						 }
	 
						 
							 }
							 else {
								 blname = false;
								 blgenre=false;
								 blcomposer=false;
								 blcountry=false;
								 blalbum=false;
							 }
						 
						 
					 
						
					 } 
				 
				  
			 };
			 saxParser.parse(value, songhandler);
			 
		 
	 }
			 }
			 
				 Collections.sort(mylist, new Comparator<Song>(){
					 public int compare(Song s1, Song s2) {
					 
					  if ( s1.getarray().size() == s2.getarray().size()) {              // if they have the same numbre of genres sort them alphabetically
							 
						 return  s1.getTitle().compareTo(s2.getTitle()); 
					 }
						 else {																// else sort them ascending in terms of amount of genres they have 
	 
							 int a = s1.getarray().toString().length();
							 int b = s2.getarray().toString().length(); 
							 
							 
								  return Integer.compare(a, b) ;
						 }
				 
				 } });
			 
			 
			 return mylist;
		 }
	 public static void main (String[] args) throws ParserConfigurationException, SAXException, IOException {
		 
		 
		 getinit();
	 
		getQ1Albums("USA");
	 }
 }
 
 