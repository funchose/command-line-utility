package org.study;

public class Statistics {
  private int numberOfStrings;
  private int numberOfInts;
  private int numberOfDoubles;
  private double doublesMin;
  private double doublesMax;
  private double doublesSum;
  private double doublesAvg;
  private int intsMin;
  private int intsMax;
  private int intsSum;
  private double intsAvg;
  private int minStrSize;
  private int maxStrSize;

  public Statistics(FileHandler fileHandler) {
    if (fileHandler.getExistingLists().contains("integers")) {
      this.intsMin = fileHandler.getIntegers().getFirst();
      this.intsMax = fileHandler.getIntegers().getFirst();
    }
    if (fileHandler.getExistingLists().contains("floats")) {
      this.doublesMin = fileHandler.getDoubles().getFirst();
      this.doublesMax = fileHandler.getDoubles().getFirst();
    }
    if (fileHandler.getExistingLists().contains("strings")) {
      this.minStrSize = fileHandler.getStrings().getFirst().length();
      this.maxStrSize = fileHandler.getStrings().getFirst().length();
    }
  }

  public void collectIntsStatistics(Integer integer) {
    numberOfInts++;
    intsSum += integer;
    if (integer > intsMax) {
      intsMax = integer;
    }
    if (integer < intsMin) {
      intsMin = integer;
    }
  }

  public void collectDoublesStatistics(Double doubleNum) {
    numberOfDoubles++;
    doublesSum += doubleNum;
    if (doubleNum > doublesMax) {
      doublesMax = doubleNum;
    }
    if (doubleNum < doublesMin) {
      doublesMin = doubleNum;
    }
  }

  public void collectStringsStatistics(String string) {
    numberOfStrings++;
    if (string.length() > maxStrSize) {
      maxStrSize = string.length();
    }
    if (string.length() < minStrSize) {
      minStrSize = string.length();
    }
  }

  public void calculateAvg() {
    calcIntsAvg();
    calcDoublesAvg();
  }

  private void calcIntsAvg() {
    intsAvg = (double) intsSum / numberOfInts;
  }

  private void calcDoublesAvg() {
    doublesAvg = doublesSum / numberOfDoubles;
  }

  public void printShortIntsStatistics() {
    System.out.printf("Number of integers: %d%n", numberOfInts);
  }

  public void printShortDoublesStatistics() {
    System.out.printf("Number of floats: %d%n", numberOfDoubles);
  }

  public void printShortStringsStatistics() {
    System.out.printf("Number of strings: %d%n", numberOfStrings);
  }

  public void printFullIntsStatistics() {
    printShortIntsStatistics();
    System.out.printf("Min integer: %d%nMax integer: %d%nSum: %d%nAverage: %.2f%n",
        intsMin, intsMax, intsSum, intsAvg);
  }

  public void printFullDoublesStatistics() {
    printShortDoublesStatistics();
    System.out.printf("Min float: %.2f%nMax float: %.2f%nSum: %.2f%nAverage: %.2f%n",
        doublesMin, doublesMax, doublesSum, doublesAvg);

  }

  public void printFullStringsStatistics() {
    printShortStringsStatistics();
    System.out.printf("Min string length: %d%nMax string length: %d%n",
        minStrSize, maxStrSize);
  }
}
