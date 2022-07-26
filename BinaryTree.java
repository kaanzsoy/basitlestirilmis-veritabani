public class BinaryTree<E> {

    private static class Node<E> {

        private int treeIndex;
        private LinkedList<Row> referencedRow;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(int index, LinkedList<Row> e, Node<E> p, Node<E> l, Node<E> r) {
            treeIndex = index;
            referencedRow = e;
            parent = p;
            left = l;
            right = r;
        }

        public void setReferencedRow(LinkedList<Row> e) {
            referencedRow = e;
        }

        public LinkedList<Row> getReferencedRow() {
            return referencedRow;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getRight() {
            return right;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setParent(Node<E> parentNode) {
            parent = parentNode;
        }

        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }

        public void setTreeIndex(int n) {
            treeIndex = n;
        }

        public int getTreeIndex() {
            return treeIndex;
        }

    } // node class sonu

    private Node<E> root;
    private int size;

    public BinaryTree() {
        size = 0;
        root = null;
    }

    public Node<E> getRoot() {
        return root;
    }

    public void insertNodes(int[] data, int bas, int son, Node<E> parentNode, char c) {

        Node<E> parent = null;
        int mid = (bas + son + 1) / 2;

        if (bas > son) { // end case
            return;
        }

        if (size == 0) {
            Node<E> root = addRoot(data[mid]);
            parent = root;
        } else {
            Node<E> new_node = createNode(data[mid], null, parentNode, null, null);

            if (c == 'l') { // insert left side
                parentNode.setLeft(new_node);
            } else if (c == 'r') {
                parentNode.setRight(new_node);
            }
            parent = new_node;
        }

        insertNodes(data, bas, mid - 1, parent, 'l'); // left child bulma
        insertNodes(data, mid + 1, son, parent, 'r'); // right child bulma

    }

    public Node<E> createNode(int index, LinkedList<Row> e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(index, e, parent, left, right);
    }

    public Node<E> root() {
        return root;
    }

    public Node<E> addRoot(int index) {
        if (size != 0) {
            // EXCEPTION
        } else {
            root = createNode(index, null, null, null, null);
            size = 1;
        }
        return root;
    }

    public int getSize() {
        return size;
    }

    public void binarySearch(int key, Row row, Node<E> currNode) {

        if (key == currNode.treeIndex) {
            if (currNode.referencedRow == null) {
                LinkedList<Row> new_list = new LinkedList<Row>();
                new_list.addFirst(row);
                currNode.setReferencedRow(new_list);
            } else {
                currNode.getReferencedRow().addFirst(row);
            }
        } else {
            if (key < currNode.treeIndex) {
                binarySearch(key, row, currNode.getLeft());
            } else if (key > currNode.treeIndex) {
                binarySearch(key, row, currNode.getRight());
            }
        }

    }

    public void binaryIDSearchMultipleLine(int key, int[] columns) {
        // iterative

        Node<E> currNode = root;
        Node<E> searchingNode = null;

        while (currNode != null) {
            if (currNode.getTreeIndex() == key) {
                searchingNode = currNode;
                break;
            } else {
                if (key < currNode.getTreeIndex()) {
                    currNode = currNode.getLeft();
                } else {
                    currNode = currNode.getRight();
                }
            }
        }

        LinkedList.Node<Row> currRow = searchingNode.getReferencedRow().getHead();

        while (currRow != null) {
            for (int i = 0; i < columns.length; i++) {
                System.out.print(currRow.getElement().get(i));

                if (i != columns.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();

            currRow = currRow.getNext();
        }

    }

    public void binaryIDSearchOneLine(int key, int columnIndex) {

        Node<E> currNode = root;
        Node<E> searchingNode = null;

        while (currNode != null) {
            if (currNode.getTreeIndex() == key) {
                searchingNode = currNode;
                break;
            } else {
                if (key < currNode.getTreeIndex()) {
                    currNode = currNode.getLeft();
                } else {
                    currNode = currNode.getRight();
                }
            }
        }

        LinkedList.Node<Row> currRow = searchingNode.getReferencedRow().getHead();

        while (currRow != null) {
            System.out.println(currRow.getElement().get(columnIndex));

            currRow = currRow.getNext();
        }

    }

}
