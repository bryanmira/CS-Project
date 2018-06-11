

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DayView extends JPanel implements ChangeListener{
	String title;
	GregorianCalendar start;
	GregorianCalendar end;
	CalendarModel model;
	JTextField startField;
	JTextField endField;
	JTextArea eventsL;

	public DayView(CalendarModel model, GregorianCalendar cal) {
		this.model = model;
		start = model.getCurr();
		JTextField textField = new JTextField(10);
		JLabel titleLabel = new JLabel("Title:");

		startField = new JTextField((start.get(GregorianCalendar.MONTH)+1)+"/"+start.get(GregorianCalendar.DATE)
		+"/" + start.get(GregorianCalendar.YEAR), 10);
		startField.setEditable(false);
		JLabel startLabel = new JLabel("Date:");
		
		JLabel endLabel = new JLabel("End Time");
		endField = new JTextField((start.get(GregorianCalendar.MONTH)+1)+"/"+start.get(GregorianCalendar.DATE)
		+"/" + start.get(GregorianCalendar.YEAR), 10);
		
		
		eventsL = new JTextArea(model.toString(),20,20);
		
		this.setLayout(new FlowLayout());
		this.add(titleLabel);
		this.add(textField);

		this.add(startLabel);
		this.add(startField);
		
		this.add(endLabel);
		this.add(endField);
		
		this.add(eventsL);

		JButton submit = new JButton("Create");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				title = textField.getText();
				model.update(new Event(title, start, end));
			}
		});
		this.add(submit);
		
		
	}
	
	public void stateChanged(ChangeEvent e)
	   {
		startField.setText(""+(model.getCurr().get(GregorianCalendar.MONTH)+1)+"/"+model.getCurr().get(GregorianCalendar.DATE)+"/"+model.getCurr().get(GregorianCalendar.YEAR));
		endField.setText(""+(model.getCurr().get(GregorianCalendar.MONTH)+1)+"/"+model.getCurr().get(GregorianCalendar.DATE)+"/"+model.getCurr().get(GregorianCalendar.YEAR));
		
		
		eventsL.setText(model.toString());
	   }
}
