package javarush.com;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws Exception {
      Set<String> files = new TreeSet<>();
      Scanner scanner = new Scanner(System.in);
      while (true) {
          String partsFile = scanner.nextLine();
          if(partsFile.equals("end")) break;
          files.add(partsFile);
      }
      System.out.println(files);
      String str;
      for(String s: files) {
          str = s.substring(0,s.indexOf(".part"));
      }
    }
}



