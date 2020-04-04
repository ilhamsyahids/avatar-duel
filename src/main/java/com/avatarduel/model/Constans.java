package com.avatarduel.model;

public enum Constans {
  MAXCARDS(50),
  MAXAREAFIELD(4),
  MAXHANDCARDS(9);

  public final int value;
 
  private Constans(int value) {
      this.value = value;
  }

  public int getValue() {
    return value;
  }
}