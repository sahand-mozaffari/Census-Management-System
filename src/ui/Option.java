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
			String country = UserInterface.askString("Choose the country: ").toLowerCase();;
			String year = UserInterface.askString("Choose a year: ");
			Number value = UserInterface.askNumber("Choose a year: ");
			DataManager.singleton().put(population, features, country, year, value);
			System.out.println("Data was updated.");
		}
	},
	Chart{
		@Override
		public String toString() {
			return "Get chart of populations by country";
		}

		@Override
		void trigger() {
			
			String populationMale = CsvParser.populations[0];
			String populationFemale = CsvParser.populations[1];
			String features = CsvParser.features[UserInterface.askStrings("Choose a feature: ", CsvParser.features)];
			String country = UserInterface.askString("Choose the country: ").toLowerCase();
			int start = 2015;
			int end = 2100;
			if(features.equals("ESTIMATES")){
				start = 1950;
				end = 2015;
			}
			Number[] male = new Number[end - start + 1];
			Number[] female = new Number[end - start + 1];
			try{
			for(int i = start; i < end + 1; i ++){
				male[i - start] = DataManager.singleton().get(populationMale, features, country, String.valueOf(i));
				female[i - start] = DataManager.singleton().get(populationFemale, features, country,String.valueOf(i));
			}
			CreateChartHelper.create(features, male, female);
			}catch(NullPointerException e){
				System.err.println("Data not available!");
			}
		}
	};
	

	abstract void trigger();
}
