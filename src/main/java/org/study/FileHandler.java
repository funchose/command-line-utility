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

  public Set<String> existingLists;
  private List<Integer> integers;
  private List<Double> doubles;
  private List<String> strings;
  private String directoryPath;
  private String prefix;


  private Scanner scanner;

  private FileWriter writer;

  public FileHandler() {
    this.integers = new ArrayList<>();
    this.doubles = new ArrayList<>();
    this.strings = new ArrayList<>();
    this.existingLists = new HashSet<>();
    this.directoryPath = "";
    this.prefix = "";
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getDirectoryPath() {
    return directoryPath;
  }

  public void setDirectoryPath(String directoryPath) {
    this.directoryPath = directoryPath;
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
        integers.add(Integer.parseInt(scanner.nextLine()));
        existingLists.add("integers");
      } else if (scanner.hasNextDouble()) {
        doubles.add(Double.parseDouble(scanner.nextLine()));
        existingLists.add("floats");
      } else {
        strings.add(scanner.nextLine());
        existingLists.add("strings");
      }
    }
  }

  public void createFile(String directoryPath, String filename) throws IOException {
    Files.createDirectories(Paths.get(directoryPath));
    var file = new File(directoryPath + filename);
    file.createNewFile();
  }

  public void writeIntegers() throws IOException {
    for (Integer integer : integers) {
      writer.append(String.format("%d%n", integer));
    }
  }

  public void writeDoubles() throws IOException {
    for (Double doubleNum : doubles) {
      writer.append(String.format("%f%n", doubleNum));
    }
  }

  public void writeStrings() throws IOException {
    for (String string : strings) {
      writer.append(String.format("%s%n", string));
    }
  }
}
