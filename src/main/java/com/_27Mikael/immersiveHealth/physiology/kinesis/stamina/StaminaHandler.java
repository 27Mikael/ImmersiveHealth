package com._27Mikael.immersiveHealth.physiology.kinesis.stamina;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.entity.player.Player;

import com._27Mikael.immersiveHealth.events.PlayerActionRetriever;
import com._27Mikael.immersiveHealth.events.PlayerAttributeRetriever;


public class StaminaHandler {

  private double stamina;
  private double maxStamina = 100.0;

  // actions
  PlayerActionRetriever action = new PlayerActionRetriever();

  public StaminaHandler(double stamina) {
    this.stamina = stamina;
  }

  public double getStamina() {
    return stamina;
  }

  public void setStamina(double stamina) {
    this.stamina = Math.max(0, Math.min(maxStamina, stamina));
  }

  public double getMaxStamina() {
    return maxStamina;
  }

  /**
   *  Check if player can perform regular movement actions
   *  @return true if currentStamina > 0
   */
  public boolean canPerformMovement() {
    return stamina > 0;
  }

  /**
   * Stamina regeneration logic
   * @param currentStamina current currentStamina value
   * @return void
   */
  public void isRegenerating(double currentStamina) {
    // TODO: Implement Musculature.recoveryIndex
    // double Musculature.recoveryIndex = recoveryIndex;
    if (currentStamina <= 0) {
      // currentStamina += recoveryIndex;
      setStamina(currentStamina + 1.0f);
    }
  }

  /**
   * Stamina drain logic for running
   * @param currentStamina
   * @return newStamina
   */
  public double SprintState(double currentStamina) {
    // TODO: Implement sprintIndex
    // double Musculature.sprintIndex = sprintIndex;
    if (action.isSprinting() && currentStamina > 0) {
      double newStamina =  currentStamina - 2f;
      setStamina(newStamina);
      return newStamina;
    }
    return currentStamina;
  }

  /**
   * Stamina drain logic for jumping
   * @param currentStamina
   * @return newStamina
   */
  public double JumpingState(double currentStamina) {
    // TODO: Implement jumpIndex
    // double Musculature.jumpIndex = jumpIndex;
    if (action.isJumping() && currentStamina > 0) {
      double newStamina = currentStamina - 2f;
      setStamina(newStamina);
      return newStamina;
    }
    return currentStamina;
  }
 

  /**
   * Stamina drain logic for crouching
   * @param currentStamina
   * @return newStamina
   */
  public double CrouchingState(double currentStamina) {
    // TODO: Implement recoveryIndex
    // double Musculature.recoveryIndex = recoveryIndex;
    if (action.isCrouching()) {
      double newStamina = currentStamina + 2f;
      setStamina(newStamina);
      return newStamina;
    }
    return currentStamina;
  }

  /**
   * Calculate parkour cost modifier based on stamina level
   * @return cost multiplier (1.0 = normal, higher = more expensive)
   */
  public double getParkourCostModifier() { 
    if (stamina <= 0 ) {
      return 2.0;
    } else if (stamina < (maxStamina * 0.3)) {
      return 1.5;
    }
    return 1.0;
  }

  /**
   * Get stamina percentage for UI or other systems
   * @ return percentage from 0.0. to 1.0
   */
  public double getStaminaPercentage() {
    return stamina / maxStamina;
  }
}


