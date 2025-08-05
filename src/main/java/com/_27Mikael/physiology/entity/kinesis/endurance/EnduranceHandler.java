package  com._27Mikael.physiology.kinesis.endurance;


import com._27Mikael.physiology.events.PlayerAttributeRetriever;
import com._27Mikael.physiology.events.PlayerActionRetriever;

public class EnduranceHandler {

  // attributes
  private double endurance;
  private double attackSpeed;
  private double attackDamage;
  private double movementSpeed;


  // actions
  private boolean isSitting;
  private boolean isJumping;
  private boolean isSprinting;
  private boolean isCrouching;

  public void EnduranceHandler(double endurance) {
    this.endurance = endurance;
    this.attackSpeed = attackSpeed;
    this.attackDamage = attackDamage;
    this.movementSpeed = movementSpeed;
  }

  /**
   * Stamina regeneration logic
   * @param stamina
   * @return void
   */
  public boolean isRegenerating(double endurance) {
    // TODO: Implement Musculature.recoveryIndex
    // double Musculature.recoveryIndex = recoveryIndex;
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
   *
   * Endurance exhaustion logic
   * @param stamina
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
   * Endurance drain logic for running
   * @param endurance
   * @return newEndurance
   */
  public double Sprint(double endurance, double movementSpeed) {
    // TODO: Implement sprintIndex
    // double Musculature.sprintIndex = sprintIndex;
    if (PlayerActionRetriever.isSprinting) {
      double newEndurance =  endurance - 2f;
      
      return newEndurance;
    }

  }

  /**
   * Endurance drain logic for jumping
   * @param endurance
   * @return newEndurance
   */
  public double JumpingState(double endurance) {
    // TODO: Implement jumpIndex
    // double Musculature.jumpIndex = jumpIndex;
    if (PlayerActionRetriever.isJumping) {
      double newEndurance = endurance - 2f;
      return newEndurance;
    }
  }
 

  /**
   * Endurance drain logic for crouching
   * @param endurance
   * @return newEndurance
   */
  public double CrounchingState(double endurance) {
    //TODO: Implement recoveryIndex
    // double Musculature.recoveryIndex = recoveryIndex;
    if (PlayerActionRetriever.recoveryIndex = recoveryIndex) {
      double newEndurance = endurance + 2f;
      return newEndurance;
    }
  }
}
