package com._27Mikael.immersiveHealth.physiology.exhaustion;

import com._27Mikael.immersiveHealth.events.PlayerActionRetriever;
import com._27Mikael.immersiveHealth.events.PlayerAttributeRetriever;


public class ExhaustionHandler {

  
  public Exhaustion(double currentStamina, double endurance) {
    // limit 
  }

  /**
   *
   * Endurance exhaustion logic
   * @param currentStamina
   * @return void
   */
  public boolean isExhausted(double endurance) {
    if (endurance == 0 && isSprinting) {
      // endurance regenerates if you sleep
      // initiate regeneration
      // endurance += recoveryIndex;
    } else if (endurance == 0 && isSitting){
      // endurance regenerates if you eat
      // initiate regeneration
      // endurance += recoveryIndex;
    } else if (endurance == 0 && isCrouching){
      // endurance regenerates if you sit
      // initiate regeneration
      // endurance += recoveryIndex;
    } else  if (endurance == 0 && isJumping) {
      // endurance regenerates if 
    }
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
