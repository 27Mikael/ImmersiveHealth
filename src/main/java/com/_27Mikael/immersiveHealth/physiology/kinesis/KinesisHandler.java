package com._27Mikael.immersiveHealth.physiology.kinesis;

import com.alrex.parcool.api.Stamina;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.OnlyIn;

import com._27Mikael.immersiveHealth.events.PlayerActionRetriever;
import com._27Mikael.immersiveHealth.events.PlayerAttributeRetriever;

import com._27Mikael.immersiveHealth.physiology.exhaustion.ExhaustionHandler;
import com._27Mikael.immersiveHealth.physiology.kinesis.endurance.EnduranceHandler;
import com._27Mikael.immersiveHealth.physiology.kinesis.stamina.StaminaHandler;

public class KinesisHandler {

  private final StaminaHandler staminaHandler;
  private final EnduranceHandler enduranceHandler;
  private final ExhaustionHandler exhaustionHandler;

  private final PlayerActionRetriever actionRetriever;

  public KinesisHandler(Player player, PlayerActionRetriever actionRetriever, PlayerAttributeRetriever attributeRetriever) {
    // initialize the stamina and endurance systems
    this.staminaHandler = new StaminaHandler(100.0);
    this.enduranceHandler = new EnduranceHandler(100.0);
    this.exhaustionHandler = new ExhaustionHandler();

    // use centralized retriever system
    this.actionRetriever = actionRetriever;
    
    // Inject the centralized retrievers into the handlers
    this.staminaHandler.setActionRetriever(actionRetriever);
    this.enduranceHandler.setActionRetriever(actionRetriever);
    this.enduranceHandler.setAttributeRetriever(attributeRetriever);

    processMovementLogic();
  }

  /******************************************************************************************************
   * 
   *                                   Main movement processing logic
   *
   *****************************************************************************************************/
  public void processMovementLogic() {
    double stamina = staminaHandler.getStamina();
    double endurance = enduranceHandler.getEndurance();

    if (stamina > 0) {
      // initiate the stamina system
      handleStaminaMovement(stamina);
    } else if (endurance > 0) {
      // initiate the endurance system
      handleEnduranceMovement(endurance);
      // preventStaminaRegen(); // TODO: Implement this method
    } else {
      // TODO: Player exhaustion state
      // handleExhausion();
    }
  }

  /**
   * Handle movement when running on staminaHandler
   * @param stamina stamina value
   */
  private void handleStaminaMovement(double stamina) {
    boolean isMoving = actionRetriever.isSprinting() || actionRetriever.isJumping();

    if (isMoving) {
      // Drain stamina based on movement type
      if (actionRetriever.isSprinting()) {
        staminaHandler.SprintState(stamina);
      }
      if (actionRetriever.isJumping()) {
        staminaHandler.JumpingState(stamina);
      } else {
        // Regenerate stamina when not moving
        staminaHandler.isRegenerating(stamina);
      }
    }
  }
  
  /**
   * Handle movement when running on endurance (stamina depleted)
   * @param endurance Current endurance value
   */
  private void handleEnduranceMovement(double endurance) {
    boolean isMoving = actionRetriever.isSprinting() || actionRetriever.isJumping();

    if (isMoving) {
      // Endurance drains when moving and stamina is 0
      if (actionRetriever.isSprinting()) {
        enduranceHandler.SprintState(endurance);
      } else {
        // Regenerate endurance when resting
        if (enduranceHandler.isRegenerating(endurance)) {
          // Endurance regenerates, then stamina can start regenerating 
          if (endurance > 20) {
            staminaHandler.isRegenerating(staminaHandler.getStamina());
          }
        }
      }
    }
  }

  /**
   * Handle exhaustion state (when stamina and endurance == 0)
   */
  private void handleExhausion() {
    // Player is exhausted
    // TODO: potential effects on player
    // exhaustionHandler.applyExhaustionEffects();

    // Endurance regeneration only if completely still or resting
    if (actionRetriever.isCrouching() || actionRetriever.isSleeping()) {
      enduranceHandler.isRegenerating(0);
    }
  }

