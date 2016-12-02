package Amir_Nasiri_1225039_CW2;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import weatherforecast.FetchWeatherForecast;

/**
 * This class creates a frame with two JLabels and three JEditorPane's.The
 * weather conditions are shown in this class.
 * 
 * @author Amir Nasiri 1225039
 * 
 */

@SuppressWarnings("serial")
public class thirdFrame extends JFrame {
	/**
	 * JEditorPane of the thirdclass.The weather conditions are set into these.
	 */
	private JEditorPane day1txt, day2txt, day3txt;

	/**
	 * Default class constructor.
	 */
	public thirdFrame() {
		super("Weather Conditions ");
		guiCreater();
	}

	/**
	 * This method sets the components of this class into a frame.
	 */
	public void guiCreater() {

		this.setSize(400, 400);
		this.setLocation(750, 200);

		this.setLayout(new BorderLayout(15, 15));
		// title
		JLabel myTitle = new JLabel("Weather:");
		Font titleFont = new Font("helvetica", Font.BOLD, 15);
		myTitle.setFont(titleFont);

		myTitle.setHorizontalAlignment(JTextField.CENTER);
		this.add(myTitle, BorderLayout.NORTH);
		// center panel
		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new GridLayout(3, 1, 5, 5));
		day1txt = new JEditorPane();
		day1txt.setEditable(false);
		JScrollPane day1txtScroll = new JScrollPane(day1txt);

		day2txt = new JEditorPane();
		JScrollPane day2txtScroll = new JScrollPane(day2txt);
		day3txt = new JEditorPane();
		day2txt.setEditable(false);
		day3txt.setEditable(false);
		day1txt.setContentType("text/html");
		day2txt.setContentType("text/html");
		day3txt.setContentType("text/html");
		JScrollPane day3txtScroll = new JScrollPane(day3txt);
		centerPanel.add(day1txtScroll);

		centerPanel.add(day2txtScroll);
		centerPanel.add(day3txtScroll);
		this.add(centerPanel, BorderLayout.CENTER);
		JLabel bbcLink = new JLabel(FetchWeatherForecast.getAcknowledgement());
		bbcLink.setHorizontalAlignment(JTextField.CENTER);
		this.add(bbcLink, BorderLayout.SOUTH);
	}

	/**
	 * This method returns one of the JEditrPanes based on the parameters.
	 * 
	 * @param i
	 *            it determines which JEditorPane to get.
	 * @return one of the JEditorPane's in the thirdFrame class.
	 */
	public JEditorPane getEditorPAne(int i) {
		if (i == 0) {
			return this.day1txt = day1txt;
		} else if (i == 1) {
			return this.day2txt = day2txt;
		} else {
			return this.day3txt = day3txt;
		}
	}

}
