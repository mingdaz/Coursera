import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by mingdzhang on 12/11/16.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    public RandomizedQueue(){// construct an empty randomized queue
        a = (Item[]) new Object[2];
        size = 0;
    }

    private void resize(int N){
        Item[] temp = (Item[]) new Object[N];
        for(int i=0;i<size;i++){
            temp[i] = a[i];
        }
        a = temp;
    }

    public boolean isEmpty(){// is the queue empty?
        return size==0;
    }

    public int size(){// return the number of items on the queue
        return size;
    }

    public void enqueue(Item item){// add the item
        if(item == null) throw new java.lang.NullPointerException("");
        if(size== a.length) resize(2* a.length);
        a[size++] = item;
    }

    public Item dequeue(){// remove and return a random item
        if(size==0) throw new java.util.NoSuchElementException("");
        int index = StdRandom.uniform(size);
        Item item = a[index];
        a[index] = a[--size];
        a[size] = null;
        if(size >0 && size == a.length/4) resize(a.length/2);
        return item;
    }

    public Item sample(){// return (but do not remove) a random item
        if(size==0) throw new java.util.NoSuchElementException("");
        int index = StdRandom.uniform(size);
        return a[index];
    }

    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomArrayIterator implements Iterator<Item> {

        public RandomArrayIterator() {
            rand = (Item[]) new Object[size];
            i = size;
            for(int n=0;n<i;n++){
                rand[n] = a[n];
            }
        }

        private int i;
        private Item[] rand;


        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("");
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException("");
            int index = StdRandom.uniform(i);
            Item item = rand[index];
            rand[index] = rand[--i];
            return item;
        }
    }

    private int size;
    private Item[] a;
    public static void main(String[] args){// unit testing

    }
}