package org.study;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.commons.cli.*;

public class CmdParser {
  private final Options options = new Options();
  private Statistics statistics;
  private final CommandLine cmd;
  private FileHandler fileHandler;
  private final List<String> files;
  private boolean appendMode;
  private String directoryPath;
  private String prefix;
  private StatisticsType type;

  public void setFileHandler(FileHandler fileHandler) {
    this.fileHandler = fileHandler;
  }

  public boolean isAppendMode() {
    return appendMode;
  }

  public void setAppendMode(boolean appendMode) {
    this.appendMode = appendMode;
  }

  public CmdParser(String[] args) throws ParseException {
    createCmdOptions();
    CommandLineParser parser = new DefaultParser();
    this.cmd = parser.parse(options, args);
    this.files = cmd.getArgList();
    this.appendMode = false;
    this.type = StatisticsType.NONE;
    this.directoryPath = "";
    this.prefix = "";
  }

  public void createCmdOptions() {
    options.addOption("o", true, "Set the filepath for result files");
    options.addOption("p", true, "Set the prefix for filenames");
    options.addOption("a", false, "Add information to existing files " +
        "instead of overwriting");
    options.addOption("s", false, "Show short statistics");
    options.addOption("f", false, "Show full statistics");
  }

  public void parseCommand() throws IOException {
    fileHandler = new FileHandler();
    parseFlags();
    readFiles();
    statistics = new Statistics(fileHandler);
    for (String list : fileHandler.getExistingLists()) {
      createOutputFile(list);
      writeDataToFile(list);
      fileHandler.getWriter().close();
    }
    statistics.calculateAvg();
    printStatistics(type);
  }

  public void parseFlags() {
    if (cmd.hasOption("o")) {
      directoryPath = cmd.getOptionValue("o");
    }
    if (cmd.hasOption("p")) {
      prefix = cmd.getOptionValue("p");
    }
    if (cmd.hasOption("a")) {
      setAppendMode(true);
    }
    if (cmd.hasOption("s")) {
      type = StatisticsType.SHORT;
    }
    if (cmd.hasOption("f")) {
      type = StatisticsType.FULL;
    }
  }

  private void readFiles() throws FileNotFoundException {
    for (String file : files) {
      var new_file = new File(file);
      if (new_file.exists()) {
        if (!isAppendMode()) {
          setFileHandler(new FileHandler());
        }
        fileHandler.readFile(file);
        fileHandler.getScanner().close();
      } else {
        System.out.println("File " + file + " is not found");
      }
    }
  }

  private void createOutputFile(String list) throws IOException {
    String filename = prefix + list + ".txt";
    fileHandler.createFile(directoryPath, filename);
    fileHandler.setWriter(new FileWriter(
        directoryPath + filename, isAppendMode()));
  }

  private void writeDataToFile(String list) throws IOException {
    switch (list) {
      case "integers":
        fileHandler.writeIntegers(statistics);
        break;
      case "floats":
        fileHandler.writeDoubles(statistics);
        break;
      case "strings":
        fileHandler.writeStrings(statistics);
        break;
    }
  }

  private void printStatistics(StatisticsType type) {
    switch (type) {
      case SHORT -> {
        if (fileHandler.getExistingLists().contains("integers")) {
          statistics.printShortIntsStatistics();
        }
        if (fileHandler.getExistingLists().contains("floats")) {
          statistics.printShortDoublesStatistics();
        }
        if (fileHandler.getExistingLists().contains("strings")) {
          statistics.printShortStringsStatistics();
        }
      }
      case FULL -> {
        if (fileHandler.getExistingLists().contains("integers")) {
          statistics.printFullIntsStatistics();
        }
        if (fileHandler.getExistingLists().contains("floats")) {
          statistics.printFullDoublesStatistics();
        }
        if (fileHandler.getExistingLists().contains("strings")) {
          statistics.printFullStringsStatistics();
        }
      }
    }
  }
}