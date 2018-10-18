import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class evaluatorDifferences {
    public static String rv = ";";
    public static String rs = "\n";
    public static String fileName = "evaluatorDifferences.csv";

    public static void main(String[] args) {
        try {
            System.out.println("Введите количество сравниваемых файлов");
            int n = setIntInput();

            BufferedReader files[] = setArrayFiles(n);
            String csv = diffFiles(files);

            PrintWriter pw = new PrintWriter(new File(fileName));
            pw.write(csv);
            pw.close();
            System.out.println("готово, смотри файл - " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка");
            System.exit(0);
        }

    }

    public static int setIntInput() {
        int m;
        Scanner in = new Scanner(System.in);
        for (int i=0; true; i++) {
            if (in.hasNextInt()) {
                m = in.nextInt();
                break;
            } else {
                in.next();
                System.out.println("Ошибка, введите число");
            }
        }
        return m;
    }

    public static BufferedReader[] setArrayFiles (int n) {
        BufferedReader files[] = new BufferedReader[n];
        Scanner in = new Scanner(System.in);
        String name;
        int i=0;
        while (i < n) {
            System.out.println("Введите название файла для сравнения №" + Integer.toString(i+1));
            try {
                name = in.next();
                BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
                files[i] = file;
                i++;
            } catch (IOException e) {
                System.out.println("Ошибка загрузки файла");
            }
        }
        return files;
    }

    public static String diffFiles(BufferedReader[] files) {
        String[] lineCsv;
        String str;
        StringBuilder sb = new StringBuilder();
        lineCsv = new String[files.length];

        try {
            sb.append("Номер строки" + rv);
            for (int i=0; i<files.length; i++) {
                lineCsv[i] = "Файл " + Integer.toString(i+1);
            }
            appendLine(sb, lineCsv);

            int nStr = 0;
            while (++nStr > 0) {
                for (int i=0; i<files.length; i++) {
                    lineCsv[i] =files[i].readLine();
                }
                if (checkNullLines(lineCsv)) {
                    break;
                }
                if (diffLines(lineCsv)) {
                    sb.append(Integer.toString(nStr) + rv);
                    appendLine(sb, lineCsv);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
            System.exit(0);
        }
        return sb.toString();
    }

    public static boolean diffLines(String[] lineCsv) {
        int flag = 0;
        String str1 = lineCsv[0] != null ? lineCsv[0] : "";
        for (int i=1; i< lineCsv.length; i++) {
            if (!str1.equals(lineCsv[i])) {
                flag = 1;
                break;
            }
        }
        return flag == 1;
    }

    public static boolean checkNullLines(String[] lineCsv) {
        int flag = 0;
        for (int i=0; i< lineCsv.length; i++) {
            if (lineCsv[i] !=null) {
                flag = 1;
                break;
            }
        }
        return flag == 0;
    }

    public static void appendLine(StringBuilder sb, String[] arr) {
        sb.append(String.join(rv, arr));
        sb.append(rs);
    }
}
