public abstract class Node {
  public boolean isValue() {
    return this instanceof ValueNode;
  }

  public boolean isArray() {
    return this instanceof ArrayNode;
  }

  public boolean isObject() {
    return this instanceof ObjectNode;
  }

  public void printSimple() {
    StringBuilder sb = new StringBuilder();
    printSimple(this, sb);
    System.out.print(sb);
  }

  public void printJson() {
    StringBuilder sb = new StringBuilder();
    int count = 0;
    boolean isLast = true;
    printJson(this, sb, count, isLast);
    System.out.print(sb);
  }

  private void printJson(Node node, StringBuilder sb, int count, boolean isLast) {
    String spaces = "  ";
    if (node.isObject()) {
      ObjectNode objNode = (ObjectNode) node;
      sb.append("{").append(NL);
      count++;
      int i = 1;

      for (String name : objNode) {
        sb.append(spaces.repeat(count));
        sb.append("\"");
        sb.append(name).append("\": ");
        if (i == objNode.size()) {
          printJson(objNode.get(name), sb, count, true);
        }
        else {
          printJson(objNode.get(name), sb, count, false);
          i++;
        }
      }
      count--;
      sb.append(spaces.repeat(count));
      sb.append("}");
      if (isLast == false) {
        sb.append(",");
      }
      sb.append(NL);
    }
    else if (node.isArray()) {
      ArrayNode arrNode = (ArrayNode) node;

      if (arrNode.size() == 0) {
        sb.append("[],").append(NL);
      } else {
        sb.append("[").append(NL);
        count++;

        int i = 1;

        for (Node aNode : arrNode) {
          sb.append(spaces.repeat(count));
          if (i == arrNode.size()) {
            printJson(aNode, sb, count, true);
          }
          else {
            printJson(aNode, sb, count, false);
            i++;
          }
        }


        count--;
        sb.append(spaces.repeat(count));
        sb.append("]");
        if (isLast == false) {
          sb.append(",");
        }
        sb.append(NL);
      }


    }
    else if(node.isValue()) {
      ValueNode valNode = (ValueNode) node;
      String valStr = "null";
      if (valNode.isNumber()) {
        valStr = numberToString(valNode.getNumber());
      }
      else if (valNode.isBoolean()) {
        valStr = Boolean.toString(valNode.getBoolean());
      }
      else if (valNode.isString()) {
        valStr = "\"" + valNode.getString() + "\"";
      }
      if (isLast == false) {
        sb.append(String.format("%s,%n", valStr));
      } else {
        sb.append(String.format("%s%n", valStr));
      }

    }
  }

  private static final String NL = System.lineSeparator();

  private static String numberToString(Double d) {
    String str = Double.toString(d);
    if(str.endsWith(".0")) {
      str = str.substring(0, str.length() - 2);
    }
    return str;
  }

  private void printSimple(Node node, StringBuilder sb) {
    if(node.isObject()) {
      sb.append("ObjectNode").append(NL);
      ObjectNode objNode = (ObjectNode) node;
      for(String name : objNode) {
        sb.append(name).append(": ");
        printSimple(objNode.get(name), sb);
      }
    }
    else if(node.isArray()) {
      sb.append("ArrayNode").append(NL);
      ArrayNode arrNode = (ArrayNode) node;
      for(Node aNode : arrNode) {
        printSimple(aNode, sb);
      }
    }
    else if(node.isValue()) {
      ValueNode valNode = (ValueNode) node;
      String typeStr = "NullValue";
      String valStr = "null";
      if(valNode.isNumber()) {
        typeStr = "NumberValue";
        valStr = numberToString(valNode.getNumber());
      }
      else if(valNode.isBoolean()) {
        typeStr = "BooleanValue";
        valStr = Boolean.toString(valNode.getBoolean());
      }
      else if(valNode.isString()) {
        typeStr = "StringValue";
        valStr = "\"" + valNode.getString() + "\"";
      }
      sb.append(String.format("%s(%s)%n", typeStr, valStr));
    }
  }
}
