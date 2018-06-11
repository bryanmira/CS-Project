import java.util.GregorianCalendar;

public class Event {
	GregorianCalendar date;
	GregorianCalendar dateEnd;
	String title;
	public Event(String title, GregorianCalendar start, GregorianCalendar end) {
		this.date = start;
		this.dateEnd = end;
		this.title = title;
	}
	
	public String toString() {
		return title+" "+date.get(GregorianCalendar.MONTH)+"/"+date.get(GregorianCalendar.DATE)
			+"/"+date.get(GregorianCalendar.YEAR);
	}
	
}
