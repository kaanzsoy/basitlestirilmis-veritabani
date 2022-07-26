public class Main {

    public static void main(String[] args) {

        etuDB veriTabanim = new etuDB();
        try {
            veriTabanim.createTable("CREATE TABLE FROM /Users/kaan/Desktop/BIL212/odev_1/userLogs.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

        veriTabanim.printSchema("userLogs");

        try {
            veriTabanim.query("SELECT first_name FROM userLogs;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            veriTabanim.query("SELECT id,first_name,last_name,email FROM userLogs;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            veriTabanim.query("SELECT * FROM userLogs");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * long start1 = System.currentTimeMillis();
         * 
         * try {
         * veriTabanim.query("SELECT * FROM userLogs WHERE id=11842;");
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         * 
         * long end1 = System.currentTimeMillis();
         * System.out.println("Tree: " + (end1 - start1));
         * 
         * long start2 = System.currentTimeMillis();
         * 
         * try {
         * veriTabanim.query("SELECT * FROM userLogs WHERE first_name=Lorene;");
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         * 
         * long end2 = System.currentTimeMillis();
         * System.out.println("List: " + (end2 - start2));
         */

    }

    /*
     * etuDB veriTabanim = new etuDB();
     * try {
     * veriTabanim.createTable("CREATE TABLE FROM /home/userLogs.csv");
     * } catch (Exception e) {
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * }
     * veriTabanim.printSchema("userLogs");
     * try {
     * veriTabanim.query("SELECT first_name FROM userLogs;");
     * } catch (Exception e) {
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * }
     * try {
     * veriTabanim.query("SELECT id,first_name,last_name,email FROM userLogs;");
     * } catch (Exception e) {
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * }
     * try {
     * veriTabanim.query("SELECT * FROM userLogs;");
     * } catch (Exception e1) {
     * // TODO Auto-generated catch block
     * e1.printStackTrace();
     * }
     * try {
     * veriTabanim.query("SELECT email FROM userLogs WHERE id=10321;");
     * } catch (Exception e) {
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * }
     * try {
     * veriTabanim.query("SELECT email FROM userLogs WHERE first_name=Elfie;");
     * } catch (Exception e) {
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * }
     * 
     * // veriTabanim.createTable("CREATE TABLE
     * // FROM/Users/kaan/Desktop/BIL212/odev_1/userLogs.csv");
     * 
     * /*
     * try {
     * veriTabanim.createTable("userLogs.csv");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * /*
     * try {
     * veriTabanim.
     * createTable("CREATE TABLE FROM /Users/kaan/Desktop/BIL212/odev_1/oscar_age_female.csv"
     * );
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * 
     * try {
     * veriTabanim.createTable("mlb_players.csv");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * try {
     * veriTabanim.
     * createTable("CREATE TABLE FROM /Users/kaan/Desktop/BIL212/odev_1/oscar_age_male.csv"
     * );
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * try {
     * veriTabanim.createTable("trees.csv");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * try {
     * veriTabanim.createTable("zillow.txt"); // Tablo bulunamadi hatasi
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * try {
     * veriTabanim.query("SELECT Year FROM oscar_age_female WHERE Index=1;");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * try {
     * veriTabanim.query("SELECT email FROM userLogs WHERE id=10321;");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * try {
     * veriTabanim.query("SELECT year FROM userLogs WHERE id=10321;"); // Kolon
     * bulunamadi hatasi
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * try {
     * veriTabanim.printSchema("userLogs");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * 
     * /*
     * try {
     * veriTabanim.query("SELECT first_name FROM userLogs;");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * 
     * try {
     * veriTabanim.query("SELECT id,first_name,last_name,email FROM userLogs;");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * 
     * 
     * try {
     * veriTabanim.query("SELECT email FROM userLogs;");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * 
     * 
     * try {
     * veriTabanim.query("SELECT * FROM userLogs;");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * 
     * 
     * try {
     * veriTabanim.query("SELECT email FROM userLogs WHERE id=10321;");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * try {
     * veriTabanim.query("SELECT first_name FROM userLogs WHERE id=11;");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * try {
     * veriTabanim.query("SELECT id,email FROM userLogs WHERE first_name=Elfie;");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * try {
     * veriTabanim.query("SELECT email FROM userLogs WHERE first_name=Elfie;");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * try {
     * veriTabanim.query("SELECT * FROM userLogs WHERE id=11;");
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * 
     * 
     * 
     * /*
     * long start1 = System.currentTimeMillis();
     * 
     * veriTabanim.query("SELECT * FROM userLogs WHERE id=11842;");
     * 
     * long end1 = System.currentTimeMillis();
     * System.out.println("Tree: " + (end1 - start1));
     * 
     * long start2 = System.currentTimeMillis();
     * 
     * veriTabanim.query("SELECT * FROM userLogs WHERE first_name=Lorene;");
     * 
     * long end2 = System.currentTimeMillis();
     * System.out.println("List: " + (end2 - start2));
     */

    /*
     * long start1 = System.currentTimeMillis();
     * 
     * veriTabanim.query("SELECT first_name FROM userLogs;");
     * 
     * long end1 = System.currentTimeMillis();
     * 
     * long start2 = System.currentTimeMillis();
     * 
     * veriTabanim.query("SELECT last_name,email FROM userLogs;");
     * 
     * long end2 = System.currentTimeMillis();
     * 
     * System.out.println("Bir: " + (end1 - start1));
     * System.out.println("Iki: " + (end2 - start2));
     */

}
