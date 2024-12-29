package org.study;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    try {
      var scanner = new Scanner(new File("file.txt"));
      scanner.useLocale(Locale.ENGLISH);
      var integers = new ArrayList<Integer>();
      var doubles = new ArrayList<Double>();
      var strings = new ArrayList<String>();
      while (scanner.hasNextLine()) {
        if (scanner.hasNextInt()) {
          integers.add(Integer.parseInt(scanner.nextLine()));
        } else if (scanner.hasNextDouble()) {
          doubles.add(Double.parseDouble(scanner.nextLine()));
        } else {
          strings.add(scanner.nextLine());
        }
      }
      if (!integers.isEmpty()) {
        var writer = new FileWriter("integers.txt");
        for (Integer integerNum : integers) {
          writer.write(String.format("%d%n", integerNum));
        }
        writer.close();
      }
      if (!doubles.isEmpty()) {
        var writer = new FileWriter("doubles.txt");
        for (Double doubleNum : doubles) {
          writer.write(String.format("%f%n", doubleNum));
        }
        writer.close();
      }
      if (!strings.isEmpty()) {
        var writer = new FileWriter("strings.txt");
        for (String string : strings) {
          writer.write(String.format("%s%n", string));
        }
        writer.close();
      }
    } catch (IOException e) {
      System.out.println(e.getLocalizedMessage());
    }
  }
}

