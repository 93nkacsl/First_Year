package Amir_Nasiri_1225039_CW2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import weatherforecast.FetchWeatherForecast;
import weatherforecast.ForecastForOneDay;
import weatherforecast.WeatherLocationIDAndName;

/**
 * This is the myFrame class,it will show the favourite list. It contains a
 * JList, a JLabel and JButtons.
 * 
 * @author Amir Nasiri 1225039
 * 
 */
@SuppressWarnings("serial")
public class myFrame extends JFrame {
	/**
	 * button in the first frame
	 */
	private JButton add, load, remove, save;
	/**
	 * jlist in the first frame
	 */
	private JList<String> myList;
	/**
	 * this is linkedList used to fetch the weather conditions into the
	 * thirdFrame class.
	 */
	private LinkedList<ForecastForOneDay> weatherStatus;
	/**
	 * secondFrame created as an Object
	 */
	private secondFrame sFrame = new secondFrame();
	/**
	 * thirFrame created as an object
	 */
	private thirdFrame tFrame = new thirdFrame();

	/**
	 * class default constructor.it contains it contains action listener for the
	 * buttons.
	 */
	public myFrame() {
		super("Favourite Weather Locations");
		guiMaker();

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == add) {
					sFrame.setVisible(true);

				}

			}
		});
		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == remove) {
					try {
						// sFrame.getFavListModel().remove(myList.getSelectedIndex());
						sFrame.getSFFavStations().remove(
								myList.getSelectedIndex());
						sFrame.getFavListModel().clear();
						for (int i = 0; i < sFrame.getSFFavStations().size(); i++) {
							sFrame.getFavListModel().addElement(
									sFrame.getSFFavStations().get(i)
											.getLocationName());
						}
					} catch (IndexOutOfBoundsException e1) {
						JOptionPane.showMessageDialog(null,
								"No location has been selected!");
					}

				}

			}
		});
		save.addActionListener(new saveData(save, sFrame.getSFFavStations()));
		load.addActionListener(new loadData(load, sFrame.getSFFavStations(),
				sFrame.getFavListModel()));

		myList.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					WeatherLocationIDAndName location = sFrame
							.getSFFavStations().get(myList.getSelectedIndex());
					weatherStatus = FetchWeatherForecast
							.getWeatherForecast(location);
					try {
						setWeather(tFrame.getEditorPAne(0), 0);
						setWeather(tFrame.getEditorPAne(1), 1);
						setWeather(tFrame.getEditorPAne(2), 2);
						ScrollViewSetter(tFrame.getEditorPAne(0));
						ScrollViewSetter(tFrame.getEditorPAne(1));
						ScrollViewSetter(tFrame.getEditorPAne(2));
						tFrame.setVisible(true);
					} catch (NullPointerException we) {
						JOptionPane.showMessageDialog(null,
								"The location did not have any weather data!");
					}

				}

			}
		});

	}

	/**
	 * This method sets the view position of the JEditorPane to position(0,0).
	 * 
	 * @param pane
	 *            this is a JEditorpane.
	 * @return Nothing
	 */
	public void ScrollViewSetter(JEditorPane pane) {
		pane.setCaretPosition(0);
	}

	/**
	 * This method sets the weather data into a JEditorPane of the thirdFrame.
	 * 
	 * @param editorPane
	 *            the editorPane to set the data in.
	 * @param i
	 *            this will determine the day of the weather.
	 */
	public void setWeather(JEditorPane editorPane, int i) {

		tFrame.getEditorPAne(i).setText(
				"<b>" + weatherStatus.get(i).getDayOfWeek() + "</b>"
						+ ": <br> " + "Condition: "
						+ weatherStatus.get(i).getInformation("Conditions")
						+ "<br> Min temp: "
						+ weatherStatus.get(i).getInformation("Min Temp")
						+ "<br> Max temp: "
						+ weatherStatus.get(i).getInformation("Max Temp")
						+ "<br> Wind Speed: "
						+ weatherStatus.get(i).getInformation("Wind Speed")
						+ "<br> Visibility: "
						+ weatherStatus.get(i).getInformation("Visibility")
						+ "<br> Pressure:"
						+ weatherStatus.get(i).getInformation("Pressure")
						+ "<br> Humidity: "
						+ weatherStatus.get(i).getInformation("Humidity")
						+ "<br> Sunrise: "
						+ weatherStatus.get(i).getInformation("Sunrise")
						+ "<br>Sunset: "
						+ weatherStatus.get(i).getInformation("Sunset"));

	}

	/**
	 * this method sets the components of this class into a frame.
	 */
	public void guiMaker() {
		this.setSize(300, 300);
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		// title
		JLabel myTitle = new JLabel("Favourite Locations");
		Font titleFont = new Font("helvetica", Font.BOLD, 15);
		myTitle.setFont(titleFont);
		myTitle.setHorizontalAlignment(JTextField.CENTER);
		this.add(myTitle, BorderLayout.NORTH);

		// centerPanel
		JPanel centerPanel = new JPanel();
		this.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(5, 5));
		add = new JButton("Add");
		save = new JButton("Save");
		load = new JButton("Load");
		remove = new JButton("Remove");
		JPanel centerPanelN = new JPanel();
		JPanel centerPanelS = new JPanel();
		centerPanelN.setLayout(new GridLayout(1, 2));
		centerPanelS.setLayout(new GridLayout(1, 2));
		centerPanel.add(centerPanelN, BorderLayout.NORTH);
		centerPanel.add(centerPanelS, BorderLayout.SOUTH);
		centerPanelN.add(add);
		centerPanelN.add(remove);
		centerPanelS.add(load);
		centerPanelS.add(save);

		myList = new JList<String>(sFrame.getFavListModel());
		JScrollPane scrollPane = new JScrollPane(myList);
		centerPanel.add(scrollPane, FlowLayout.CENTER);
		this.setVisible(true);

	}
}
