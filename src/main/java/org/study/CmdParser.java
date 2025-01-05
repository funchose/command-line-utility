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
//    } if (cmd.hasOption("s")) {
//      fileHandler.showShortStatistics();
//    } if (cmd.hasOption("f")) {
//      fileHandler.showFullStatistics();
    }
  }

  public void parseCommand() throws IOException {
    parseFlags();
    statistics = new Statistics();
    for (String file : files) {
      setFileHandler(new FileHandler());
      fileHandler.readFile(file);
      fileHandler.getScanner().close();
      if (!isAppendMode()) {
        statistics = new Statistics();
      }
      for (String list : fileHandler.existingLists) {
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
        fileHandler.getWriter().close();
      }
    }
    statistics.printFullIntsStatistics();
  }
}
