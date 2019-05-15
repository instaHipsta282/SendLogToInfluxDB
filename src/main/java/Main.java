import LogParser.LogParser;
import Property.MyProperty;

class Main {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        MyProperty.initProperty();
        LogParser.read();
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
