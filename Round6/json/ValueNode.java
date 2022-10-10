public class ValueNode extends Node {
    private double valueIsDouble;
    private boolean valueIsBoolean;
    private String valueIsString;
    private boolean isDouble = false;
    private boolean isBoolean = false;
    private boolean isString = false;

    public ValueNode(double value) {
        this.valueIsDouble = value;
        this.isDouble = true;
    }
    public ValueNode(boolean value) {
        this.valueIsBoolean = value;
        this.isBoolean = true;
    }
    public ValueNode(String value) {
        if (value != null) {
            this.valueIsString = value;
            this.isString = true;
        }
    }

    public boolean isNumber() {
        return isDouble;
    }

    public boolean isBoolean() {
        return isBoolean;
    }

    public boolean isString() {
        return isString;
    }

    public boolean isNull() {
        return valueIsString == null;
    }

    public double getNumber() {
        return valueIsDouble;
    }

    public boolean getBoolean() {
        return valueIsBoolean;
    }

    public String getString() {
        return valueIsString;
    }

}
