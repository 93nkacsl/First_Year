package weatherForecast_Amir_Nasiri_1225039_KCL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import weatherforecast.FetchWeatherForecast;
import weatherforecast.ForecastForOneDay;
import weatherforecast.WeatherLocationIDAndName;

/**
 * This is the myListenersandMethods class, this class has all the methods and
 * ActionListeners required for the program to run.
 * 
 * @author Amir Nasiri, KCL ID:1225039
 * 
 * 
 * @see myFrame
 * @since 20/02/2013
 * @see MainApp
 */

public class myListenersandMethods {
	/**
	 * this is the linkedList where the possible stations are put into.
	 */
	LinkedList<WeatherLocationIDAndName> stations;
	/**
	 * this is the the location for which the weather is fetched for.
	 */
	WeatherLocationIDAndName location;
	/**
	 * this linked list gets the weather for one day and looping through this
	 * gets the weather for three days.
	 */
	LinkedList<ForecastForOneDay> weatherStatus;
	/**
	 * this is the GUI(myFrame) that has been created as an object.
	 */
	myFrame my = new myFrame();
	/**
	 * this is for storing the locations.
	 */
	String searchWord;

	/**
	 * The myListenerandMethods class constructor.<br>
	 * The first Action Listener in this constructor is for my JButton which is
	 * on the GUI(myFrame).<br>
	 * The second Action Listener is for the JList in the frame. <br>
	 * The third is a Key Listener and does thesame job as the first Action
	 * Listener the fourth and the fifth Action Listeners are for my
	 * JRadioButtons .the sixth listener adds a keyListener to my JList. 
	 * 
	 */

