package p4;


public class Album {
    private String aid , Name, Country ,Singer, review ;
    private int year;
    public Album (String a, String n, String c, int i , String s, String r) {
	aid = a;
	Name = n;
	Country = c;
	year = i;
	Singer = s;
	review = r;
	    }
	public String getaid () {
	return aid; }
    public String getTitle () {
	return Name; }
    public String getcountry () {
	return Country; }
    public int getyear () {
	return year; }
    public String getperformer () {
	return Singer; }
    public String getreview () {
	return review; }
}
