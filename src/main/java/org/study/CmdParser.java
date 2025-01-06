package org.study;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.commons.cli.*;

public class CmdParser {
  private final Options options = new Options();
  private final CommandLine cmd;

  private FileHandler fileHandler;

  private final List<String> files;

  private boolean appendMode;
  private Statistics statistics;
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
  }

  public void createCmdOptions() {
    options.addOption("o", true, "Set the filepath for result files");
    options.addOption("p", true, "Set the prefix for filenames");
    options.addOption("a", false, "Add information to existing files " +
        "instead of overwriting");
    options.addOption("s", false, "Show short statistics");
    options.addOption("f", false, "Show full statistics");
  }

  public void parseFlags() {
    if (cmd.hasOption("o")) {
      fileHandler.setDirectoryPath(cmd.getOptionValue("o"));
    }
    if (cmd.hasOption("p")) {
      fileHandler.setPrefix(cmd.getOptionValue("p"));
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

  public void parseCommand() throws IOException {
    parseFlags();
    setFileHandler(new FileHandler());
    for (String file : files) {
      if (!isAppendMode()) {
        setFileHandler(new FileHandler());
      }
      fileHandler.readFile(file);
      fileHandler.getScanner().close();
    }
    statistics = new Statistics(fileHandler);
    for (String list : fileHandler.getExistingLists()) {
      String filename = fileHandler.getPrefix() + list + ".txt";
      try {
        fileHandler.createFile(fileHandler.getDirectoryPath(), filename);
      } catch (Exception e) {
        System.out.println(e.getLocalizedMessage());
      }
      fileHandler.setWriter(new FileWriter(
          fileHandler.getDirectoryPath() + filename, isAppendMode()));
      switch (list) {
        case "integers":
          fileHandler.writeIntegers(this.statistics);
          break;
        case "floats":
          fileHandler.writeDoubles(this.statistics);
          break;
        case "strings":
          fileHandler.writeStrings(this.statistics);
          break;
      }
      statistics.calcIntsAvg();
      statistics.calcDoublesAvg();
      fileHandler.getWriter().close();
    }

    switch (type) {
      case SHORT -> {
        statistics.printShortIntsStatistics();
        statistics.printShortDoublesStatistics();
//        statistics.printShortStringsStatistics();
      }
      case FULL -> {
        statistics.printFullIntsStatistics();
        statistics.printFullDoublesStatistics();
//        statistics.printFullStringsStatistics();
      }
    }
  }
}