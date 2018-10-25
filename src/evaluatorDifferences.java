import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class evaluatorDifferences {
    public static String rv = ";";
    public static String rs = "\n";
    public static String fileName = "evaluatorDifferences.csv";
    public static int filesCount;

    public static void main(String[] args) {
        try {
            System.out.println("Введите количество сравниваемых файлов");
            filesCount = setIntInput();

            BufferedReader files[] = setArrayFiles(filesCount);
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
                if (name.equals("exit")) {
                    System.exit(0);
                }
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
        lineCsv = setFirstLineCSV(filesCount);
        appendLine(sb, lineCsv);
        int nStr = 0;

        try {
            do {
                nStr++;
                lineCsv[0] = Integer.toString(nStr);
                for (int i=0; i < filesCount; i++) {
                    lineCsv[i+1] =files[i].readLine();
                }
                if (diffLines(Arrays.copyOfRange(lineCsv, 1, filesCount+1))) {
                    appendLine(sb, lineCsv);
                }
            } while (checkNullLines(Arrays.copyOfRange(lineCsv, 1, filesCount+1)));
        } catch (IOException e) {
            System.out.println("Ошибка");
            System.exit(0);
        }
        return sb.toString();
    }

    public static String[] setFirstLineCSV(int n) {
        String line[] = new String[n + 1];
        line[0] = "Номер строки";
        for (int i=1; i <= n; i++) {
            line[i] = "Файл " + Integer.toString(i);
        }
        return line;
    }

    public static boolean diffLines(String[] lineCsv) {
        String str = lineCsv[0] != null ? lineCsv[0] : "";
        for (int i=1; i< lineCsv.length; i++) {
            if (!str.equals(lineCsv[i] != null ? lineCsv[i] : "")) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkNullLines(String[] lineCsv) {
        for (int i=0; i< lineCsv.length; i++) {
            if (lineCsv[i] !=null) {
                return true;
            }
        }
        return false;
    }

    public static void appendLine(StringBuilder sb, String[] arr) {
        sb.append(String.join(rv, arr));
        sb.append(rs);
    }
}
