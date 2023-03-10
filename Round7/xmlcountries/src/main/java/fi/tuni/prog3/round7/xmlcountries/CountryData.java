package fi.tuni.prog3.round7.xmlcountries;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class CountryData extends Country {
    
    
    public CountryData(String name, double area, long population, double gdp) {
        super(name, area, population, gdp);
    }
    
    static HashMap<String, HashMap<String, String>> countryMap = 
            new HashMap<>();
     
    public static List<Country> readFromXmls(String areaFile, 
            String populationFile, String gdpFile) 
            throws JDOMException, IOException {
        
        List<Country> countries = new ArrayList();
        List<String> files = Arrays.asList(areaFile, populationFile, gdpFile);
        
        for (String file : files) {
            SAXBuilder s = new SAXBuilder();
            Document areaDocument = s.build(new File(file));
            
            Element root = areaDocument.getRootElement();
            Element data = root.getChild("data");
            List<Element> records = data.getChildren("record");
            
            for (Element record : records) {
                List<Element> fields = record.getChildren("field");
                
                String name = ""; 
                String value = "";
                String key = "";
                
                for (Element field : fields) {
                    if ("Country or Area".equals(
                            field.getAttributeValue("name"))) {
                        name = field.getText();
                    }
                    if ("Item".equals(field.getAttributeValue("name"))) {
                        key = field.getText();
                    }
                    if ("Value".equals(field.getAttributeValue("name"))) {
                        value = field.getText();
                    }
                }
                
                switch (key) {
                    case "Surface area (sq. km)":
                        if (countryMap.get(name) == null) {
                            HashMap<String, String> tmp = new HashMap<>();
                            tmp.put("area", value);
                            countryMap.put(name, tmp);
                        } 
                        else {
                            countryMap.get(name).put("area", value);
                        }
                        break;
                        
                    case "Population, total":
                        if (countryMap.get(name) == null) {
                            HashMap<String, String> tmp = new HashMap<>();
                            tmp.put("population", value);
                            countryMap.put(name, tmp);
                        } 
                        else {
                            countryMap.get(name).put("population", value);
                        }
                        break;
                        
                    case "GDP (constant 2015 US$)":
                        if (countryMap.get(name) == null) {
                            HashMap<String, String> tmp = new HashMap<>();
                            tmp.put("gdp", value);
                            countryMap.put(name, tmp);
                        } 
                        else {
                            countryMap.get(name).put("gdp", value);
                        }
                        break;
                }
            }
        }
        
        for (Map.Entry<String, HashMap<String, String>> country : 
                countryMap.entrySet())
        {
            String name = country.getKey();
            
            double area = Double.parseDouble(country.getValue().get("area"));
            
            long populationCount = Long.parseLong(country.getValue().
                    get("population"));
            
            double gdp = Double.parseDouble(country.getValue().get("gdp"));
            

            Country newCountry = new Country(name, area, populationCount, gdp);
            countries.add(newCountry);
           
        }
        return countries;
    }
    
    public static void writeToXml(List<Country> countries, String countryFile) 
            throws IOException {
        Document doc = new Document();
        doc.setRootElement(new Element("countries"));

        for (Country countryObj : countries)
        {
            Element country = new Element("country");
            country.addContent(new Element("name").
                    setText(countryObj.getName()));
            
            country.addContent(new Element("area").
                    setText(String.valueOf(countryObj.getArea())));
            
            country.addContent(new Element("population")
                    .setText(String.valueOf(countryObj.getPopulation())));
            
            country.addContent(new Element("gdp").
                    setText(String.valueOf(countryObj.getGdp())));

            doc.getRootElement().addContent(country);
        }

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());

        try (FileOutputStream f = new FileOutputStream(countryFile)) {
            BufferedOutputStream bf = new BufferedOutputStream(f);
            xmlOutputter.output(doc, bf);
        }

    }
}
