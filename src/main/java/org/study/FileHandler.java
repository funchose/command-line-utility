package org.study;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileHandler {

  public Set<String> getExistingLists() {
    return existingLists;
  }

  private final Set<String> existingLists;
  private final List<Integer> integers;
  private final List<Double> doubles;
  private final List<String> strings;

  private Scanner scanner;

  private FileWriter writer;

  public FileHandler() {
    this.integers = new ArrayList<>();
    this.doubles = new ArrayList<>();
    this.strings = new ArrayList<>();
    this.existingLists = new HashSet<>();
  }

  public FileWriter getWriter() {
    return writer;
  }

  public void setWriter(FileWriter writer) {
    this.writer = writer;
  }

  public List<Integer> getIntegers() {
    return integers;
  }

  public List<Double> getDoubles() {
    return doubles;
  }

  public List<String> getStrings() {
    return strings;
  }

  public Scanner getScanner() {
    return scanner;
  }

  public void setScanner(Scanner scanner) {
    this.scanner = scanner;
  }

  public void readFile(String filename) throws FileNotFoundException {
    setScanner(new Scanner(new File(filename)));
    scanner.useLocale(Locale.ENGLISH);
    while (scanner.hasNextLine()) {
      if (scanner.hasNextInt()) {
        try {
          integers.add(Integer.parseInt(scanner.nextLine()));
          existingLists.add("integers");
        } catch (NumberFormatException e) {
          System.out.println("The input was incorrect. Please, read rules for data input." +
              " Error has occurred in the file: " + filename);
        }
      } else if (scanner.hasNextDouble()) {
        doubles.add(Double.parseDouble(scanner.nextLine()));
        existingLists.add("floats");
      } else {
        strings.add(scanner.nextLine());
        existingLists.add("strings");
      }
    }
  }

  public void createFile(String directoryPath, String filename) {
    try {
      Files.createDirectories(Paths.get(directoryPath));
      var file = new File(directoryPath + filename);
      file.createNewFile();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }
  }

  public void writeIntegers(Statistics statistics) throws IOException {
    for (Integer integer : integers) {
      writer.append(String.format("%d%n", integer));
      statistics.collectIntsStatistics(integer);
    }
  }

  public void writeDoubles(Statistics statistics) throws IOException {
    for (Double doubleNum : doubles) {
      writer.append(String.format("%f%n", doubleNum));
      statistics.collectDoublesStatistics(doubleNum);
    }
  }

  public void writeStrings(Statistics statistics) throws IOException {
    for (String string : strings) {
      writer.append(String.format("%s%n", string));
      statistics.collectStringsStatistics(string);
    }
  }
}
