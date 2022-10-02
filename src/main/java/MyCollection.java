
import java.util.*;

public class MyCollection<T extends Object> extends LinkedList<T> {

    private Node<T> first; //Ссылка на 1-ый узел в коллекции

    public MyCollection() {
        this.first = null;
    }

    private static class Node<T> {
        //Класс Узел содержащий в себе
        public Node<T> next;//Ссылку на следующий узел
        public Node<T> prev;//Ссылку на предыдущий узел
        public ArrayList<T> value = new ArrayList<>(5);//Массив из 5 элемнтов

        public Node() {
            this(null, null);
        }

        public Node(Node<T> next, Node<T> prev) {
            this.next = next;
            this.prev = prev;
        }

        //Добавить в конец
        public boolean add (T t) {
            //Если элементов уже пять, значит добавить не можем
            if (value.size() >= 5) {
                return false;
            }

            return value.add(t);
        }

        //Удалить с конца
        public boolean remove (T t) {
            return value.remove(t);
        }
    }

    private class IteratorLinked implements Iterator<T> {
        //Класс итератор по нашей коллекци
        private int currentIndex = 0;//Текущий индекс

        @Override
        public boolean hasNext() {
            //Функция проверяет есть ли следующий элемент
            Node<T> temp = first;

            int size = 0;
            // Ищем нужный узел
            while (temp != null) {
                size += temp.value.size();

                if (size > currentIndex) {
                    break;
                }

                temp = temp.next;
            }

            if (temp == null) {
                return false;
            }

            size -= temp.value.size();
            int index = currentIndex - size;

            //Проверяем есть ли такое значение
            return temp.value.get(index) != null;
        }

        @Override
        public T next() {
            //Функция достает следующий элемент из коллекции
            int size = 0;
            Node<T> temp = first;
            //Ищем нужный узел
            while (temp != null) {
                size += temp.value.size();

                if (size > currentIndex) {
                    break;
                }

                temp = temp.next;
            }

            if (temp == null) {
                return null;
            }

            size -= temp.value.size();
            int index = currentIndex - size;

            currentIndex++;
            //Возвращаем значение из этого узла
            return temp.value.get(index);
        }
    }

