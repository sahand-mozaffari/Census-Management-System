package data;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class DataManager implements Serializable {
	private static DataManager singleton;
	private HashMap<Key, Number> data;

	private DataManager(HashMap<Key, Number> data) {
		this.data = data;
	}

	public static DataManager singleton() {
		if(singleton == null) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("storage"));
				singleton = (DataManager) ois.readObject();
			} catch(IOException e) {
				HashMap<Key, Number> map = CsvParser.parse();
				singleton = new DataManager(map);
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return singleton;
	}

	public Number get(String population, String feature, String country, String year) {
		return data.get(new Key(population, feature, country, year));
	}

	public void put(String population, String feature, String country, String year, Number value) {
		data.put(new Key(population, feature, country, year), value);
		persist();
	}

	private void persist() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("storage"));
			oos.writeObject(this);
		} catch(IOException e) {
			System.err.println("Error writing to file");
			e.printStackTrace();
		}
	}

	public void sort(String population, String feature, String year) {
		String[] countries = new String[]{"WORLD", "More developed regions", "Less developed regions", "Least developed countries", "Less developed regions, excluding least developed countries", "Less developed regions, excluding China", "High-income countries", "Middle-income countries", "Upper-middle-income countries", "Lower-middle-income countries", "Low-income countries", "Sub-Saharan Africa", "AFRICA", "Eastern Africa", "Burundi", "Comoros", "Djibouti", "Eritrea", "Ethiopia", "Kenya", "Madagascar", "Malawi", "Mauritius", "Mayotte", "Mozambique", "R�union", "Rwanda", "Seychelles", "Somalia", "South Sudan", "Uganda", "United Republic of Tanzania", "Zambia", "Zimbabwe", "Middle Africa", "Angola", "Cameroon", "Central African Republic", "Chad", "Congo", "Democratic Republic of the Congo", "Equatorial Guinea", "Gabon", "Sao Tome and Principe", "Northern Africa", "Algeria", "Egypt", "Libya", "Morocco", "Sudan", "Tunisia", "Western Sahara", "Southern Africa", "Botswana", "Lesotho", "Namibia", "South Africa", "Swaziland", "Western Africa", "Benin", "Burkina Faso", "Cabo Verde", "C�te d'Ivoire", "Gambia", "Ghana", "Guinea", "Guinea-Bissau", "Liberia", "Mali", "Mauritania", "Niger", "Nigeria", "Senegal", "Sierra Leone", "Togo", "ASIA", "Eastern Asia", "China", "China, Hong Kong SAR", "China, Macao SAR", "Dem. People's Republic of Korea", "Japan", "Mongolia", "Republic of Korea", "Other non-specified areas", "South-Central Asia", "Central Asia", "Kazakhstan", "Kyrgyzstan", "Tajikistan", "Turkmenistan", "Uzbekistan", "Southern Asia", "Afghanistan", "Bangladesh", "Bhutan", "India", "Iran (Islamic Republic of)", "Maldives", "Nepal", "Pakistan", "Sri Lanka", "South-Eastern Asia", "Brunei Darussalam", "Cambodia", "Indonesia", "Lao People's Democratic Republic", "Malaysia", "Myanmar", "Philippines", "Singapore", "Thailand", "Timor-Leste", "Viet Nam", "Western Asia", "Armenia", "Azerbaijan", "Bahrain", "Cyprus", "Georgia", "Iraq", "Israel", "Jordan", "Kuwait", "Lebanon", "Oman", "Qatar", "Saudi Arabia", "State of Palestine", "Syrian Arab Republic", "Turkey", "United Arab Emirates", "Yemen", "EUROPE", "Eastern Europe", "Belarus", "Bulgaria", "Czech Republic", "Hungary", "Poland", "Republic of Moldova", "Romania", "Russian Federation", "Slovakia", "Ukraine", "Northern Europe", "Channel Islands", "Denmark", "Estonia", "Finland", "Iceland", "Ireland", "Latvia", "Lithuania", "Norway", "Sweden", "United Kingdom", "Southern Europe", "Albania", "Bosnia and Herzegovina", "Croatia", "Greece", "Italy", "Malta", "Montenegro", "Portugal", "Serbia", "Slovenia", "Spain", "TFYR Macedonia", "Western Europe", "Austria", "Belgium", "France", "Germany", "Luxembourg", "Netherlands", "Switzerland", "LATIN AMERICA AND THE CARIBBEAN", "Caribbean", "Antigua and Barbuda", "Aruba", "Bahamas", "Barbados", "Cuba", "Cura�ao", "Dominican Republic", "Grenada", "Guadeloupe", "Haiti", "Jamaica", "Martinique", "Puerto Rico", "Saint Lucia", "Saint Vincent and the Grenadines", "Trinidad and Tobago", "United States Virgin Islands", "Central America", "Belize", "Costa Rica", "El Salvador", "Guatemala", "Honduras", "Mexico", "Nicaragua", "Panama", "South America", "Argentina", "Bolivia (Plurinational State of)", "Brazil", "Chile", "Colombia", "Ecuador", "French Guiana", "Guyana", "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela (Bolivarian Republic of)", "NORTHERN AMERICA", "Canada", "United States of America", "OCEANIA", "Australia/New Zealand", "Australia", "New Zealand", "Melanesia", "Fiji", "New Caledonia", "Papua New Guinea", "Solomon Islands", "Vanuatu", "Micronesia", "Guam", "Kiribati", "Micronesia (Fed. States of)", "Polynesia", "French Polynesia", "Samoa", "Tonga"};
		Number[] country_population = new Number[242];
		String[][] x = new String[241][3]; 
		for(int i = 0; i < 241; i++) {
			for(int j = 0; j < 3; j++) {
				x[i][j] = new String();
			}
		}
		for(int i = 0; i < 241; i++) {
			country_population[i] = get(population, feature, countries[i].toLowerCase(), year);

			x[i][2] = country_population[i] + "";
			x[i][1]=" : ";
			x[i][0] = countries[i];
			if(country_population[i] == null) {
				x[i][2] = "0";
			}
			if(countries[i] == "") {
				x[i][0] = "empty";	
			}
		}

		Arrays.sort(x,
	            Comparator.comparing((String[] entry) -> Double.parseDouble(entry[2]))
	                      .reversed());
		for(int i = 0; i < 241; i++) {
			System.out.print(x[i][0]);
			System.out.print(x[i][1]);
			System.out.print(x[i][2]);
			System.out.println();
		}
	}
}

