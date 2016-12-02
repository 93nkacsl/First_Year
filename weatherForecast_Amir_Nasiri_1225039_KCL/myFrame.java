package weatherForecast_Amir_Nasiri_1225039_KCL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import weatherforecast.FetchWeatherForecast;

/**
 * This class creates the user interface of the weather forecast application.
 * all of the variables in this class have been declared as public and
 * local(except for some JLabel and font variables) so they can be used in other
 * classes.
 * 
 * there are no methods or inner classes in this class.
 * 
 * 
 * @author Amir Nasiri, KCL ID:1225039
 * 
 * @since 20/02/2013
 * @see MainApp
 * @see myListenersandMethods
 */

public class myFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * <b>topPanel:</b> this is the top panel out of the three main panels in my
	 * frame. <br>
	 * There is a JLabel inside the topPanel <br>
	 * <b>centerPanel :</b> this is the center panel out of the three main
	 * panels in my frame. <br>
	 * there are two panels in the centerPanel that are the leftPanel and
	 * rightPanel. the rightPanel has a gridlayout and there are three
	 * JEditorPanes in it. the leftPanel has a gridlayout and it has two panels
	 * in it that are lpTop and lpBottom. The lpTop has a gridlayout and it it
	 * has two panels in it lpTopTop and lpTopBottom. the lpBottom has a
	 * borderLayout and it has a Jlabel, a JList and a Date. and the lpTopTop
	 * has a JLabel a JTextField and a JButton in it. the lpTopBottom has a
	 * JLabel in it.<br>
	 * <b>bottomPanel :</b> this is the bottom panel out of the three main
	 * panels in my frame. there is a JLabel in this panel.
	 * 
	 */

	public JPanel topPanel, centerPanel, bottomPanel, rightPanel, leftPanel,
			lpTop, lpBottom, lpTopTop, lpTopBottom;

	/**
	 * This is the textField in the lpTopTop panel.
	 */

	public JTextField searchCity;
	/**
	 * this is the JLabel in the bottom panel
	 */
	public JLabel bbcLink;
	/**
	 * this is the "search" word in the lpTopTop panel.
	 */
	public JLabel searchWord;
	/**
	 * this is the JList in the lpBottom panel
	 */
	public JList<String> stationOption = new JList<String>();
	/**
	 * this is the JEditorPane in the rightPanel of the centerPanel.
	 */
	public JEditorPane day1txt, day2txt, day3txt;
	/**
	 * this is the JRadioButtton in the lpTopBottom panel
	 */
	public JRadioButton imperial, metric;
	/**
	 * this is the JButton in the lptopTop panel.
	 */
	public JButton searchButton;
	/**
	 * the value of this Integer can only be 1 or 0 and it is used in other
	 * classes
	 */
	public int y = 1;
	/**
	 * this is the the JScrollPane that is used on the JEditorPanes in the
	 * rightPanel of the centerPanel.
	 */
	public JScrollPane day1txtScroll, day2txtScroll, day3txtScroll;

	/**
	 * myFrame class constructor.
	 * this constructor creates a GUI frame.
	 */

	public myFrame() {

		super("Three day weather Forecast By:Amir Nasiri 1225039");
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		/*
		 * this method is to set the focus on my search text field whenever the
		 * program starts to run. it also shows a dialogue after run.
		 */
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				searchCity.requestFocus();
				JOptionPane
						.showMessageDialog(centerPanel,
								"Please make sure that you have access to a HIGH SPEED Internet.");
			}
		});

		// my three main panels top, centre and bottom.

		topPanel = new JPanel();
		centerPanel = new JPanel();
		bottomPanel = new JPanel();

		this.add(topPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		// the content of my top panel.

		JLabel title = new JLabel("Three Day Weather Forecast");
		Font titleFont = new Font("helvetica", Font.PLAIN, 20);
		title.setFont(titleFont);
		title.setHorizontalAlignment(JTextField.CENTER);
		topPanel.add(title);

		// the content of my centre panel.

		centerPanel.setLayout(new GridLayout(1, 2, 20, 30));
		rightPanel = new JPanel();
		leftPanel = new JPanel();
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);

		////// left panel

		GridLayout leftPanelLayout = new GridLayout(2, 1);
		leftPanel.setLayout(leftPanelLayout);
		lpTop = new JPanel();
		lpBottom = new JPanel();

		searchWord = new JLabel("Location:");
		searchCity = new JTextField();
		searchWord.setHorizontalAlignment(JLabel.LEFT);
		searchCity.setHorizontalAlignment(JTextField.LEFT);
		searchCity.setBorder(BorderFactory.createLineBorder(Color.black));
		searchCity.setPreferredSize(new Dimension(100, 30));

		Font panel1Font = new Font("arial", Font.PLAIN, 18);
		searchCity.setFont(panel1Font);
		searchWord.setFont(panel1Font);
		searchWord.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		searchButton = new JButton("Search");
		searchButton.setPreferredSize(new Dimension(85, 30));

		lpTop.setLayout(new GridLayout(2, 1, 10, 10));
		lpTopTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lpTopBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lpTop.add(lpTopTop);
		lpTop.add(lpTopBottom);
		lpTopTop.add(searchWord);
		lpTopTop.add(searchCity);
		lpTopTop.add(searchButton);

		ButtonGroup imperialorMetric = new ButtonGroup();
		imperial = new JRadioButton("Imperial", true);
		metric = new JRadioButton("Metric", false);
		imperialorMetric.add(imperial);
		imperialorMetric.add(metric);
		lpTopBottom.add(imperial);
		lpTopBottom.add(metric);

		//////// left panel bottom

		lpBottom.setLayout(new BorderLayout(20, 5));
		JScrollPane scroll = new JScrollPane(stationOption);
		stationOption.setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel stationWord = new JLabel("Possible weather stations:");
		Font lpanelBottomFont = new Font("arial", Font.PLAIN, 18);
		stationWord.setFont(lpanelBottomFont);
		stationWord.setHorizontalAlignment(JLabel.LEFT);
		lpBottom.add(stationWord, BorderLayout.NORTH);
		lpBottom.add(scroll, BorderLayout.CENTER);

		// optional date setter!!

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		JLabel lpBottomBottom = new JLabel(" Date: " + dateFormat.format(date));
		lpBottomBottom.setFont(panel1Font);

		lpBottom.add(lpBottomBottom, BorderLayout.SOUTH);
		leftPanel.add(lpTop);
		leftPanel.add(lpBottom);

		////// right panel in centre panel

		GridLayout rightPanellayout = new GridLayout(3, 1, 35, 35);
		rightPanel.setLayout(rightPanellayout);
		day1txt = new JEditorPane();
		day1txt.setBorder(BorderFactory.createLineBorder(Color.black));
		day1txt.setEditable(false);
		day1txt.setContentType("text/html");
		day2txt = new JEditorPane();
		day2txt.setBorder(BorderFactory.createLineBorder(Color.black));
		day2txt.setEditable(false);
		day2txt.setContentType("text/html");
		day3txt = new JEditorPane();
		day3txt.setBorder(BorderFactory.createLineBorder(Color.black));
		day3txt.setEditable(false);
		day3txt.setContentType("text/html");
		day1txtScroll = new JScrollPane(day1txt);
		rightPanel.add(day1txtScroll);
		day2txtScroll = new JScrollPane(day2txt);
		rightPanel.add(day2txtScroll);
		day3txtScroll = new JScrollPane(day3txt);
		rightPanel.add(day3txtScroll);

		// bottom panel
		
		bbcLink = new JLabel(FetchWeatherForecast.getAcknowledgement());
		bottomPanel.add(bbcLink);
		bbcLink.setFont(panel1Font);

		this.setVisible(true);

	}

}
