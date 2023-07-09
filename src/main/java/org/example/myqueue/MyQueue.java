package org.example.myqueue;

import java.util.Arrays;
import java.util.Iterator;

public class MyQueue<T> implements Iterable<T>{
//    add(Object value) додає елемент в кінець
//    clear() очищає колекцію
//    size() повертає розмір колекції
//    peek() повертає перший елемент з черги
//    poll() повертає перший елемент з черги і видаляє його з колекції

    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private Object[] arrayList;
    private int first = 0;

    private int currentSize;
    public MyQueue() {
        this.arrayList = EMPTY_ELEMENT_DATA;
        this.currentSize = arrayList.length;
    }

    public MyQueue(T[] startArray) {
        this.arrayList = Arrays.copyOf(startArray,startArray.length);
        this.currentSize = arrayList.length;
    }

    private void add(T e, Object[] elementData, int size) {
        if (size == elementData.length){
            arrayList = Arrays.copyOf(elementData,elementData.length+1);
        }
        arrayList[size] = e;
        currentSize = size + 1;
    }

    public void add(T value) {
            add(value, arrayList, currentSize);
    }
    public T poll(){
        if (arrayList.length==0){
            throw new ArrayIndexOutOfBoundsException();
        }
        T temp = (T) arrayList[first];
        Object[] newArrayList = new Object[arrayList.length-1];
        for (int i = 0;i < newArrayList.length;i++){
            newArrayList[i] = arrayList[i+1];
        }
        arrayList = newArrayList;
        return temp;
    }
    public T peek() {
        return (T) arrayList[first];
    }
    public void clear(){
        arrayList = Arrays.copyOf(EMPTY_ELEMENT_DATA,EMPTY_ELEMENT_DATA.length);
    }
    public int size(){
        return currentSize;
    }



    public Iterator<T> iterator() {
        return  new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && arrayList[currentIndex] != null;
            }

            @Override
            public T next() {
                return (T) arrayList[currentIndex++];
            }
        };
    }
}

