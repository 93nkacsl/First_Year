package Amir_Nasiri_1225039_CW2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import weatherforecast.WeatherLocationIDAndName;

/**
 * This class is for the save button in the first frame.
 * 
 * @author Amir Nasiri 1225039
 * 
 */
public class saveData implements ActionListener {
	/**
	 * This is the save button in the first frame.
	 */
	private JButton save;
	/**
	 * This is the linkedList of the Jlist in the first frame.
	 */
	private LinkedList<WeatherLocationIDAndName> stations;

	/**
	 * this is the class constructor.
	 * 
	 * @param save
	 *            this is the save button in the first frame.
	 * @param stations
	 *            this is the linkedList of the Jlist in the first frame.
	 */
	public saveData(JButton save, LinkedList<WeatherLocationIDAndName> stations) {
		this.save = save;
		this.stations = stations;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.save) {
			if (this.stations.size() == 0) {
				JOptionPane.showMessageDialog(null,
						"There is no data in the list to save!");
			} else {
				JFileChooser fileChooser = new JFileChooser();

				if (JFileChooser.APPROVE_OPTION == fileChooser
						.showSaveDialog(null)) {
					try {
						BufferedWriter myWriter = new BufferedWriter(
								new FileWriter(fileChooser.getSelectedFile()
										+ ".txt"));
						for (int i = 0; i < this.stations.size(); i++) {
							myWriter.write(this.stations.get(i).getLocationID()
									+ "");
							myWriter.newLine();
							myWriter.write(this.stations.get(i)
									.getLocationName());
							myWriter.newLine();
						}

						myWriter.close();
					}

					catch (IOException y) {

						y.printStackTrace();
					}
				}

			}

		}

	}
}
