import java.util.ArrayList;
import java.util.Iterator;

public class MyStack<E> implements Iterable<E> {
    private final ArrayList<E> myStack;
    private int length;

    public MyStack() {
        myStack = new ArrayList<>();
        updateLength();
    }

    public void push(E e) {
        myStack.add(e);
        updateLength();
    }

    public E pop() {
        if(isEmpty()) {
            return null;
        }

        E top = myStack.get(length - 1);
        myStack.remove(length - 1);
        updateLength();
        return top;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    private void updateLength() {
        length = myStack.size();
    }

    public Iterator<E> iterator() {
        return new MyStackIterator<E> ();
    }

    class MyStackIterator<E> implements Iterator<E> {
        int index;
         public MyStackIterator() {
             index = length-1;
         }

        @Override
        public boolean hasNext() {
            return index > -1;
        }

        @Override
        public E next() {
             E nextItem = (E) myStack.get(index);
             index--;
             return nextItem;
        }
    }
}
