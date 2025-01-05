package org.study;

public class Statistics {
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

  public int getNumberOfFloats() {
    return numberOfFloats;
  }

  public void setNumberOfFloats(int numberOfFloats) {
    this.numberOfFloats = numberOfFloats;
  }

  public int getFloatsMin() {
    return floatsMin;
  }

  public void setFloatsMin(int floatsMin) {
    this.floatsMin = floatsMin;
  }

  public int getFloatsMax() {
    return floatsMax;
  }

  public void setFloatsMax(int floatsMax) {
    this.floatsMax = floatsMax;
  }

  public double getFloatsSum() {
    return floatsSum;
  }

  public void setFloatsSum(double floatsSum) {
    this.floatsSum = floatsSum;
  }

  public double getFloatsAvg() {
    return floatsAvg;
  }

  public void setFloatsAvg(double floatsAvg) {
    this.floatsAvg = floatsAvg;
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

  private int numberOfStrings = 0;
  private int numberOfInts = 0;
  private int numberOfFloats = 0;
  private int floatsMin = 0;
  private int floatsMax = 0;
  private double floatsSum = 0;
  private double floatsAvg = 0;
  private int intsMin = 0;
  private int intsMax = 0;
  private int intsSum = 0;
  private double intsAvg = 0;
  private int minStrSize = 0;
  private int maxStrSize = 0;

  public void getIntsStatistics(Integer integer) {
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
    System.out.printf("Min integer: %d%nMax integer: %d%nSum: %d%nAverage: %f%n",
        intsMin, intsMax, intsSum, intsAvg);
  }
}
