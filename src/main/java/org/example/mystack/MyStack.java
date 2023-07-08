package org.example.mystack;

import java.util.Arrays;
import java.util.Iterator;

public class MyStack<T> implements Iterable {    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private Object[] arrayList;
    private int first = -1;

    private int currentSize;
    public MyStack() {
        this.arrayList = EMPTY_ELEMENT_DATA;
        this.currentSize = arrayList.length;
    }

    public MyStack(T[] startArray) {
        this.arrayList = Arrays.copyOf(startArray,startArray.length);
        this.currentSize = arrayList.length;
    }

    private void add(T e, Object[] elementData, int size) {
        if (size == elementData.length)
            arrayList = Arrays.copyOf(elementData,elementData.length+1);;
        arrayList[size] = e;
        currentSize = size + 1;
    }

    public void add(T value) {
        if (value == null){
            throw new NullPointerException();
        }
        try {
            add(value, arrayList, currentSize);
            first++;
        }catch (ClassCastException cce){
            cce.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
    public T poll(){
        T temp = (T) arrayList[first];
        arrayList = Arrays.copyOf(arrayList,arrayList.length-1);
        first--;
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

