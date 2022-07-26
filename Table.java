
public class Table {

    private String[] columns_name;
    private String table_name;
    private int number_of_columns;
    private int number_of_rows;
    private int[] ids_as_int; // sorted ve ayni sayilar yalnizca bir defa var

    private BinaryTree<Row> binary_tree; // id'ler siralandiktan sonra da yapilabilir

    private LinkedList<Row> row_list; // Row'lardan olusan bir linkedlist'e sahip olacak
    private LinkedList<Integer> ids_list;

    public BinaryTree<Row> getBinaryTree() {
        return binary_tree;
    }

    public LinkedList<Row> getRowList() {
        return row_list;
    }

    public LinkedList<Integer> getIdsList() {
        return ids_list;
    }

    // her seferinde binary search icinde arama yapilacak.
    public void addRowToLinkedList(Row r) {

        int ind = Integer.parseInt(r.get(0));
        binary_tree.binarySearch(ind, r, binary_tree.getRoot()); // olusturulmus binary treenin nodelarina rowlarin
                                                                 // referanslari tanimlandi

        row_list.addFirst(r);
    }

    public void addIdsLinkedList(String s) {
        int i = Integer.parseInt(s);
        ids_list.addFirst(i);
    }

    public Table() {
        columns_name = null;
        table_name = null;
        row_list = new LinkedList<Row>();
        ids_list = new LinkedList<Integer>();
    }

    public void createBinaryTree() {
        binary_tree = new BinaryTree<Row>();

        binary_tree.insertNodes(ids_as_int, 0, ids_as_int.length - 1, null, 'o');
    }

    public void setNumberOfRows(int n) {
        number_of_rows = n;
    }

    public int getNumberOfRows() {
        return number_of_rows;
    }

    public void sortIDs() {

        ids_as_int = new int[number_of_rows];

        LinkedList.Node<Integer> temp = ids_list.getHead();

        int i = 0;
        while (temp != null) {

            ids_as_int[i] = temp.getElement();
            i++;
            temp = temp.getNext();
        }

        sortedIDs(ids_as_int);

    }

    // Insertion sort
    public void sortedIDs(int[] arr) {
        int[] temp = new int[arr.length]; // degerlerin siralanabilmesi icin int olarak tutan dizi var

        int index = 0;

        for (int j = 0; j < temp.length; j++) {
            // ayni sayilar yalnizca bir defa yer alacak..
            if (j > 0) {
                if (arr[j - 1] == arr[j]) {
                    continue;
                } else {
                    temp[index] = arr[j];
                    index++;
                }
            } else {
                temp[index] = arr[j];
                index++;
            }
        }

        index--;

        int key = 0;
        int j = 0;

        for (int i = 1; i < index + 1; i++) {
            key = temp[i];
            j = i - 1;

            while (j >= 0 && temp[j] > key) {
                temp[j + 1] = temp[j];
                j = j - 1;
            }
            temp[j + 1] = key;

        }

        ids_as_int = temp;

    }

    public String[] getColumnsName() {
        return columns_name;
    }

    public void setColumnsName(String[] arr) {
        columns_name = arr;
        setNumberOfColumns(columns_name.length);
    }

    public void setTableName(String s) {
        table_name = s;
    }

    public String getTableName() {
        return table_name;
    }

    public void setNumberOfColumns(int i) {
        number_of_columns = i;
    }

    public int getNumberOfColumns() {
        return number_of_columns;
    }

}
