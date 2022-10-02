public class addFirstLastTest {
    public static void main(String[] args) {
        var myCollection = new MyCollection<String>();
        myCollection.add("1");
        myCollection.add("2");
        myCollection.add("3");
        myCollection.add("4");
        myCollection.add("5");
        myCollection.add("6");

        System.out.println("Начальное состояние коллекции " + myCollection);
        System.out.println("--------------");

        myCollection.addFirst("a");
        myCollection.addFirst("b");
        myCollection.addFirst("c");
        myCollection.addFirst("d");
        System.out.println("Cостояние коллекции после применения методов addFirst " + myCollection);
        System.out.println("--------------");

        myCollection.addLast("-a");
        myCollection.addLast("-b");
        myCollection.addLast("-c");
        myCollection.addLast("-d");
        System.out.println("Cостояние коллекции после применения методов addLast " + myCollection);
        System.out.println("--------------");
    }
}
