package com.company;

// this creates a new node for the Doubly Linked List
public class Node<E> {
    E element;
    Node<E> next;
    Node<E> previous;

    public Node(E element) {
        this.element = element;
    }
}
