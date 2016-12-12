import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by mingdzhang on 12/11/16.
 */
public class Deque<Item> implements Iterable<Item> {
    public Deque()   {// construct an empty deque
        size = 0;
        first = null;
        last = null;
    }
    public boolean isEmpty() {                  // is the deque empty?
        return size==0;
    }
    public int size() {               // return the number of items on the deque
        return size;
    }
    public void addFirst(Item item)  {   // add the item to the front
        if(item==null){
            throw new java.lang.NullPointerException("");
        }
        Node Oldfirst = first;
        first = new Node();
        first.item = item;
        first.pre = null;
        first.next = Oldfirst;
        if(Oldfirst==null){
            last = first;
        }
        else{
            Oldfirst.pre = first;
        }
        size++;

    }
    public void addLast(Item item)         {// add the item to the end
        if(item==null){
            throw new java.lang.NullPointerException("");
        }
        Node Oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.pre = Oldlast;
        if(Oldlast==null){
            first = last;
        }
        else{
            Oldlast.next = last;
        }
        size++;
    }
    public Item removeFirst(){ // remove and return the item from the front
        if(isEmpty()) throw new java.util.NoSuchElementException("");
        Item item = first.item;
        first = first.next;
        if(first==null) last=first;
        else first.pre = null;
        size--;
        return item;
    }
    public Item removeLast(){ // remove and return the item from the end
        if(isEmpty()) throw new java.util.NoSuchElementException("");
        Item item = last.item;
        last = last.pre;
        if(last==null) first=last;
        else last.next = null;
        size--;
        return item;
    }

    public Iterator<Item> iterator() {     // return an iterator over items in order from front to end
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException("no remove");
        }
        public Item next(){
            if(!this.hasNext()) throw new java.util.NoSuchElementException("");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    private Node first;
    private Node last;
    private int size;
    private class Node{
        Item item;
        Node pre;
        Node next;
    }

    public static void main(String[] args)  {// unit testing

    }

}