	public myListenersandMethods() {

		my.searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == my.searchButton) {

					if (my.searchCity.getText().length() == 0) {
						JOptionPane
								.showMessageDialog(my.centerPanel,
										"please enter the name of the location into the search box!");
					} else {
						searchWord = spaceRemover(my.searchCity.getText());

						stations = FetchWeatherForecast
								.findWeatherStationsNearTo(searchWord);
						String[] myArray = new String[stations.size()];
						for (int i = 0; i < stations.size(); i++) {
							if (stations.get(i).getLocationName().contains(",")) {
								myArray[i] = stations.get(i).getLocationName();
							}

							my.stationOption.setListData(myArray);
						}
					}

				}
			}
		});

		my.stationOption.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {

					location = stations.get(my.stationOption.getSelectedIndex());

					weatherStatus = FetchWeatherForecast
							.getWeatherForecast(location);
					setWeather(my.day1txt, 0, my.y);

					setWeather(my.day2txt, 1, my.y);

					setWeather(my.day3txt, 2, my.y);

				}

			}

		});

		my.searchCity.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					if (my.searchCity.getText().length() == 0) {
						JOptionPane
								.showMessageDialog(my.centerPanel,
										"please enter the name of the location into the search box!");
					} else {
						searchWord = spaceRemover(my.searchCity.getText());

						stations = FetchWeatherForecast
								.findWeatherStationsNearTo(searchWord);
						String[] myArray = new String[stations.size()];
						for (int i = 0; i < stations.size(); i++) {
							if (stations.get(i).getLocationName().contains(",")) {
								myArray[i] = stations.get(i).getLocationName();
							}

							my.stationOption.setListData(myArray);
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		my.imperial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == my.imperial) {
					my.y = 1;
				} else {
					my.y = 0;
				}
				if (my.stationOption.isSelectionEmpty() == true) {

				} else {
					setWeather(my.day1txt, 0, my.y);

					setWeather(my.day2txt, 1, my.y);

					setWeather(my.day3txt, 2, my.y);

				}

			}
		});
		my.metric.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == my.imperial) {
					my.y = 1;

				} else {
					my.y = 0;
				}
				if (my.stationOption.isSelectionEmpty() == true) {

				} else {
					setWeather(my.day1txt, 0, my.y);

					setWeather(my.day2txt, 1, my.y);

					setWeather(my.day3txt, 2, my.y);

				}

			}

		});
		my.stationOption.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					location = stations.get(my.stationOption.getSelectedIndex());

					weatherStatus = FetchWeatherForecast
							.getWeatherForecast(location);

					setWeather(my.day1txt, 0, my.y);

					setWeather(my.day2txt, 1, my.y);

					setWeather(my.day3txt, 2, my.y);
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					location = stations.get(my.stationOption.getSelectedIndex());

					weatherStatus = FetchWeatherForecast
							.getWeatherForecast(location);
					setWeather(my.day1txt, 0, my.y);

					setWeather(my.day2txt, 1, my.y);

					setWeather(my.day3txt, 2, my.y);
				}

			}
		});
	}

	/**
	 * this method sets the Data(weather Conditions) into a JEditorpane.
	 * 
	 * 
	 * @param day
	 *            this is the JEditorPane that the data will be put into.
	 * @param i
	 *            this would set the day for the dayOfTheWeek String.
	 * @param j
	 *            this is thesame integer as the my.y which used for the methods
	 *            to return the value in either metric or farenheit.
	 * @return nothing.
	 * 
	 * @see my.day1txt
	 * @see my.day2txt
	 * @see my.day3txt
	 * @see my.y
	 */

	public void setWeather(JEditorPane day, int i, int j) {
		String dayOfTheWeek = weatherStatus.get(i).getDayOfWeek();

		day.setText("<b>"
				+ dayOfTheWeek
				+ ": "
				+ weatherStatus.get(i).getInformation("Conditions")
				+ "</b>"
				+ "<br> Min Temp: "
				+ tempColor(imperialMetricSetter(weatherStatus.get(i)
						.getInformation("Min Temp"), j))
				+ "<br> Max Temp: "
				+ tempColor(imperialMetricSetter(weatherStatus.get(i)
						.getInformation("Max Temp"), j))
				+ "<br> Wind Direction: "
				+ weatherStatus.get(i).getInformation("Wind Direction")
				+ "<br> Wind Speed: "
				+ mileKilometer(
						weatherStatus.get(i).getInformation("Wind Speed"), my.y)
				+ "<br> Visibility: "
				+ weatherStatus.get(i).getInformation("Visibility")
				+ "<br> Pressure:"
				+ weatherStatus.get(i).getInformation("Pressure")
				+ "<br> Humidity: "
				+ weatherStatus.get(i).getInformation("Humidity")
				+ "<br> Sunrise: "
				+ weatherStatus.get(i).getInformation("Sunrise")
				+ "<br>Sunset: "
				+ weatherStatus.get(i).getInformation("Sunset")
				+ "<br> Windchill(Min temp): "
				+ windChillCalculate(
						weatherStatus.get(i).getInformation("Min Temp"),
						weatherStatus.get(i).getInformation("Wind Speed"), my.y)
				+ "<br> Wind chill(Max temp): "
				+ windChillCalculate(
						weatherStatus.get(i).getInformation("Max Temp"),
						weatherStatus.get(i).getInformation("Wind Speed"), my.y));

		ScrollViewSetter(my.day1txt);
		ScrollViewSetter(my.day2txt);
		ScrollViewSetter(my.day3txt);
	}

	/**
	 * this method calculates the windchill from the given weather conditions.
	 * 
	 * @param degree
	 *            this is the temperature.
	 * @param windSpeed
	 *            this this is the wind speed.
	 * @param i
	 *            this is an integer and it determines to return the value in
	 *            either metric or imperial.
	 * @return this method returns the windChill.
	 * @see setWeather
	 * 
	 */
	public String windChillCalculate(String degree, String windSpeed, int i) {
		String result = "";
		double windchill = 0;
		String[] myTwoDegrees = degree.split(" ");
		myTwoDegrees[1] = myTwoDegrees[1].replace("(", "");
		String celsius[] = myTwoDegrees[0].split("&");
		int celsiuss = Integer.parseInt(celsius[0]);
		String[] array = windSpeed.split("m");
		String inMile = array[0];
		int intMile = Integer.parseInt(inMile);
		int km = (int) Math.round(intMile * 1.61);

		if (celsiuss <= 10) {
			windchill = 13.12 + (0.6215 * celsiuss)
					- (11.37 * Math.pow(km, 0.16))
					+ (0.3965 * celsiuss * Math.pow(km, 0.16));
			if (i == 1) {
				result = Math.round(windchill * 1.8 + 32) + "&#xB0;F ";
			}

			else if (i == 0) {
				result = Math.round(windchill) + "&#xB0;C";
			}
		} else {
			result = "The temperature is not less than 10&#xB0;C / 50&#xB0;F.";
		}

		return result;

	}

	/**
	 * This method removes the space in a String, this method is created to
	 * avoid errors(for example the city "New York" came out as an error).
	 * 
	 * @param cityName
	 *            This is the given location
	 * @return it returns the cityName without space.
	 */
	public String spaceRemover(String cityName) {
		if (cityName.contains(" ")) {
			cityName = cityName.replace(" ", "");
		}

		return cityName;
	}

	/**
	 * This method gets the temperature and then it divides the celsius and the
	 * fahrenheit part and it return one of them.
	 * 
	 * @param Temp
	 *            this is the given temperature.
	 * @param i
	 *            This is a given number that determines the return value of
	 *            either celsius or fahrenheit.
	 * @return This method returns a String
	 */
	public String imperialMetricSetter(String temp, int i) {
		String[] array = temp.split(" ");
		array[i] = array[i].replace('(', ' ');
		array[i] = array[i].replace(')', ' ');

		return array[i];

	}

	/**
	 * this method converts miles to kilometers
	 * 
	 * @param mile
	 *            this is the windspeed in mile
	 * @param z
	 *            is the my.y value and it determines if the program should
	 *            return the Km/mile version.
	 * @return this method returns the speed in either mile or km
	 * @see my.y
	 */

	public String mileKilometer(String mile, int z) {

		String[] array = mile.split("m");
		String inMile = array[0];
		int intMile = Integer.parseInt(inMile);
		int Km = (int) Math.round(intMile * 1.61);
		String myKm = Integer.toString(Km);

		if (z == 1) {
			return mile;
		} else
			return myKm + " km/h";
	}

	/**
	 * this method sets the view position of the JEditorPane to position(0,0).
	 * 
	 * @param pane
	 *            this is a JEditorpane.
	 * @return Nothing
	 */
	public void ScrollViewSetter(JEditorPane pane) {
		pane.setCaretPosition(0);
	}

	/**
	 * This methods changes the colour of the weather temperature depending on
	 * the temperature value.
	 * 
	 * @param temp
	 *            This is the given weather temperature.
	 * @return This method return the weather coloured and in HTML format.
	 */
	public String tempColor(String temp) {
		String value = "";
		int a = temp.length();
		if (temp.charAt(a - 1) == 'C') {
			String[] array = temp.split("&");
			int celsius = Integer.parseInt(array[0]);
			if (celsius >= 30) {
				value = "<font color=\'red\'>" + temp + " </font>";

			} else if (celsius <= 0) {
				value = "<font color=\'blue\'>" + temp + " </font>";

			} else
				value = temp;

		} else if (temp.charAt(a - 2) == 'F') {

			String[] array = temp.split("&");
			double farenheit = Double.parseDouble(array[0]);
			if (farenheit >= 86) {
				value = "<font color=\'red\'>" + temp + " </font>";

			} else if (farenheit <= 32) {
				value = "<font color=\'blue\'>" + temp + " </font>";

			} else
				return value = temp;
		} else {
			value = temp;
		}
		return value;

	}

}