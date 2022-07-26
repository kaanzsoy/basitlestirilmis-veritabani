import java.util.Scanner;

public class Prompt {

    private static Scanner scan;
    private static etuDB veriTabanim = new etuDB();

    public static void main(String[] args) {

        while (true) {

            scan = new Scanner(System.in);
            System.out.print("etuDB>> ");

            String input = scan.nextLine();

            if (input.equals("")) { // enter'a basilinca program biter (while dongusu biter)
                break;
            }

            else if (input.substring(0, 6).equals("CREATE")) {

                try {
                    veriTabanim.createTable(input);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (input.substring(0, 6).equals("SELECT")) {
                try {
                    veriTabanim.query(input);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            else { // tablo ismi yazilirsa

                Table[] temp = veriTabanim.getTables();
                Table table = null;
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i] != null) {
                        if (temp[i].getTableName().equals(input)) {
                            table = temp[i];
                            break;
                        }
                    }
                }

                if (table == null) {
                    continue;
                } else {
                    try {
                        veriTabanim.query("SELECT * FROM " + table.getTableName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}