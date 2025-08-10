package com._27Mikael.immersiveHealth.events;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerAttributeRetriever {

  double movementSpeed;
  double attackSpeed;
  double attackDamage;

  // retrieve player entity data from server
  // can use getCapability(), getAttributes(), and check actions
  @SubscribeEvent
  public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
    Player player = event.getEntity();
    
    // movement related events
    movementSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED).getValue();
    attackSpeed = player.getAttribute(Attributes.ATTACK_SPEED).getValue();
    attackDamage = player.getAttribute(Attributes.ATTACK_DAMAGE).getValue();

  }

  public double getMovementSpeed () {
    return movementSpeed;
  }

  public double getAttackSpeed() {
    return attackSpeed;
  }

  public double getAttackDamage() {
    return attackDamage;
  }
}
