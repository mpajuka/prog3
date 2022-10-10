public class Rectangle implements IShapeMetrics {
    private double height;
    private double width;
    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public String toString() {
        return String.format("Rectangle height with height %.2f and width %.2f",
                             height, width);
    }

    @Override
    public String name() {
        return "rectangle";
    }

    @Override
    public double area() {
        return height * width;
    }

    @Override
    public double circumference() {
        return (2*height) + (2*width);
    }
}
