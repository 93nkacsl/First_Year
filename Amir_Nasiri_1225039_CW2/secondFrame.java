package Amir_Nasiri_1225039_CW2;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import weatherforecast.FetchWeatherForecast;
import weatherforecast.WeatherLocationIDAndName;

/**
 * This is the seacondFrame class, this class is used to search for weather
 * stations(locations).It contains a jlist, a textfield, two JRadio buttons and
 * a JButton.
 * 
 * @author Amir Nasiri 1225039
 * 
 */
@SuppressWarnings("serial")
public class secondFrame extends JFrame {
	/**
	 * This is the ok button in the secondFrame.
	 */
	private JButton okButton;
	/**
	 * This is the textfield in the secondFrame.
	 */
	private JTextField search;
	/**
	 * This is the radio button in the second frame.
	 */
	private JRadioButton normalLocation, exactLocation;
	/**
	 * The JList in the second frame
	 */
	private JList<String> sFrameJList;
	/**
	 * This is the default list model of the second frames JList.
	 */
	private DefaultListModel<String> sFrameListModel;
	/**
	 * This is the DefaultListModel of the JList of the first frame favorite.
	 */
	private DefaultListModel<String> sFrameFavListModel = new DefaultListModel<String>();
	/**
	 * This is the stations linked list. from the second frame.
	 */
	private LinkedList<WeatherLocationIDAndName> sFrameStations;
	/**
	 * This is the linkedlist used for the favourite list of the first frame.
	 */
	private LinkedList<WeatherLocationIDAndName> sFrameFavStations = new LinkedList<WeatherLocationIDAndName>();

	/**
	 * class constructor. it contains the action listeners on the components of
	 * the frame.
	 */
	public secondFrame() {
		super("Add Location");
		guiMaker();

		search.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					search.setForeground(Color.black);
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					search.setForeground(Color.BLACK);
					String location = search.getText();
					sFrameListModel.clear();
					sFrameStations = FetchWeatherForecast
							.findWeatherStationsNearTo(location);
					if (sFrameStations.size() == 0) {
						search.setForeground(Color.red);
						JOptionPane
								.showMessageDialog(null,
										"Please search again there was no location for the input!");
					} else {
						search.setForeground(Color.GREEN);
					}

					new sortList(sFrameStations);
					for (int i = 0; i < sFrameStations.size(); i++) {
						sFrameListModel.add(i, sFrameStations.get(i)
								.getLocationName());
					}
				}

			}
		});
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == okButton) {

					try {
						search.setForeground(Color.black);
						sFrameFavListModel.add(sFrameFavListModel.size(),
								sFrameListModel.get(sFrameJList
										.getSelectedIndex()));

						sFrameFavStations.add(sFrameStations.get(sFrameJList
								.getSelectedIndex()));

						setVisible(false);
					} catch (IndexOutOfBoundsException e1) {
						JOptionPane.showMessageDialog(null,
								"No location was selected please try again! ");
					}
				}

			}
		});

		normalLocation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == normalLocation) {
					sFrameListModel.clear();
					search.setText("");
					search.setEditable(true);

				}

			}
		});

		exactLocation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == exactLocation) {
					search.setEditable(false);
					search.setText("");
					String[] locationData = getLocationData().split(
							",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");

					locationData[5] = locationData[5].replace("\"", "");
					sFrameStations = FetchWeatherForecast
							.findWeatherStationsNearTo(locationData[5]);
					new sortList(sFrameStations);
					sFrameListModel.clear();
					for (int i = 0; i < sFrameStations.size(); i++) {
						sFrameListModel.add(i, sFrameStations.get(i)
								.getLocationName());
					}

				}

			}
		});

	}

	/**
	 * this method sets the components of this class into a frame.
	 */
	public void guiMaker() {
		this.setSize(300, 300);
		this.setLocation(50, 200);

		this.setLayout(new BorderLayout(15, 15));
		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 1, 15, 15));
		JPanel southPanel = new JPanel();
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

		// north panel
		northPanel.setLayout(new GridLayout(1, 2));
		okButton = new JButton(" ok ");
		search = new JTextField();

		northPanel.add(search);
		northPanel.add(okButton);

		// center panel
		sFrameListModel = new DefaultListModel<String>();
		sFrameJList = new JList<String>(sFrameListModel);

		JScrollPane scrollPane = new JScrollPane(sFrameJList);
		centerPanel.add(scrollPane);
		normalLocation = new JRadioButton("Enter search term", true);
		exactLocation = new JRadioButton("Use current location");
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(normalLocation);
		buttonGroup.add(exactLocation);
		southPanel.add(normalLocation, BorderLayout.SOUTH);
		southPanel.add(exactLocation, BorderLayout.SOUTH);

	}

	/**
	 * This methods returns a DefaultListModel
	 * 
	 * @return it returns the sFrameFavStations model.
	 */
	public DefaultListModel<String> getFavListModel() {
		return sFrameFavListModel;
	}

	/**
	 * This method returns a linkedList
	 * 
	 * @return it returns sFrameFavStations
	 */
	public LinkedList<WeatherLocationIDAndName> getSFFavStations() {
		return sFrameFavStations;
	}

	/**
	 * This method returns the data of the users current location
	 * 
	 * @return the data as a String.
	 */
	public static String getLocationData() {
		String currentLocationData = null;
		try {
			URL location = new URL(
					"http://www.inf.kcl.ac.uk/staff/andrew/freegeoip.php");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					location.openStream()));
			currentLocationData = in.readLine();
			in.close();
		} catch (java.net.MalformedURLException e) {
			System.err.println("Cannot open URL for location data");
		} catch (IOException e) {
			System.err.println("IO error whilst fetching location data");
		}
		return currentLocationData;
	}

}
