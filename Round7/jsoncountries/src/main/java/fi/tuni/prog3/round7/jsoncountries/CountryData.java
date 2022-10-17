package fi.tuni.prog3.round7.jsoncountries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryData extends Country{
    
    public CountryData(String name, double area, long population, double gdp) {
        super(name, area, population, gdp);
    }
    
    static HashMap<String, HashMap<String, String>> countryData = 
            new HashMap<>();
    
    public static List<Country> readFromJsons(String areaFile, 
            String populationFile, String gdpFile) 
            throws IOException {
        
        List<String> files = Arrays.asList(areaFile, populationFile, gdpFile);
        List<Country> countries = new ArrayList();
        
        for (String file : files) {
            Gson gson = new Gson();
            
            Reader reader = Files.newBufferedReader(Paths.get(file));
            
            
            Map<?, ?> map = gson.fromJson(reader, Map.class);
            Map<?, ?> root = (Map<?, ?>) map.get("Root");
            Map<?, ?> data = (Map<?, ?>) root.get("data");
            List<Map<String, List<Map<String, ?>>>> records = 
                    (List<Map<String, List<Map<String, ?>>>>) 
                    data.get("record");
            
            
            for (var record : records) {
                List<Map<String, ?>> fields = record.get("field");
                
                String country = "";
                String key = "";
                String value = "";
                
                for (var field : fields) {
                    
                    String place = (String) ((Map) 
                            field.get("attributes")).get("name");
                    
                    if (place.equals("Country or Area")) {
                        country = field.get("value").toString();
                    }
                    if (place.equals("Item")) {
                        key = field.get("value").toString();
                    }
                    if (place.equals("Value")) {
                        value = field.get("value").toString();
                    }
                }
                
                switch(key) {
                    case "Surface area (sq. km)":
                        if (countryData.get(country) == null) {
                            HashMap<String, String> areaTmp = new HashMap<>();
                            areaTmp.put("area", value);
                            countryData.put(country, areaTmp);
                        } else {
                         countryData.get(country).put("area", value);
                        }
                    case "Population, total":
                        if (countryData.get(country) == null) {
                            HashMap<String, String> populationTmp = 
                                    new HashMap<>();
                            populationTmp.put("population", value);
                            countryData.put(country, populationTmp);
                        
                        } else {
                            countryData.get(country).put("population", value);
                        }
                    case "GDP (constant 2015 US$)":
                        if (countryData.get(country) == null) {
                            HashMap<String, String> gdpTmp = new HashMap<>();
                            gdpTmp.put("population", value);
                            countryData.put(country, gdpTmp);
                        } else {
                            countryData.get(country).put("gdp", value);
                        }
                    }
            }
            reader.close();
        }
        for (var country : countryData.entrySet()) {
            String name = country.getKey();
            double area = Double.parseDouble(country.getValue().get("area"));
            long population = Long.parseLong(country.getValue().
                    get("population"));
            double gdp = Double.parseDouble(country.getValue().get("gdp"));
            
            Country countryObj = new Country(name, area, population, gdp);
            countries.add(countryObj);
        
        }
        
        return countries;
    }
    
    public static void writeToJson(List<Country> countries, String countryFile) 
            throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        Writer w = new FileWriter(countryFile);
        gson.toJson(countries, w);
        w.flush();
        w.close();
    }
}