/**
 * @author Bryan Mira
 * Simple Calendar.
 */
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

public class SimpleCalendar {
	public static void main(String[] args) throws ParseException, FileNotFoundException {
		GregorianCalendar cal = new GregorianCalendar(); // capture today
		FullCalendar cal2 = new FullCalendar(cal);
		
        cal2.setVisible(true);
        cal2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}