    @Override
    public int size() {
        //Функция рассчитывает размер коллекции
        int size = 0;

        //Обход по всем элементам с помощью итератора
        for (T t : this) {
            size++;
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> temp = first;
        //Обходим по всем узлам
        while (temp != null){
            ArrayList<T> values = temp.value;

            //Проверяем каждый узел на наличие переданного значени
            if (values.contains(o)) {
                return true;
            }

            temp = temp.next;
        }

        //Если значение не нашлось то вернем false
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        //Создаем итератор
        return new IteratorLinked();
    }

    @Override
    public boolean add(Object o) {
        if (first == null) {
            //Если первого узла еше нет то создаем
            first = new Node<T>(null, null);
        }

        //Идем до последнего узла
        Node<T> last = first;
        while (last.next != null) {
            last = last.next;
        }

        //Если при добавлении текущий узел уже заполнен
        // То нудно создать новый
        boolean result = last.add((T)o);
        if (!result) {
            //Создаем новый узел
            Node<T> newNode = new Node<T>(null, last);
            newNode.add((T)o);
            last.next = newNode;
        }

        return true;
    }

    public void shift () {
        //После удаления элемента в массиве какого либо узла
        //Образуется дырка, эта функция делает смещение всех элементов влево
        //Тем самым дырка закроется
        Node<T> temp = first;
        while (temp != null) {
            if (temp.value.size() != 5 && temp.next != null) {
                //Берем первый элемент из след узла
                Node<T> next = temp.next;
                T elem = next.value.get(0);
                //Добавлем в тот в котором дырка
                temp.value.add(elem);
                //Удаляем этот элемент из следующего
                next.value.remove(elem);

                //Если следующий стал пустым то зануляем его
                if (next.value.isEmpty()) {
                    temp.next = null;
                }
            }

            temp = temp.next;
        }
    }

    @Override
    public boolean remove(Object o) {
        Node<T> temp = first;
        //Обходим по всем узлам и удаляем из всех переданный элемент

        boolean result = false;
        while (temp != null) {
            result |= temp.remove((T)o);
            temp = temp.next;
        }

        //После каждог удаления делаем сдвиг элементов
        shift ();

        return result;
    }

    @Override
    public boolean addAll(Collection c) {
        if (first == null) {
            //Если первого узла еше нет то создаем
            first = new Node<T>(null, null);
        }

        //Добавления сразу много элементов

        //Идем в последний узел
        Node<T> last = first;
        while (last.next != null) {
            last = last.next;
        }

        Object[] objects = c.toArray();

        //Дополняем последний узел до конца если там есть место
        int size = last.value.size();
        int i = 0;
        for (; i < 5 - size; i++) {
            last.value.add((T)objects[i]);

            if (i + 1 == objects.length) {
                break;
            }
        }

        //Если в переданной коллекции еще есть элементы
        if (5 - size < objects.length) {
            //Создаем новый узел с массивом
            Node<T> root = new Node<T>(null, null);

            //Кладем в него 5 следующих элементов
            for (; i < 5 - size + 5; i++) {
                root.value.add((T)objects[i]);
                if (i + 1 == objects.length) {
                    break;
                }
            }

            //Если в переданной коллекции еще есть элементы
            if (i + 1 != objects.length) {
                //Создаем узел
                Node<T> temp = new Node<T>(null, root);
                root.next = temp;

                for (; i < objects.length; i++) {
                    temp.value.add((T)objects[i]);

                    if (temp.value.size() == 5 && i + 1 != objects.length) {
                        Node<T> temp2 = new Node<T>(null, temp);
                        temp.next = temp2;
                        temp = temp2;
                    }
                }
            }

            last.next = root;
            root.prev = last;
        }

        return false;
    }

    @Override
    public void clear() {
        //Зануляем ссылку на первый элемент
        first = null;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        int counter = 0;//Общий размер всех элементов
        int number = 0;//Кол-во узлов

        Node<T> temp = first;
        //Обход по всем узлам и поиск нужного
        while (temp != null) {
            counter += temp.value.size();
            if (counter >= index) {
                break;
            }

            temp = temp.next;
            number++;
        }

        if (temp == null) {
            return null;
        }

        return temp.value.get(index - number * 5);
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        int number = index / 5; // Номер узла
        index = index % 5; // Индекс в узле

        int i = 0;
        Node<T> temp = first;
        //Обход по узлам и поиск нужного
        while (temp != null) {
            if (i == number) {
                //Если нашли нужный
                //Удаляем из него элемент
                T elem = temp.value.remove(index);

                //Сдвигаем коллекцию
                shift ();
                return elem;
            }

            temp = temp.next;
            i++;
        }

        return null;
    }

    @Override
    public void addFirst(T t) {
        //Добавить в начало
        if (first == null) {
            first = new Node<T>(null, null);

            if (first.value.isEmpty()) {
                first.value.add(t);
            } else {
                first.value.add(0, t);
            }
        } else {
            if (first.value.size() >= 5) {
                //Если в стартовом узле нет места то создаем новый перед ним и делаем стартовым его
                Node<T> firstNext = first.next;
                ArrayList<T> firstValue = first.value;

                Node<T> oldFirst = first;
                first = null;

                Node<T> newFirst = new Node<T>(oldFirst, null);
                newFirst.value.add(t);

                oldFirst.prev = newFirst;
                first = newFirst;

                return;
            }

            first.value.add(0, t);
        }
    }

    @Override
    public void addLast(T t) {
        //Добавить в конец
        add(t);
    }

    // метод для использования в addAll, когда MyCollection.addAll(MyCollection)
    @Override
    public Object[] toArray(){
        ArrayList<T> objects = new ArrayList<>();
        for (T t : this) {
            objects.add(t);
        }

        return objects.toArray();
    }
}
