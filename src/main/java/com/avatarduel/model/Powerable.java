package com.avatarduel.model;

public interface Powerable {

  /**
   * 
   * @return the power
   */
  public int getPower();

  /**
   * Power player must greater than or equal this power
   * 
   * @return is the card can summon
   */
  public boolean isCanSummon();
}