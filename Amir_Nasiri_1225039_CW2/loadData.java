package Amir_Nasiri_1225039_CW2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import weatherforecast.WeatherLocationIDAndName;

/**
 * this class is the action listener for the load button in the first frame.
 * 
 * @author Amir Nasiri 1225039
 * 
 */
public class loadData implements ActionListener {
	/**
	 * this is the load button in the first frame.
	 */
	private JButton load;
	/**
	 * this is the favourite stations linkedList.
	 */
	private LinkedList<WeatherLocationIDAndName> stations;
	/**
	 * this is a String variable that is used to temporarily store the data read
	 * from a file.
	 */
	private String location = "";
	/**
	 * this is an arrayList where the data that is red from a file is put into.
	 */
	private ArrayList<String> dataList = new ArrayList<String>();
	/**
	 * this is the listmodel of the favourite jlist on the first frame.
	 */
	private DefaultListModel<String> listModel;
	/**
	 * this variable is used to determine if any data has been loaded into the
	 * jlist or not.
	 */
	private int stationSize = 0;
	/**
	 * this int variable will stop the progrm from printing two JOptionPane
	 * message dialogues.
	 */
	private int y = 0;

	/**
	 * This is the class constructor.
	 * 
	 * @param load
	 *            this is the load button in the first frame.
	 * @param stations
	 *            this is the linkedlist of the favourite stations.
	 * @param listModel
	 *            this is the listmodel of the favourite stations.
	 */
	public loadData(JButton load,
			LinkedList<WeatherLocationIDAndName> stations,
			DefaultListModel<String> listModel) {
		this.load = load;
		this.stations = stations;
		this.listModel = listModel;
		stationSize = this.stations.size();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.load) {
			JFileChooser fileChooser = new JFileChooser();
			int myLoad = fileChooser.showOpenDialog(null);
			try {
				BufferedReader myReader = new BufferedReader(new FileReader(
						fileChooser.getSelectedFile()));
				while ((location = myReader.readLine()) != null) {
					dataList.add(location);
				}

			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "No file was found!");

			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null,
						"No file has been selected!");
			}
		}
		Iterator<String> iterator = dataList.iterator();

		this.listModel.clear();

		if (dataList.size() > 0) {
			while (iterator.hasNext()) {
				try {

					int id = Integer.parseInt(iterator.next());
					String location = iterator.next();
					this.stations
							.add(new WeatherLocationIDAndName(id, location));
				} catch (NumberFormatException ee) {

					JOptionPane
							.showMessageDialog(null,
									"The file does not contain the right type of data!");
					y = 1;
				}

			}
		}
		dataList.clear();

		for (int i = 0; i < this.stations.size(); i++) {
			this.listModel.addElement(this.stations.get(i).getLocationName());
		}

		if (this.stations.size() == stationSize) {
			if (y == 1) {
				JOptionPane
						.showMessageDialog(null,
								"The file was empty, or the content of the file was in the wrong order!");
			}
		}
		if (this.stations.size() > stationSize) {
			JOptionPane.showMessageDialog(null, "Loaded");
		}
	}

}
