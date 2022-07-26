import java.util.*;
import java.io.*;

public class etuDB {

    private Scanner inputStream;

    private Table[] tables = new Table[4]; // dynamic array..
    private int tabloSayisi = 0;

    public etuDB() {

    }

    private void resize() {
        Table[] temp = new Table[tables.length * 2];
        for (int i = 0; i < tables.length; i++) {
            temp[i] = tables[i];
        }

        tables = temp;

    }

    public void createTable(String createTableCommand) throws Exception {

        Table table; // atamasi asagida yapilacak
        String file_path;

        if (createTableCommand.length() >= 18 && createTableCommand.substring(0, 18).equals("CREATE TABLE FROM ")) {
            file_path = createTableCommand.substring(18);
        } else {
            file_path = createTableCommand;
        }

        inputStream = new Scanner(new FileInputStream(file_path));

        // dosya adi icin
        File file = new File(file_path);

        String new_table_name = file.getName().substring(0, file.getName().lastIndexOf("."));

        String table_name_extension = file.getName().substring(file.getName().lastIndexOf("."));

        if (!table_name_extension.equals(".csv")) {
            throw new Exception("Dosya formati uygun degil!");
        }

        boolean var_mi = false;
        int tablo_index = 0;

        if (tabloSayisi > 0) {
            for (int i = 0; i < tables.length; i++) { // daha onceki tablolarin adlarina bakilsin
                if (tables[i] != null) {
                    if (tables[i].getTableName().equals(new_table_name)) {
                        tablo_index = i;
                        var_mi = true; // tablo sayisi arttirilmayacak, o isimde tablo daha once varmis
                        break;
                    }
                }
            }
        }

        if (!var_mi) { // yeni bir tablo olustur
            table = new Table();
            tabloSayisi++;

            if (tabloSayisi >= tables.length) {
                resize();
            }

            tables[tabloSayisi - 1] = table;

            table.setTableName(new_table_name);

        } else {
            table = tables[tablo_index];
        }

        String line = null;

        // ilk satir, kolon isimlerini icerecek

        if (inputStream.hasNext()) {
            line = inputStream.nextLine();

            table.setColumnsName(line.split(","));
        }

        StringBuilder input = new StringBuilder("");

        line = null;
        int satir_sayisi = 0;

        // once idler okunsun ve binary tree olusturulsun

        while (inputStream.hasNext()) {
            satir_sayisi++;
            line = inputStream.nextLine();
            input.append(line);
            input.append("\n");

            table.addIdsLinkedList(line.substring(0, line.indexOf(",")));

        }

        inputStream.close();
        table.setNumberOfRows(satir_sayisi);

        table.sortIDs();
        table.createBinaryTree();

        String[] data = input.toString().split("\n");

        int ind = 0;
        while (ind < data.length) {
            Row new_row = new Row(table.getNumberOfColumns());
            new_row.transferData(data[ind]);
            ind++;

            table.addRowToLinkedList(new_row);
        }

    } // create metodu sonu

    public void printSchema(String tableName) {

        String sum = "";

        for (Table t : tables) {
            if (t.getTableName().equals(tableName)) {

                String[] names = t.getColumnsName();

                for (int i = 0; i < names.length; i++) {
                    sum += names[i];

                    if (!(i == names.length - 1)) {
                        sum += "\n";
                    }

                }

                break;
            }
        }

        System.out.println(sum);
    }

