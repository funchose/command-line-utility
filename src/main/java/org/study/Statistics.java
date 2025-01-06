package org.study;

public class Statistics {
  private int numberOfStrings = 0;
  private int numberOfInts = 0;
  private int numberOfDoubles = 0;
  private double doublesMin;
  private double doublesMax;
  private double doublesSum = 0;
  private double doublesAvg = 0;
  private int intsMin;
  private int intsMax;
  private int intsSum = 0;
  private double intsAvg = 0;
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
  public double getDoublesMin() {
    return doublesMin;
  }

  public void setDoublesMin(double doublesMin) {
    this.doublesMin = doublesMin;
  }

  public double getDoublesMax() {
    return doublesMax;
  }

  public void setDoublesMax(double doublesMax) {
    this.doublesMax = doublesMax;
  }

  public double getDoublesSum() {
    return doublesSum;
  }

  public void setDoublesSum(double doublesSum) {
    this.doublesSum = doublesSum;
  }

  public double getDoublesAvg() {
    return doublesAvg;
  }

  public void setDoublesAvg(double doublesAvg) {
    this.doublesAvg = doublesAvg;
  }

  public int getNumberOfStrings() {
    return numberOfStrings;
  }

  public void setNumberOfStrings(int numberOfStrings) {
    this.numberOfStrings = numberOfStrings;
  }

  public int getNumberOfInts() {
    return numberOfInts;
  }

  public void setNumberOfInts(int numberOfInts) {
    this.numberOfInts = numberOfInts;
  }

  public int getNumberOfDoubles() {
    return numberOfDoubles;
  }

  public void setNumberOfDoubles(int numberOfFloats) {
    this.numberOfDoubles = numberOfFloats;
  }

  public int getIntsMin() {
    return intsMin;
  }

  public void setIntsMin(int intsMin) {
    this.intsMin = intsMin;
  }

  public int getIntsMax() {
    return intsMax;
  }

  public void setIntsMax(int intsMax) {
    this.intsMax = intsMax;
  }

  public int getIntsSum() {
    return intsSum;
  }

  public void setIntsSum(int intsSum) {
    this.intsSum = intsSum;
  }

  public int getMinStrSize() {
    return minStrSize;
  }

  public void setMinStrSize(int minStrSize) {
    this.minStrSize = minStrSize;
  }

  public int getMaxStrSize() {
    return maxStrSize;
  }

  public void setMaxStrSize(int maxStrSize) {
    this.maxStrSize = maxStrSize;
  }

  public void collectIntsStatistics(Integer integer) {
    setNumberOfInts(getNumberOfInts() + 1);
    setIntsSum(getIntsSum() + integer);
    if (integer > getIntsMax()) {
      setIntsMax(integer);
    }
    if (integer < getIntsMin()) {
      setIntsMin(integer);
    }
  }

  public void calcIntsAvg() {
    intsAvg = (double) intsSum / numberOfInts;
  }

  public void printShortIntsStatistics() {
    System.out.printf("Number of integers: %d%n", numberOfInts);
  }

  public void printFullIntsStatistics() {
    printShortIntsStatistics();
    System.out.printf("Min integer: %d%nMax integer: %d%nSum: %d%nAverage: %.2f%n",
        intsMin, intsMax, intsSum, intsAvg);
  }
  public void collectDoublesStatistics(Double doubleNum) {
    setNumberOfDoubles(getNumberOfDoubles() + 1);
    setDoublesSum(getDoublesSum() + doubleNum);
    if (doubleNum > getDoublesMax()) {
      setDoublesMax(doubleNum);
    }
    if (doubleNum < getDoublesMin()) {
      setDoublesMin(doubleNum);
    }
  }

  public void calcDoublesAvg() {
    doublesAvg = doublesSum / numberOfDoubles;
  }

  public void printShortDoublesStatistics() {
    System.out.printf("Number of floats: %d%n", numberOfDoubles);
  }

  public void printFullDoublesStatistics() {
    printShortDoublesStatistics();
    System.out.printf("Min float: %.2f%nMax float: %.2f%nSum: %.2f%nAverage: %.2f%n",
        doublesMin, doublesMax, doublesSum, doublesAvg);

  }
}
