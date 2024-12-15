package Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Crud {
    public class Solution {
        public static void main(String[] args) throws Exception {
            if (args.length == 0 || args.length > 4) {
                return;
            }
            String fileName = "";
            List<String> listId = new ArrayList<>();
            String stringmaxId = "";
            String productName = parseProductName(args[1]);
            String price = parsePrice(args[2]);
            String quantity = parseQuantity(args[3]);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                fileName = reader.readLine();
            }
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
                String str;
                while (bufferedReader.ready()) {
                    str = bufferedReader.readLine();
                    listId.add(str.substring(0, 8));
                }
                List<String> trimFormatedId = new ArrayList<>();
                List<Integer> intListId = new ArrayList<>();
                for (int i = 0; i < listId.size(); i++) {
                    String clean = listId.get(i).replaceAll("\\s+", "");
                    trimFormatedId.add(clean);
                }
                for (String s : trimFormatedId) {
                    intListId.add(Integer.parseInt(s));
                }
                int intMaxId = Collections.max(intListId) + 1;
                stringmaxId = String.valueOf(intMaxId);
                String resultAll = stringmaxId + productName + price + quantity;
                bufferedWriter.newLine();
                bufferedWriter.write(resultAll);
            }
        }

        public static String parseProductName(String productName) {
            if (productName.length() > 30) return productName.substring(0, 30);
            return String.format("%-30s", productName);
        }

        public static String parsePrice(String price) {
            if (price.length() > 8) return price.substring(0, 8);
            return String.format("%-8s", price);
        }

        public static String parseQuantity(String quantity) {
            if (quantity.length() > 4) return quantity.substring(0, 4);
            return String.format("%-4s", quantity);
        }
    }
}
