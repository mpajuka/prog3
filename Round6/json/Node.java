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
    System.out.print(sb.toString());
  }

  public void printJson() {
    StringBuilder sb = new StringBuilder();
    int count = 0;
    printJson(this, sb, count);
    System.out.print(sb);
  }

  private void printJson(Node node, StringBuilder sb, int count) {
    String spaces = "  ";

    if (node.isObject()) {
      ObjectNode objNode = (ObjectNode) node;
      sb.append("{").append(NL);
      count++;
      int i = 0;
      for (String name : objNode) {
        sb.append(spaces.repeat(count));
        sb.append("\"");
        sb.append(name).append("\": ");

        if (i < objNode.size() - 1) {
          printJson(objNode.get(name), sb, count);
          i++;
        } else {
          printJson(objNode.get(name), sb, count);;
        }

      }
      count--;
      sb.append(spaces.repeat(count));


      sb.append("}");

      sb.append(NL);



    }
    else if (node.isArray()) {
      ArrayNode arrNode = (ArrayNode) node;
      sb.append("[").append(NL);
      count++;
      int i = 0;
      for (Node aNode : arrNode) {
        sb.append(spaces.repeat(count));
        sb.append("{").append(NL);
        sb.append(spaces.repeat(count + 1));
        sb.append("\"" + "name" + "\"" + ": ");

        if (i == arrNode.size()) {
          printJson(aNode, sb, count);
        } else {
          printJson(aNode, sb, count);
          i++;
        }

        sb.append(spaces.repeat(count));

        sb.append("}");
        if (i < arrNode.size()) {
          sb.append(",");
        }
        sb.append(NL);
      }
      count--;

      sb.append(spaces.repeat(count));
      sb.append("]");
      sb.append(",");
      sb.append(NL);

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
      sb.append(String.format("%s%n", valStr));
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
