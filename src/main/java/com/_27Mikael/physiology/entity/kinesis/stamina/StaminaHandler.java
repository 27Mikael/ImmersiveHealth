package  com._27Mikael.physiology.kinesis.stamina;


import com._27Mikael.physiology.events.PlayerAttributeRetriever;
import com._27Mikael.physiology.events.PlayerActionRetriever;

public class StaminaHandler {

  private double stamina;

  public void StaminaHandler(double stamina) {
    this.stamina = stamina;
  }

  /**
   * Stamina regeneration logic
   * @param stamina
   * @return void
   */
  public void Regeneration(double stamina) {
    // TODO: Implement Musculature.recoveryIndex
    // double Musculature.recoveryIndex = recoveryIndex;
    if (stamina == 0) {
      // stamina += recoveryIndex;
      // initiate regeneration
    }
  }

  /**
   * Stamina exhaustion logic
   * @param stamina
   * @return void
   */
  public void Exhausted(double stamina) {
    // TODO: Im
    if (stamina == 0) {
      // initiate the endurance system
    }
  }

  /**
   * Stamina drain logic for running
   * @param stamina
   * @return newStamina
   */
  public double SprintState(double stamina) {
    // TODO: Implement sprintIndex
    // double Musculature.sprintIndex = sprintIndex;
    if (PlayerActionRetriever.isSprinting) {
      double newStamina =  stamina - 2f;
      return newStamina;

    }
  }

  /**
   * Stamina drain logic for jumping
   * @param stamina
   * @return newStamina
   */
  public double JumpingState(double stamina) {
    // TODO: Implement jumpIndex
    // double Musculature.jumpIndex = jumpIndex;
    if (PlayerActionRetriever.isJumping) {
      double newStamina = stamina - 2f;
      return newStamina;
    }
  }
 

  /**
   * Stamina drain logic for crouching
   * @param stamina
   * @return newStamina
   */
  public double CrounchingState(double stamina) {
    //TODO: Implement recoveryIndex
    // double Musculature.recoveryIndex = recoveryIndex;
    if (PlayerActionRetriever.recoveryIndex = recoveryIndex) {
      double newStamina = stamina + 2f;
      return newStamina;
    }
  }
}


