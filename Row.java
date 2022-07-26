public class Row {

    private String[] data;

    public Row(int i) {
        data = new String[i];
    }

    public void transferData(String s) {
        data = s.split(",");
    }

    public String get(int i) {
        return data[i];
    }

}
