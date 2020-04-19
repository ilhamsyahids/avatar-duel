package com.avatarduel.model.card;

public interface Powerable {

  /**
   * 
   * @return the power
   */
  int getPower();

  /**
   * Power player must greater than or equal this power
   * 
   * @return is the card can summon
   */
  boolean isCanSummon();
}