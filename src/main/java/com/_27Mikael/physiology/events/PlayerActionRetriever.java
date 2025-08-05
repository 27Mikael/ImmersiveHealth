package com._27Mikael.physiology.events;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerActionTracker {
  private boolean isSprinting;
  private boolean isJumping;
  private boolean isCrouching;
  private boolean isSitting;

  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    Player player = event.player;

    isSprinting = player.isSprinting();
    isCrouching = player.isCrouching();

    if (player.level().isClientSide && player.input != null) {
        isJumping = player.input.jumping;
    }
    isSitting = player.isPassenger();

    // debug output
    // System.out.println("Jumping: " + isJumping + ", Sprinting: " + isSprinting);
  }

  public boolean isCrouching() {
      return isCrouching;
  }

  public boolean isJumping() {
      return isJumping;
  }

  public boolean isSprinting() {
      return isSprinting;
  }

  public boolean isSitting() {
      return isStitting;
  }
}
