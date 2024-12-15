package Test;

import java.io.*;

public class TestSolution {
    public static void main(String[] args) throws IOException {
        String fileName1;
        String fileName2;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName1 = bufferedReader.readLine();
            fileName2 = bufferedReader.readLine();
        }
        String str = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName1))) {
            while (reader.ready()) {
                str = reader.readLine();
            }
        }
        String[] doubleStrings = str.split(" ");
        double[] source = new double[doubleStrings.length];
        int[] res = new int[source.length];
        for (int i = 0; i <doubleStrings.length; i++) {
            source[i] = Double.parseDouble(doubleStrings[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <source.length; i++) {
            res[i] =(int) Math.round(source[i]);
            sb.append(res[i]).append(" ");
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2))) {
            writer.write(sb.toString());
        }
    }
}

