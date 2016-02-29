package ui;

import data.CsvParser;
import data.DataManager;

enum Option {
	Exit {
		@Override
		public String toString() {
			return "Exit";
		}

		@Override
		void trigger() {
			System.exit(0);
		}
	},

	GetByKey {
		@Override
		public String toString() {
			return "Read data by population, country, feature and year.";
		}

		@Override
		void trigger() {
			String population = CsvParser.populations[UserInterface.askStrings("Choose the sampling population: ", CsvParser.populations)];
			String features = CsvParser.features[UserInterface.askStrings("Choose a feature: ", CsvParser.features)];
			String country = UserInterface.askString("Choose the country: ").toLowerCase();
			String year = UserInterface.askString("Choose a year: ");
			Number result = DataManager.singleton().get(population, features, country, year);
			if(result != null)
				System.out.println(result);
			else
				System.err.println("Data not available!");
		}
	},

	SetByKey {
		@Override
		public String toString() {
			return "Update data by population, country, feature and year.";
		}

		@Override
		void trigger() {
			String population = CsvParser.populations[UserInterface.askStrings("Choose the sampling population: ", CsvParser.populations)];
			String features = CsvParser.features[UserInterface.askStrings("Choose a feature: ", CsvParser.features)];
			String country = UserInterface.askString("Choose the country: ");
			String year = UserInterface.askString("Choose a year: ");
			Number value = UserInterface.askNumber("Choose a year: ");
			DataManager.singleton().put(population, features, country, year, value);
			System.out.println("Data was updated.");
		}
	};

	abstract void trigger();
}
