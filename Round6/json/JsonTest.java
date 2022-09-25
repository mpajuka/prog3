import java.util.List;
import java.util.Map;

public class JsonTest {
  private static Node test1() {
    ObjectNode rootObj = new ObjectNode();
    rootObj.set("name", new ValueNode("Tampere University"));

    ObjectNode addrObj = new ObjectNode();
    rootObj.set("address", addrObj);
    addrObj.set("city", new ValueNode("Tampere"));
    addrObj.set("country", new ValueNode("Finland"));

    ArrayNode facultyArr = new ArrayNode();
    rootObj.set("faculties", facultyArr);
    for(String faculty : List.of("Faculty of Built Environment",
            "Faculty of Education and Culture",
            "Faculty of Engineering and Natural Sciences",
            "Faculty of Information Technology and Communication Sciences",
            "Faculty of Management and Business",
            "Faculty of Medicine and Health Technology",
            "Faculty of Social Sciences")) {
      facultyArr.add(new ValueNode(faculty));
    }
    
    return rootObj;
  }

  private static Node test2() {
    ObjectNode rootObj = new ObjectNode();
    rootObj.set("name", new ValueNode("Tampere University"));

    ObjectNode addrObj = new ObjectNode();
    rootObj.set("address", addrObj);
    addrObj.set("city", new ValueNode("Tampere"));
    addrObj.set("country", new ValueNode("Finland"));

    ArrayNode facultyArr = new ArrayNode();
    rootObj.set("faculties", facultyArr);
    for(String faculty : List.of("Faculty of Built Environment",
            "Faculty of Education and Culture",
            "Faculty of Engineering and Natural Sciences",
            "Faculty of Information Technology and Communication Sciences",
            "Faculty of Management and Business",
            "Faculty of Medicine and Health Technology",
            "Faculty of Social Sciences")) {
      ObjectNode facultyObj = new ObjectNode();
      facultyArr.add(facultyObj);
      facultyObj.set("name", new ValueNode(faculty));
    }
    
    return rootObj;
  }

  private static Node test3() {
    ObjectNode rootObj = new ObjectNode();
    rootObj.set("firstName", new ValueNode("John"));
    rootObj.set("lastName", new ValueNode("Smith"));
    rootObj.set("isAlive", new ValueNode(true));
    rootObj.set("age", new ValueNode(27));

    ObjectNode addrObj = new ObjectNode();
    rootObj.set("address", addrObj);
    addrObj.set("streetAddress", new ValueNode("21 2nd Street"));
    addrObj.set("city", new ValueNode("New York"));
    addrObj.set("state", new ValueNode("NY"));
    addrObj.set("postalCode", new ValueNode("10021-3100"));

    ArrayNode phoneNumberArr = new ArrayNode();
    rootObj.set("phoneNumbers", phoneNumberArr);
    ObjectNode numberObj1 = new ObjectNode();
    phoneNumberArr.add(numberObj1);
    numberObj1.set("type", new ValueNode("home"));
    numberObj1.set("number", new ValueNode("212 555-1234"));

    ObjectNode numberObj2 = new ObjectNode();
    phoneNumberArr.add(numberObj2);
    numberObj2.set("type", new ValueNode("office"));
    numberObj2.set("number", new ValueNode("646 555-4567"));

    rootObj.set("children", new ArrayNode());
    rootObj.set("spouse", new ValueNode(null));
    
    return rootObj;
  }

  private static Node test4() {
    Map<String, String[]> birthPlaces = Map.of("Roger Federer", new String[]{
      "Basel", "Switzerland"}, "Rafael Nadal",
            new String[]{"Manacor", "Spain"}, "Robin Söderling", new String[]{
              "Tibro", "Sweden"});
    Map<String, Double> heights = Map.of("Roger Federer", 1.85, "Rafael Nadal",
            1.85, "Robin Söderling", 1.93);
    Map<String, Map<String, int[]>> winStatistics = Map.of(
            "Roger Federer", Map.of(
                    "Australian Open", new int[]{2004, 2006, 2007, 2010, 2017,
                                                 2018},
                    "French Open", new int[]{2009},
                    "Wimbledon", new int[]{2003, 2004, 2005, 2006, 2007, 2009,
                                           2012,
                                           2017},
                    "US Open", new int[]{2004, 2005, 2006, 2007, 2008}),
            "Rafael Nadal", Map.of(
                    "Australian Open", new int[]{2009, 2022},
                    "French Open", new int[]{2005, 2006, 2007, 2008, 2010, 2011,
                                             2012, 2013, 2014, 2017, 2018, 2019,
                                             2020},
                    "Wimbledon", new int[]{2008, 2010},
                    "US Open", new int[]{2010, 2013, 2017, 2019}),
            "Robin Söderling", Map.of());

    ArrayNode rootArr = new ArrayNode();
    for(String player : List.of("Rafael Nadal", "Robin Söderling",
            "Roger Federer")) {
      ObjectNode playerObj = new ObjectNode();
      rootArr.add(playerObj);
      playerObj.set("name", new ValueNode(player));
      ObjectNode birthPlaceObj = new ObjectNode();
      playerObj.set("birthPlace", birthPlaceObj);
      birthPlaceObj.set("city", new ValueNode(birthPlaces.get(player)[0]));
      birthPlaceObj.set("country", new ValueNode(birthPlaces.get(player)[1]));
      ObjectNode grandSlamsObj = new ObjectNode();
      playerObj.set("grandSlamTitles", grandSlamsObj);
      var playerSlams = winStatistics.get(player);
      for(String grandSlam : List.of("Australian Open", "French Open",
              "Wimbledon", "US Open")) {
        if(playerSlams.containsKey(grandSlam)) {
          ObjectNode grandSlamObj = new ObjectNode();
          grandSlamsObj.set(grandSlam, grandSlamObj);
          ArrayNode yearArr = new ArrayNode();
          grandSlamObj.set("years", yearArr);
          for(int year : playerSlams.get(grandSlam)) {
            yearArr.add(new ValueNode(year));
          }
        }
      }
    }
    
    return rootArr;
  }

  public static void main(String[] args) {
    Node rootNode;
    switch(args[0]) {
      case "4":
        rootNode = test4();
        break;
      case "3":
        rootNode = test3();
        break;
      case "2":
        rootNode = test2();
        break;
      case "1":
      default:
        rootNode = test1();
    }
    if("json".equals(args[1])) {
      try {
        rootNode.printJson();
      }
      catch(UnsupportedOperationException e) {
        System.out.println(e.getMessage());
      }
    }
    else {
      rootNode.printSimple();
    }
  }
}
