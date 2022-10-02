public class IteratorTest {
    public static void main(String[] args) {
        var myCollection = new MyCollection<String>();
        myCollection.add("1");
        myCollection.add("2");
        myCollection.add("3");
        myCollection.add("4");
        myCollection.add("5");
        myCollection.add("6");
        for (String s : myCollection) {
            System.out.println(s);
        }
    }
}
