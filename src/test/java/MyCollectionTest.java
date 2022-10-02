import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MyCollectionTest {

    private MyCollection<String> myCollection;
    private MyCollection<String> myCollection1;
    private ArrayList<String> arrayList;

    @BeforeEach
    void prepare() {
        myCollection = new MyCollection<String>();
        myCollection.add("1");
        myCollection.add("2");
        myCollection.add("3");
        myCollection.add("4");
        myCollection.add("5");
        myCollection.add("6");
        myCollection1 = new MyCollection<String>();
        arrayList = new ArrayList<String>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
    }

    @Test
    void size_Test() {
        assertEquals(6, myCollection.size());
    }

    @Test
    void isEmpty_Test() {
        assertFalse(myCollection.isEmpty());
    }

    @Test
    void contains_True_Test() {
        assertTrue(myCollection.contains("5"));
    }

    @Test
    void contains_False_Test() {
        assertFalse(myCollection.contains("A"));
    }

    // как в ремувы добавить прроверку равенства коллекций
    @Test
    void removeObject_True_Test() {
        assertTrue(myCollection.remove("5"));
    }

    @Test
    void removeObject_False_Test() {
        assertFalse(myCollection.remove("A"));
    }

    @Test
    void removeIndex_Test() {
        assertEquals("5", myCollection.remove(4));
    }

    @Test
    void removeIndex_ThrowsException_Test() {
        assertThrows(IndexOutOfBoundsException.class, () ->myCollection.remove(12));
    }

    @Test
    void get_Test() {
        assertEquals("5", myCollection.get(4));
    }

    @Test
    void get_ThrowsException_Test() {
        assertThrows(IndexOutOfBoundsException.class, () -> myCollection.get(12));
    }

    @Test
    void clear() {
        myCollection.clear();
        assertTrue(myCollection.isEmpty());
    }
}
