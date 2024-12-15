package Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainTest {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        String fileName;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                fileName = reader.readLine();
                if (fileName.equals("exit")) break;
                ReadThread readThread = new ReadThread(fileName);
                readThread.start();
            }
        }
        System.out.println(resultMap);
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        public void run() {
            int max = Integer.MIN_VALUE;
            Map<Integer, Integer> map = new HashMap<>();
            try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                int data;
                while (fileInputStream.available() > 0) {
                    data = fileInputStream.read();
                    map.put(data, map.getOrDefault(data, 0) + 1);
                }
            } catch (IOException e) {
                System.out.println("Такого файла нет " + fileName);
            }
            for (Integer i : map.values()) {
                if (i > max) {
                    max = i;
                }
            }
            List<Integer> list = new ArrayList<>();
            int res = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (max == entry.getValue()) {
                    res = entry.getKey();
                    list.add(res);
                    resultMap.put(fileName, Collections.min(list));
                }
            }
        }
    }
}

