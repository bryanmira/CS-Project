/**
 * @author Bryan Mira
 * Simple Calendar.
 */

public class Event {
	String date;
	String title;
	public Event(String title, String start) {
		this.date = start;
		this.title = title;
	}
	
	public String toString() {
		return title+" "+date;
	}
	
}
