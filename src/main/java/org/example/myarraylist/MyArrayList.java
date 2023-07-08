package org.example.myarraylist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
//        add(Object value) додає елемент в кінець
//        remove(int index) видаляє елемент із вказаним індексом
//        clear() очищає колекцію
//        size() повертає розмір колекції
//        get(int index) повертає елемент за індексом

public class MyArrayList<T> implements Iterable<T> {

    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private Object[] arrayList;

    private int currentSize;
    public MyArrayList() {
        this.arrayList = EMPTY_ELEMENT_DATA;
        this.currentSize = arrayList.length;
    }

    public MyArrayList(T[] startArray) {
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
            add((T) value, arrayList, currentSize);
        }catch (ClassCastException cce){
            cce.printStackTrace();
        }
    }
    public void remove(int index){
        final Object[] newArrayList = arrayList;
        final int newSize;
        if ((newSize = currentSize - 1) > index)
            System.arraycopy(newArrayList, index + 1, newArrayList, index, newSize - index);
        newArrayList[currentSize = newSize] = null;
    }
    public void clear(){
        arrayList = EMPTY_ELEMENT_DATA;
    }
    public int size(){
        return currentSize;
    }
    public T get(int index){
        return (T) arrayList[index];
    }

    @Override
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
