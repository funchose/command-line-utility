package org.study;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.commons.cli.*;

public class CmdParser {
  private final Options options = new Options();
  private final CommandLine cmd;
  private final FileHandler fileHandler;
  private final List<String> files;

  public CmdParser(String[] args) throws ParseException {
    createCmdOptions();
    CommandLineParser parser = new DefaultParser();
    this.cmd = parser.parse(options, args);
    this.files = cmd.getArgList();
    this.fileHandler = new FileHandler();
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
//    } if (cmd.hasOption("a")) {
//      fileHandler.chooseAppendMode();
//    } if (cmd.hasOption("s")) {
//      fileHandler.showShortStatistics();
//    } if (cmd.hasOption("f")) {
//      fileHandler.showFullStatistics();
    } else {
      //fileHandler.chooseOverwritingMode();
    }
  }


  public void work() throws IOException {
    for (String file : files) {
      parseFlags();
      fileHandler.readFile(file);
      if (!fileHandler.getIntegers().isEmpty()) {
        String filename = fileHandler.getPrefix() + "integers.txt";
        try {
          fileHandler.createFile(fileHandler.getDirectoryPath(), filename);
        } catch (Exception e) {
          System.out.println("IO exception occurred");
        }
        fileHandler.setWriter(new FileWriter(
            fileHandler.getDirectoryPath() + filename,true));
        fileHandler.writeIntegers();
      }
      if (!fileHandler.getDoubles().isEmpty()) {
        String filename = fileHandler.getPrefix() + "floats.txt";
        try {
          fileHandler.createFile(fileHandler.getDirectoryPath(), filename);
        } catch (Exception e) {
          System.out.println("IO exception occurred");
        }
        fileHandler.setWriter(new FileWriter(
            fileHandler.getDirectoryPath() + filename, true));
        fileHandler.writeDoubles();
      }
      if (!fileHandler.getStrings().isEmpty()) {
        String filename = fileHandler.getPrefix() + "strings.txt";
        try {
          fileHandler.createFile(fileHandler.getDirectoryPath(), filename);
        } catch (Exception e) {
          System.out.println("IO exception occurred");
        }
        fileHandler.setWriter(new FileWriter(
            fileHandler.getDirectoryPath() + filename, true));
        fileHandler.writeStrings();
      }
    }
  }
}
