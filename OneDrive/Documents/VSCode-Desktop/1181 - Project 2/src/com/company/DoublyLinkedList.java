package com.company;

public class DoublyLinkedList {

    // this creates two nodes for the head and the tail
    private Node<Room> head;
    private Node<Room> tail;

    // this adds a room to the DoublyLinkedList
    public void add(Room newRoom) {
        Node<Room> newerRoom = new Node<>(newRoom);
        if (head == null) {
            head = newerRoom;
            tail = newerRoom;
        } else if (this.size() == 1) {
            tail.previous = head;
            tail.next = newerRoom;
            tail = newerRoom;
        } else {
            tail.previous = tail;
            tail.next = newerRoom;
            tail = newerRoom;
        }
    }

    // this prints out the size of the doubly linked list
    public int size() {
        int size = 0;

        Node<Room> current = head;
        while (current != null) {
            current = current.next;
            size++;
        }
        return size;
    }

    // this prints out the entire doubly linked list
    public void print() {
        Node<Room> current = head;
        while (current != null) {
            System.out.print(current.element + " ");
            current = current.next;
        }
        System.out.println();
    }

    // this returns the item at a certain index
    public Room getItemAt(int index) {
        Node<Room> current = head;
        for (int i=0; i<index; i++) {
            current = current.next;
            if (current == null) {
                break;
            }
        }
        if (current != null) {
            return current.element;
        } else {
            System.out.println("Invalid index!");
        }
        return null;
    }

    // this goes back a certain number in the doubly linked list
    public Room goBack(int index, int numBack) {
        Node<Room> current = head;
        for (int i=0; i<index - numBack; i++) {
            current = current.next;
            if (current == null) {
                break;
            }
        }
        if (current != null) {
            return current.element;
        } else {
            System.out.println("Invalid index!");
        }
        return null;
    }

    // this deletes the last item in the doubly linked list
    public void deleteLast() {
        Node<Room> current = head;

        for(int i=0; i<this.size() - 2; i++) {
            current = current.next;

            if(current == null) {
                break;
            }
        }
        Node<Room> temp = tail;

        tail = current;
        tail.next = null;
        tail.previous = current.previous;
    }
}