    public void query(String query) throws Exception {

        String[] parts = parser(query, " ");
        if (parts.length == 4) {
            parts[3] = parts[3].replace(";", "");
        } else if (parts.length == 6) { // Filtre Uygulayarak Veri Sorgula
            parts[5] = parts[5].replace(";", "");
        }

        /*
         * parts[0] --> SELECT
         * parts[1] --> COLUMN(s)
         * parts[2] --> FROM
         * parts[3] --> TABLE NAME
         * 
         * FILTRE UYGULANARAK
         * parts[4] --> WHERE
         * parts[5] --> COLUMN NAME = VALUE
         * 
         */

        int tableIndex = 0;
        String[] column_names = null; // asagidaki dongu sonrasi burasi null kalmaya devam ediyorsa tablo yok demektir

        for (int i = 0; i < tabloSayisi; i++) {
            if (tables[i].getTableName().equals(parts[3])) {
                tableIndex = i;
                column_names = tables[i].getColumnsName();
                break;
            }
        }

        if (column_names == null) {
            throw new Exception("Tablo bulunamadi!");
        }

        int columnIndex; // bir tane column sorgulanacaksa

        String[] queriedColumns; // birden fazla column sorgulanacaksa
        int[] columnsIndex;

        LinkedList.Node<Row> currNode; // linkedlist'te rowlari gezebilmek icin kullanilacak

        if (parts[1].contains(",")) { // Birden fazla kolon var

            queriedColumns = parts[1].split(",");
            columnsIndex = new int[queriedColumns.length];

            int ind = 0;

            for (int i = 0; i < column_names.length; i++) {
                for (int j = 0; j < queriedColumns.length; j++) {
                    if (column_names[i].equals(queriedColumns[j])) {
                        columnsIndex[ind] = i;
                        ind++;
                        break;
                    }
                }
            }

            if (columnsIndex.length != ind) {
                throw new Exception("Ilgili kolon(lar) tabloda yer almamaktadir!");
            }

            if (parts.length == 6) { // Filtre uygulanarak
                String[] where_parts = parser(parts[5], "=");

                if (where_parts[0].equals(column_names[0])) { // binary tree uzerinde arama yapilacak

                    int target = Integer.parseInt(where_parts[1]);

                    tables[tableIndex].getBinaryTree().binaryIDSearchMultipleLine(target, columnsIndex);

                } else { // linkedlist uzerinden aranacak

                    int where_columnIndex = 0;

                    for (int i = 0; i < column_names.length; i++) {

                        if (column_names[i].equals(where_parts[0])) {
                            where_columnIndex = i;
                            break;
                        }
                    }

                    currNode = tables[tableIndex].getRowList().getHead();

                    while (currNode != null) {
                        if (currNode.getElement().get(where_columnIndex).equals(where_parts[1])) {
                            for (int i = 0; i < columnsIndex.length; i++) {
                                System.out.print(currNode.getElement().get(columnsIndex[i]));
                                if (i != columnsIndex.length - 1) {
                                    System.out.print(", ");
                                }
                            }
                            System.out.println();
                        }

                        currNode = currNode.getNext();
                    }

                }
            } else { // Filtre uygulanmadan

                currNode = tables[tableIndex].getRowList().getHead();
                while (currNode != null) {
                    for (int i = 0; i < columnsIndex.length; i++) {
                        System.out.print(currNode.getElement().get(columnsIndex[i]));
                        if (i != columnsIndex.length - 1) {
                            System.out.print(", ");
                        }
                    }

                    System.out.println();

                    currNode = currNode.getNext();
                }

            }

        } else if (parts[1].equals("*")) { // Tum kolonlar sorgulanacak

            columnsIndex = new int[tables[tableIndex].getNumberOfColumns()];
            for (int i = 0; i < columnsIndex.length; i++) {
                columnsIndex[i] = i;
            }

            if (parts.length == 6) { // Filtreli sorgu
                String[] where_parts = parser(parts[5], "=");

                if (where_parts[0].equals(column_names[0])) { // binary tree uzerinde arama yapilacak

                    int target = Integer.parseInt(where_parts[1]);
                    tables[tableIndex].getBinaryTree().binaryIDSearchMultipleLine(target, columnsIndex);

                } else {
                    int where_columnIndex = 0;

                    for (int i = 0; i < column_names.length; i++) {

                        if (column_names[i].equals(where_parts[0])) {
                            where_columnIndex = i;
                            break;
                        }
                    }

                    currNode = tables[tableIndex].getRowList().getHead();

                    while (currNode != null) {
                        if (currNode.getElement().get(where_columnIndex).equals(where_parts[1])) {
                            for (int i = 0; i < columnsIndex.length; i++) {
                                System.out.print(currNode.getElement().get(columnsIndex[i]));

                                if (i != columnsIndex.length - 1) {
                                    System.out.print(", ");
                                }
                            }

                            System.out.println();
                        }

                        currNode = currNode.getNext();
                    }
                }
            }

            else { // filtresiz sorgu

                currNode = tables[tableIndex].getRowList().getHead();

                while (currNode != null) {
                    for (int i = 0; i < tables[tableIndex].getNumberOfColumns(); i++) {
                        System.out.print(currNode.getElement().get(i));
                        if (i != tables[tableIndex].getNumberOfColumns() - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println();

                    currNode = currNode.getNext();
                }

            }

        } else { // Tek kolon sorgulanacak

            columnIndex = 0;

            boolean kontrol = false; // query'de verilen ilgili kolonun tabloda yer alip almadigini kontrol icin
            for (int i = 0; i < column_names.length; i++) {
                if (column_names[i].equals(parts[1])) {
                    columnIndex = i;
                    kontrol = true;
                    break;
                }
            }

            if (!kontrol) {
                throw new Exception("Ilgili kolon(lar) tabloda yer almamaktadir!");
            }

            if (parts.length == 6) {
                String[] where_parts = parser(parts[5], "=");

                if (where_parts[0].equals(column_names[0])) { // binary tree uzerinde arama yapilacak

                    int target = Integer.parseInt(where_parts[1]);

                    tables[tableIndex].getBinaryTree().binaryIDSearchOneLine(target, columnIndex);

                } else {

                    int where_columnIndex = 0;

                    for (int i = 0; i < column_names.length; i++) {

                        if (column_names[i].equals(where_parts[0])) {
                            where_columnIndex = i;
                            break;
                        }
                    }

                    currNode = tables[tableIndex].getRowList().getHead();

                    while (currNode != null) {
                        if (currNode.getElement().get(where_columnIndex).equals(where_parts[1])) {
                            System.out.println(currNode.getElement().get(columnIndex));
                        }

                        currNode = currNode.getNext();
                    }

                }

            } else { // Filtre uygulanmadan

                currNode = tables[tableIndex].getRowList().getHead();
                while (currNode != null) {
                    System.out.println(currNode.getElement().get(columnIndex));

                    currNode = currNode.getNext();
                }

            }
        }

    } // query sonu

    public Table[] getTables() {
        return tables;
    }

    public String[] parser(String input, String splitter) {

        return input.split(splitter);

    }

}