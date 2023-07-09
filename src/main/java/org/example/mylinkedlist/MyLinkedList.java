package org.example.mylinkedlist;

import java.util.Iterator;



public class MyLinkedList<T> implements Iterable<T> {
    Node<T> head, tail;
    private int size = 0;

//    add(Object value) додає елемент в кінець
//    remove(int index) видаляє елемент із вказаним індексом
//    clear() очищає колекцію
//    size() повертає розмір колекції
//    get(int index) повертає елемент за індексом


    public void add(T value) {
        Node<T> node =  new Node<>(value,null,null);
        if (head == null){
            head = node;
            tail = node;
        } else {
            Node<T> temp = tail;
            tail.setNext(node);
            tail = node;
            node.setPrev(temp);
        }
        size++;
    }
    public void remove(int index){
        isIndexExist(index);
        unlinck(getNode(index));
        size--;
    }
    private Node<T> getNode(int index){
        isIndexExist(index);
        ListIterator<T> iterator = (ListIterator<T>) iterator();
        int i = 0;
        while (iterator.hasNext()){
            if (i == index){
                return iterator.current;
            }
            iterator.next();
            i++;
        }
        return null;
    }
    private void unlinck(Node<T> x){
        final T element = x.data;
        final Node<T> next = x.next;
        final Node<T> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.data = null;
    }
    public void clear(){
        head=null;
        tail=null;
        size = 0;
    }
    public int size(){
        return size;
    }

    public T get(int index){
        isIndexExist(index);
        ListIterator<T> iterator = (ListIterator<T>) iterator();
            int i = 0;
        while (iterator.hasNext()){
            if (i == index){
                return iterator.current.getData();
            }
            iterator.next();
            i++;
        }
        return null;
    }

    private void isIndexExist(int index) {
        if (index >= size){
            throw new IndexOutOfBoundsException();
        }
    }

    // return Head
    public  Node<T> getHead() {
        return head;
    }

    // return Tail
    public Node<T> getTail() {
        return tail;
    }

    // return Iterator instance
    public Iterator<T> iterator() {
        return new ListIterator<T>(this);
    }


}

class ListIterator<T> implements Iterator<T> {
    Node<T> current;

    // initialize pointer to head of the list for iteration
    public ListIterator(MyLinkedList<T> list) {
        current = list.getHead();
    }

    // returns false if next element does not exist
    public boolean hasNext() {
        return current != null;
    }

    // return current data and update pointer
    public T next() {
        Node<T> data = current;
        current = current.getNext();
        return data.getData();
    }

    // implement if needed
    public void remove() {
        throw new UnsupportedOperationException();
    }
}


class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;
    public Node(T data, Node<T> next,Node<T> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    // Setter getter methods
    public void setData(T data) {
        this.data = data;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public T getData() {
        return data;
    }


    public Node<T> getNext() {
        return next;
    }

    @Override
    public String toString() {
        return (String) this.getData();
    }

    public Node<T> getPrev() {
        return prev;
    }
}
