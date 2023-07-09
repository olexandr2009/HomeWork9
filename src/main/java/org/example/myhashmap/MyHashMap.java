package org.example.myhashmap;

public class MyHashMap<K, V> {
    private int capacity = 16;

    private Node<K, V>[] tableHeads;

    public MyHashMap(){
        tableHeads = new Node[capacity];
    }

    public MyHashMap(int capacity){
        this.capacity = capacity;
        tableHeads = new Node[capacity];
    }
    public void put(K key, V value) {
        int index = index(key);
        Node<K, V> newNode = new Node(key, value, null);
        if (tableHeads[index] == null) {
            tableHeads[index] = newNode;
        } else {
            Node<K, V> previousNode = null;
            Node<K, V> currentNode = tableHeads[index];
            while (currentNode != null) {
                if (currentNode.getKey().equals(key)) {
                    currentNode.setValue(value);
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if (previousNode != null)
                previousNode.setNext(newNode);
        }
    }
    public V get(K key){
        V value = null;
        int index = index(key);
        Node<K, V> node = tableHeads[index];
        while (node != null){
            if(node.getKey().equals(key)) {
                value = node.getValue();
                break;
            }
            node = node.getNext();
        }
        return value;
    }
    public void remove(K key){
        int index = index(key);
        Node<K, V> previous = null;
        Node<K, V> node = tableHeads[index];
        while (node != null){
            if(node.getKey().equals(key)){
                if(previous == null){
                    node = node.getNext();
                    tableHeads[index] = node;
                    return;
                }else {
                    previous.setNext(node.getNext());
                    return;
                }
            }
            previous = node;
            node = node.getNext();
        }
    }

    private int index(K key) {
            if(key == null){
                return 0;
            }
            return Math.abs(key.hashCode() % capacity);
        }
    }


    class Node<K, V> {

        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value, Node<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
}