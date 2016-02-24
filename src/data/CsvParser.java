package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CsvParser {
	public static final String[] populations = new String[]{"WPP2015_POP_F01_2_TOTAL_POPULATION_MALE",
			"WPP2015_POP_F01_3_TOTAL_POPULATION_FEMALE",
			"WPP2015_POP_F02_POPULATION_GROWTH_RATE",
			"WPP2015_POP_F03_RATE_OF_NATURAL_INCREASE",
			"WPP2015_POP_F05_MEDIAN_AGE"};
	public static final String[] features = new String[]{"ESTIMATES", "MEDIUM_VARIANT", "HIGH_VARIANT",
			"LOW_VARIANT", "CONSTANT_FERTILITY", "INSTANT_REPLACEMENT",
			"ZERO_MIGRATION", "CONSTANT_MORTALITY", "NO_CHANGE"};

	public static HashMap<Key, Number> parse() {
		HashMap<Key, Number> map = new HashMap<Key, Number>();
		File prefix = new File("Data/csv/");

		for(String population : populations) {
			for(String feature : features) {
				File file = new File(prefix.getPath() + File.separator + population + "_" + feature + ".csv");

				try {
					Scanner line_sc = new Scanner(file);
					for(int i = 0; i < 16; ++i)
						line_sc.nextLine();
					Scanner sc = new Scanner(line_sc.nextLine());
					sc.useDelimiter(Pattern.compile(";"));
					for(int i = 0; i < 5; ++i)
						sc.next();
					ArrayList<String> years = new ArrayList<String>();
					while(sc.hasNext())
						years.add(sc.next());

					while(line_sc.hasNext()) {
						sc = new Scanner(line_sc.nextLine());
						sc.useDelimiter(Pattern.compile(";"));
						while(sc.hasNext()) {
							sc.next();
							sc.next();
							String country = sc.next().trim().toLowerCase();
							sc.next();
							sc.next();

							for(String year : years) {
								Key key = new Key(population, feature, country, year);
								String str = sc.next().replaceAll(" ", "");
								Number value;
								if(str.contains(".")) {
									value = Double.parseDouble(str);
								} else {
									value = Long.parseLong(str);
								}
								map.put(key, value);
							}
						}
					}
					line_sc.close();
				} catch(FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
}
