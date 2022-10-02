import java.util.ArrayList;

public class InterfaceTest {
  public static void main(String args[]) {
    ArrayList<IShapeMetrics> shapes = new ArrayList<>();
    for(String arg : args) {
      String[] splitted = arg.split(" ");
      if(splitted.length == 1) {
        try {
          Circle c = new Circle(Double.parseDouble(splitted[0]));
          shapes.add(c);
        }
        catch(NumberFormatException ex) {
          System.out.println("Not parsable to double: \"" + arg + "\"");
        }
      }
      else if(splitted.length == 2) {
        try {
          Rectangle r = new Rectangle(Double.parseDouble(splitted[0]),
                  Double.parseDouble(splitted[1]));
          shapes.add(r);
        }
        catch(NumberFormatException ex) {
          System.out.println("Not parsable to double: \"" + arg + "\"");
        }
      }
      else {
        System.out
                .println("Invalid number of shape parameters: \"" + arg + "\"");
      }
    }
    int counter = 1;
    for(IShapeMetrics shape : shapes) {
      System.out.print("\n");
      System.out.println("Shape " + counter + ":");
      counter++;
      System.out.println(shape);
      System.out.format("Area of %s: %.2f%n", shape.name(), shape.area());
      System.out.format("Circumference of %s: %.2f%n", shape.name(), shape
              .circumference());
    }
  }

}
