package data;

import java.util.HashMap;

public class DataManager {
	private static DataManager singleton;
	private HashMap<Key, Number> data;

	private DataManager(HashMap<Key, Number> data) {
		this.data = data;
	}

	public static DataManager singleton() {
		if(singleton == null) {
			HashMap<Key, Number> map = CsvParser.parse();
			singleton = new DataManager(map);
		}
		return singleton;
	}

	public Number get(String population, String feature, String country, String year) {
		return data.get(new Key(population, feature, country, year));
	}

	public void put(String population, String feature, String country, String year, Number value) {
		data.put(new Key(population, feature, country, year), value);
	}
}

