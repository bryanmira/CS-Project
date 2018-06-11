
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;

public class DayButton extends JButton {
	
	int day; 
	int month;
	int year;
	CalendarModel model;
	final GregorianCalendar curr;
	
	public DayButton(GregorianCalendar cal, CalendarModel mod) {
		this.model = mod;
		curr = cal;
		day=curr.get(Calendar.DAY_OF_MONTH);
		month=curr.get(Calendar.MONTH);
		year=curr.get(Calendar.YEAR);
		this.setText(""+day);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setDay(day);
				model.setCurr(month,day,year);
			}
		});
	}
	

}
