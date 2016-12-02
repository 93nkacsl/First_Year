package Amir_Nasiri_1225039_CW2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;
import weatherforecast.WeatherLocationIDAndName;

/**
 * This class sorts a list of locations according to the distance from the users
 * location.
 * 
 * @author Amir Nasiri 1225039
 */
public class sortList {
	/**
	 * this is the linkedList to sort.
	 */
	private LinkedList<WeatherLocationIDAndName> stations;
	/**
	 * this is the sorted linkedList.
	 */
	private LinkedList<WeatherLocationIDAndName> updatedStations = new LinkedList<WeatherLocationIDAndName>();

	/**
	 * This is the class constructor.
	 * 
	 * @param stations
	 *            this is the linkedList to sort.
	 */
	public sortList(LinkedList<WeatherLocationIDAndName> stations) {
		this.stations = stations;

		String[] myLocationArray = getLocationData().split(
				",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");

		String currentLoclatitude = myLocationArray[7];
		currentLoclatitude = currentLoclatitude.replace("\"", "");
		String currentLoclong = myLocationArray[8];
		currentLoclong = currentLoclong.replace("\"", "");
		double cLocationLat = Double.parseDouble(currentLoclatitude);
		double cLocationLong = Double.parseDouble(currentLoclong);
		ArrayList<Integer> emptyList = new ArrayList<Integer>();
		TreeMap<Double, WeatherLocationIDAndName> myMap = new TreeMap<Double, WeatherLocationIDAndName>();
		for (int i = 0; i < this.stations.size(); i++) {
			try {

				myMap.put(
						getDistance(
								cLocationLat,
								cLocationLong,
								getLatLong(this.stations.get(i)
										.getLatitudeAndLongitude() + "", 1),
								getLatLong(this.stations.get(i)
										.getLatitudeAndLongitude() + "", 2)),
						this.stations.get(i));

			} catch (NumberFormatException e) {
				emptyList.add(i);
			}

		}
		for (WeatherLocationIDAndName value : myMap.values()) {
			updatedStations.add(value);
		}
		if (emptyList.size() > 0) {
			for (int i = 0; i < emptyList.size(); i++) {
				updatedStations.add(this.stations.get(emptyList.get(i)));
			}
		}
		this.stations.clear();
		for (int i = 0; i < updatedStations.size(); i++) {
			this.stations.add(updatedStations.get(i));
		}
		updatedStations.clear();

	}

	/**
	 * this method returns either the latitude or the longtitude of a location.
	 * 
	 * @param input
	 *            this value will decide to either return latitude or
	 *            longtitude.
	 * @param oneOrTwo
	 *            this is either one or another number.it decides to return one
	 *            of latitude or longtitude.
	 * @return
	 */
	public Double getLatLong(String input, int oneOrTwo) {
		input = input.replace("(", "");
		input = input.replace(")", "");
		String[] latOrLong = input.split(",");

		if (oneOrTwo == 1) {
			return Double.parseDouble(latOrLong[0]);
		} else
			return Double.parseDouble(latOrLong[1]);

	}

	/**
	 * this method returns the distance between two different locations.
	 * 
	 * @param lat1
	 *            users current latitude.
	 * @param lon1
	 *            users current longtitude.
	 * @param lat2
	 *            the locations latitude.
	 * @param lon2
	 *            the locations longtitde.
	 * @return the distnce between the users current location and another
	 *         location.
	 */
	public double getDistance(double lat1, double lon1, double lat2, double lon2) {
		double R = 6371;

		double dLat = Math.toRadians((lat2 - lat1));
		double dLon = Math.toRadians((lon2 - lon1));
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2)
				* Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;

		return d;
	}

	/**
	 * This method returns the users current location data.
	 * 
	 * @return location data.
	 */
	public static String getLocationData() {
		String currentLocationData = null;
		try {
			// URL location = new URL("http://freegeoip.net/csv/");
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
