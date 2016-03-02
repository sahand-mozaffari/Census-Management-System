package data;

import java.io.Serializable;

/**
 * Created by sahand on 2/24/16.
 */
class Key implements Serializable {
	String population;
	String feature;
	String country;
	String year;

	public Key(String population, String feature, String country, String year) {
		this.population = population;
		this.feature = feature;
		this.country = country;
		this.year = year;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		Key key = (Key) o;

		if(!year.equals(key.year))
			return false;
		if(!population.equals(key.population))
			return false;
		if(!feature.equals(key.feature))
			return false;
		return country.equals(key.country);
	}

	@Override
	public int hashCode() {
		int result = population.hashCode();
		result = 31 * result + feature.hashCode();
		result = 31 * result + country.hashCode();
		result = 31 * result + year.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "(" + population + ", " + feature + ", " + country + ", " + year + ")";
	}
}