  /******************************************************************************************************
   *                                                                                                    *
   *                                    PARCOOL INTERGRATION                                            *
   *                                                                                                    *
   *****************************************************************************************************/

  /**
   * Check if player can perform parkour actions
   * @param parkourCost Base stamina cost
   * @return true if parkour is allowed
   */
  public boolean canPerformParkour(int parkourCost) {
    Player player = actionRetriever.getCurrentPlayer();

    // Check for physiology energy
    boolean hasPhysiologyEnergy = staminaHandler.canPerformMovement() || enduranceHandler.getEndurance() > 0;

    // Get ParCool stamina for the player
    Stamina parCoolStamina = Stamina.get(player);
    boolean hasParCoolStamina = parCoolStamina != null && parCoolStamina.getValue() >= parkourCost && !parCoolStamina.isExhausted();

    return hasPhysiologyEnergy && hasParCoolStamina;
  }


  /**
   * Apply cost when player doest parkour
   * @param player current player
   * @param baseParkourCost cost movement
   */
  @OnlyIn(Dist.CLIENT)
  public void executeParkourMove(Player player, int baseParkourCost) {
    if (!canPerformParkour(baseParkourCost)) {
      return;
    }

    Stamina parCoolStamina = Stamina.get(player);
    if (parCoolStamina == null) return;

    // Calculate modififed cost based on physiology state
    double costModifier = staminaHandler.getParkourCostModifier();
    int finalCost = (int)(baseParkourCost * costModifier);

    //Apply cost to parcool system
    parCoolStamina.consume(finalCost);

    //Apply additional cost to Physiology system
    // TODO: to be changed in the future
    double physiologyCost = baseParkourCost * 0.5;

    if (staminaHandler.canPerformMovement()) {
      // Drain stamina 
      double newStamina = staminaHandler.getStamina() - physiologyCost;
      staminaHandler.setStamina(newStamina);
    } else {
      // Drain from endurance when stamina is depleted
      double newEndurance = enduranceHandler.getEndurance() - physiologyCost;
      enduranceHandler.setEndurance(newEndurance);
    }
  }

  /**
   * Get overall movement efficiency for ParCool integration
   * Uses your retriever system to check movement state
   * @return efficiency from 0.0 to 1.0
   */
  public double getMovementEfficiency() {
    double staminaPercent = staminaHandler.getStaminaPercentage();
    double endurancePercent = enduranceHandler.getEndurancePercentage();
    
    // Factor in current movement state from your retrievers
    boolean isActivelyMoving = actionRetriever.isSprinting() || actionRetriever.isJumping();
    
    if (staminaPercent > 0) {
      return staminaPercent; // Full efficiency when stamina available
    } else if (endurancePercent > 0) {
      double baseEfficiency = endurancePercent * 0.7; // Reduced efficiency on endurance
      return isActivelyMoving ? baseEfficiency * 0.8 : baseEfficiency; // Further reduction when actively moving
    } else {
      return 0.1; // Minimal efficiency when exhausted
    }
  }

  /**
   * Check if parkour should be completely disabled based on your system state
   * @return true if parkour should be blocked
   */
  public boolean isParkourBlocked() {
    // Block parkour if exhausted and actively moving
    boolean isExhausted = staminaHandler.getStamina() <= 0 && enduranceHandler.getEndurance() <= 0;
    boolean isActivelyMoving = actionRetriever.isSprinting() || actionRetriever.isJumping();
    
    return isExhausted && isActivelyMoving;
  }

  // Getters for other systems to access
  public StaminaHandler getStaminaHandler() {
    return staminaHandler;
  }

  public EnduranceHandler getEnduranceHandler() {
    return enduranceHandler;
  }

  public ExhaustionHandler getExhaustionHandler() {
    return exhaustionHandler;
  }

  public PlayerActionRetriever getActionRetriever() {
    return actionRetriever;
  }
}
