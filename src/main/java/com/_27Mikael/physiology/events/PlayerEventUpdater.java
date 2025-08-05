package  com._27Mikael.physiology.events;

import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.EventBus;

public class PlayerEventUpdater {

  public PlayerEventUpdater() {
    //
  }

  @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled)
  public void onPlayerTick(LivingEvent.LivingUpdateEvent event) {
    // Check if event is player event and update 
  }
}
