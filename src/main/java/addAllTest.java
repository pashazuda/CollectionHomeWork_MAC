import java.util.ArrayList;

public class addAllTest {
    public static void main(String[] args) {
        var my = new MyCollection<String>();
        my.add("1");
        my.add("2");
        my.add("3");
        my.add("4");
        my.add("5");
        my.add("6");

        var my1 = new MyCollection<String>();
        var my2 = new MyCollection<String>();

        var arrayList = new ArrayList<String>();
        arrayList.add("а");
        arrayList.add("б");
        arrayList.add("в");
        arrayList.add("г");
        arrayList.add("д");
        arrayList.add("ж");

        var my3 = new MyCollection<String>();
        my3.add("1а");
        my3.add("2б");
        my3.add("3в");
        my3.add("4г");
        my3.add("5д");
        my3.add("6ж");


        // Добавление в пустую колекцию, коллекции другого типа
        my1.addAll(arrayList);
        System.out.println("Добавление в пустую колекцию, коллекции другого типа");
        System.out.println(my1);
        System.out.println("-------------");

        // Добавление в пустую колекцию, коллекции того же типа типа
        my2.addAll(my);
        System.out.println("Добавление в пустую колекцию, коллекции другого типа");
        System.out.println(my2);
        System.out.println("-------------");

        // Добавление в существующую колекцию, коллекции другого типа
        my.addAll(arrayList);
        System.out.println("Добавление в пустую колекцию, коллекции другого типа");
        System.out.println(my);
        System.out.println("-------------");

        // Добавление в существующую колекцию, коллекции того же типа типа
        my3.addAll(my2);
        System.out.println("Добавление в пустую колекцию, коллекции другого типа");
        System.out.println(my3);
        System.out.println("-------------");
    }
}
