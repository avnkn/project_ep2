import java.io.*;

public class evaluatorDifferences {
    public static String rv = ",";
    public static String rs = "\n";

    public static void main(String[] args) {
        try {
            FileInputStream fstream1 = new FileInputStream("test/Main1.txt");
            FileInputStream fstream2 = new FileInputStream("test/Main2.txt");
            BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(fstream2));
            String strLine1;
            String strLine2;

            String[] LineCsv;
            LineCsv = new String[3];
            LineCsv[0] = "Номер строки";
            LineCsv[1] = "Файл1";
            LineCsv[2] = "Файл2";

            PrintWriter pw = new PrintWriter(new File("test.csv"));
            StringBuilder sb = new StringBuilder();
            appendLine(sb, LineCsv);

            int i = 1;
            while (i++ > 0 && (strLine1 = br1.readLine()) != null && (strLine2 = br2.readLine()) !=null) {
                if (!strLine1.equals(strLine2)) {
                    LineCsv[0] = Integer.toString(i);
                    LineCsv[1] = strLine1;
                    LineCsv[2] = strLine2;
                    appendLine(sb, LineCsv);
                }
            }
            while (i++ > 0 && (strLine1 = br1.readLine()) != null ) {
                LineCsv[0] = Integer.toString(i);
                LineCsv[1] = strLine1;
                LineCsv[2] = "";
                appendLine(sb, LineCsv);
            }
            while (i++ > 0 && (strLine2 = br2.readLine()) != null ) {
                LineCsv[0] = Integer.toString(i);
                LineCsv[1] = "";
                LineCsv[2] = strLine2;
                appendLine(sb, LineCsv);
            }
            pw.write(sb.toString());
            pw.close();
            System.out.println("done!");

        } catch (IOException e) {
            System.out.println("Ошибка");
        }
    }

    public static void appendLine(StringBuilder sb, String[] arr) {
        for (int i =0; i< arr.length; i++) {
            sb.append(arr[i] + rv);
        }
        sb.append(rs);
    }
}
