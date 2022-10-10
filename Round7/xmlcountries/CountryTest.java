import fi.tuni.prog3.round7.xmlcountries.Country;
import fi.tuni.prog3.round7.xmlcountries.CountryData;
import java.util.Collections;
import java.util.List;

public class CountryTest {
  public static void main(String[] args)
          throws Exception {
    String areaFile = args[0];
    String populationFile = args[1];
    String gdpFile = args[2];
    String countryFile = args[3];
    List<Country> countries = CountryData.readFromXmls(areaFile, populationFile,
            gdpFile);
    Collections.sort(countries);
    for(Country country : countries) {
      System.out.println(country);
    }
    CountryData.writeToXml(countries, countryFile);
  }

}
