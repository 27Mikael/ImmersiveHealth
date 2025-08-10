package com._27Mikael.immersiveHealth.physiology.exhaustion;


public class ExhaustionHandler {

  
  public void Exhaustion(double currentStamina, double endurance) {
    // limit 
  }

  /**
   *
   * Endurance exhaustion logic
   * @param currentStamina
   * @return void
   */
  public boolean isExhausted(double endurance, boolean isSprinting, boolean isSitting, boolean isCrouching, boolean isJumping) {
    if (endurance == 0 && isSprinting) {
      // endurance regenerates if you sleep
      // initiate regeneration
      // endurance += recoveryIndex;
      return true;
    } else if (endurance == 0 && isSitting){
      // endurance regenerates if you eat
      // initiate regeneration
      // endurance += recoveryIndex;
      return true;
    } else if (endurance == 0 && isCrouching){
      // endurance regenerates if you sit
      // initiate regeneration
      // endurance += recoveryIndex;
      return true;
    } else if (endurance == 0 && isJumping) {
      // endurance regenerates if 
      return true;
    }
    return false;
  }


  /**
   * Stamina exhaustion logic
   * @param currentStamina
   * @return void
   */
  public void Exhausted(double currentStamina) {
    // TODO: Im
    if (currentStamina == 0) {
      // initiate the endurance system
    }
  }
}
