package p3;
import java.util.List;
import java.util.ArrayList;
public class Song {
    private String Title, Country ,Composer, Language;
    private ArrayList <String> Genres ; 
    public Song (String t, String c, String co, String l, ArrayList <String> g) {
	Title = t;
	
	Country = c;
	
	Composer= co;
	Language = l;
	Genres = g;
	    }
		    public String getTitle () {
			return Title; }
			public String getComposer () {
			return Composer; }
			public String getCountry () {
			return Country; }
			public String getLang () {
			return Language; }
			public ArrayList <String> getGenres () {
			return Genres; }

}
