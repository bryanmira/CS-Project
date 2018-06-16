import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Ellipse2D;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Bryan Mira
 * Simple Calendar.
 */

public class MonthView extends JPanel implements ChangeListener {
	//GregorianCalendar cal = new GregorianCalendar();
	GregorianCalendar temp;
	CalendarModel model;
	JTextField startField;
	
	
	
	public MonthView(CalendarModel model, GregorianCalendar cal) {
		temp = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		this.model = model;
		setLayout( new GridLayout(0,7) );
		
		SimpleDateFormat fmt = new SimpleDateFormat("MMM");
		fmt.setCalendar(model.getCurr());
		String monthFMT = fmt.format(model.getCurr().getTime());
		
		
		startField = new JTextField(monthFMT);
		startField.setEditable(false);
		this.add(startField);
		
		do {
			DayButton button = new DayButton(temp, model);
			this.add(button);
			
			temp.add(Calendar.DAY_OF_MONTH, 1);
		} while (temp.get(Calendar.MONTH) == cal.get(Calendar.MONTH));
	}
	
	public void stateChanged(ChangeEvent e)
	   {
		SimpleDateFormat fmt = new SimpleDateFormat("MMM");
		fmt.setCalendar(model.getCurr());
		String monthFMT = fmt.format(model.getCurr().getTime());
		
		startField.setText(monthFMT);

	   }

	
	public Dimension getPreferredSize() {
		return new Dimension(300, 300);
	}

}
