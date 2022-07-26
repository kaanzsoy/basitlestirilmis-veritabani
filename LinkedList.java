public class LinkedList<E> {

    private Node<E> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
        head = new Node<E>(e, head);
        size++;
    }

    public Node<E> getHead() {
        return head;
    }

    public static class Node<E> {

        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

    }

}