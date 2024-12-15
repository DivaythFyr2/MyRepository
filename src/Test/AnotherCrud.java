package Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnotherCrud {
    public static void main(String[] args) throws IOException {
        if (args.length == 0 || args.length > 5) {
            return;
        }
        String fileName;
        String id = "";
        String productName = "";
        String price = "";
        String quantity = "";
        if (args.length > 1) id = parseId(args[1]);
        if (args.length > 2) productName = parseProductName(args[2]);
        if (args.length > 3) price = parsePrice(args[3]);
        if (args.length > 4) quantity = parseQuantity(args[4]);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = reader.readLine();
        }

        List<String> fileStrings = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String str = "";
            while (bufferedReader.ready()) {
                str = bufferedReader.readLine();
                if (args[0].equals("-u") && str.startsWith(id)) {
                    str = id + productName + price + quantity;
                } else if (args[0].equals("-d") && str.startsWith(id)) {
                    continue;
                }
                fileStrings.add(str);
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (String s : fileStrings) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
        }
    }

    public static String parseId(String id) {
        if (id.length() > 8) return id.substring(0, 8);
        return String.format("%-8s", id);
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

