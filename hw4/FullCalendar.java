import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
public class FullCalendar extends JFrame{
	CalendarModel model;
	
	public FullCalendar(GregorianCalendar cal) {
		CalendarModel model = new CalendarModel();
		model.setCurr(cal.get(GregorianCalendar.MONTH),
				cal.get(GregorianCalendar.DATE),cal.get(GregorianCalendar.YEAR));
		MonthView newMonth = new MonthView(model,model.getCurr());
		DayView newDay = new DayView(model,cal);
		model.attach(newDay);
		model.attach(newMonth);
		
		
		
		
		
		JButton prevM = new JButton("Previous Month");
		prevM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setCurr(model.getCurr().get(GregorianCalendar.MONTH)-1,
						model.getCurr().get(GregorianCalendar.DATE),model.getCurr().get(GregorianCalendar.YEAR));

			}
		});
		
		JButton nextM = new JButton("Next Month");
		nextM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setCurr(model.getCurr().get(GregorianCalendar.MONTH)+1,
						model.getCurr().get(GregorianCalendar.DATE),model.getCurr().get(GregorianCalendar.YEAR));

			}
		});
		this.setLayout(new BorderLayout());
		this.add(newMonth, BorderLayout.WEST);
		this.add(newDay, BorderLayout.EAST);
		this.add(prevM, BorderLayout.NORTH);
		this.add(nextM, BorderLayout.SOUTH);
		
        this.setVisible(true);
        this.pack();
	}
}
