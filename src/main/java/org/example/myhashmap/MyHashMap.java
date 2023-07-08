package org.example.myhashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyHashMap<K, V> {
//    put(Object key, Object value) додає пару ключ + значення
//    remove(Object key) видаляє пару за ключем
//    clear() очищає колекцію
//    size() повертає розмір колекції
//    get(Object key) повертає значення (Object value) за ключем
    private int size;
    private Node<K,V> first;

    public MyHashMap(){
        first = null;
        size = 0;
    }
    public void put(K key,V value){
        Node<K, V> last = new Node<>(null,key,value);
        if (size == 0){
            first = last;
        }else if (containsKey(key)){
            getNode(key).value = value;
        }else {

            Node<K, V> current = first;
            while (true){
                if (current.next == null){
                    current.next = last;
                    return;
                }else {
                    current = current.next;
                }
            }

        }
        size++;
    }
    public void remove(K key){
        if (!containsKey(key)){
            return;
        }
        Node<K, V> toRemove = getNode(key);
        Node<K, V> current = first;
        if (toRemove == first){
            first = first.next;
        }

        if (current.next == toRemove){
            current.next = toRemove.next;
            return;
        }
        while (true){
            if (current.next == null){
                return;
            }
            if (current.next == toRemove){
                current.next = toRemove.next;
                size--;
                return;
            }else {
                current = current.next;
            }
        }
    }
    public void clear(){
        first = null;
        size = 0;
    }
    public int size(){
        return size;
    }
    public V get(K key){
        Node<K,V> e = getNode(key);
        if (e == null){
            throw new IllegalArgumentException();
        }
        return e.value;
    }
    public boolean containsKey(K key){
        if (key == null) {
            return false;
        }
        Node<K, V> current = first;
        while (true){
            if (current.hash == Objects.hash(key) && current.key.equals(key)){
                return true;
            }
                current = current.next;
            if (current == null){
                return false;
            }
        }
    }

    private Node<K,V> getNode(K key){
        if (!containsKey(key)){
         return null;
        }
        Node<K, V> current = first;
        while (true){
            if (current.hash == Objects.hash(key) && current.key.equals(key)){
                return current;
            }
            try{
                current = current.next;
            }catch (NullPointerException npe){
                return null;
            }
        }
    }

    static class Node<K, V>{
        Node<K, V> next;
        final int hash;
         final K key;
         V value;

        public Node(Node next, K key, V value) {
            this.next = next;
            this.key = key;
            this.value = value;
            this.hash = hashcode();
        }
        public K getKey(){
            return key;
        }
        public V getValue(){
            return value;
        }
        @Override
        public String toString() {
            return key + " = " + value;
        }

        public int hashcode(){
            return Objects.hash(key);
        }
        @Override
        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Node) {
                Node<?,?> e = (Node<?,?>)o;
                if (Objects.equals(key, e.getKey()))
                    return true;
            }
            return false;
        }
    }
}