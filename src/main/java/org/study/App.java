package org.study;

import java.io.IOException;
import org.apache.commons.cli.ParseException;

public class App {
  public static void main(String[] args) {

    try {
      CmdParser parser = new CmdParser(args);
      parser.work();
    } catch (ParseException | IOException e) {
      System.out.println(e.getLocalizedMessage());
    }
  }
}
