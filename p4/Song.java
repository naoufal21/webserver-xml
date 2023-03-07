package p4;
import java.util.List;
import java.util.ArrayList;
public class Song {
    private String Title, Country ,Composer, Language;
    private String Genres ; 
	
    public Song (String t, String c, String co, String l, String g) {
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
			public String getGenres () {
			return Genres; }
			public ArrayList <String> getarray () {
				final ArrayList <String> array = new ArrayList<String>();
				String[] parts = Genres.split(",");
				int size = parts.length;
				for (int i=0; i<size;i++) {
					array.add(parts[i]);
				}
				
				return array;
			
			}	
}
