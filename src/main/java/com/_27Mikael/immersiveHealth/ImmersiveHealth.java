package com._27Mikael.immersiveHealth;

import com.mojang.logging.LogUtils;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.Logger;

import com._27Mikael.immersiveHealth.physiology.Physiology;
import com._27Mikael.immersiveHealth.events.PlayerActionRetriever;
import com._27Mikael.immersiveHealth.events.PlayerAttributeRetriever;
import com._27Mikael.immersiveHealth.events.PlayerEventUpdater;

@Mod(ImmersiveHealth.MOD_ID)
public class ImmersiveHealth {
  public static final String MOD_ID = "immersivehealth";
  public static final Logger LOGGER = LogUtils.getLogger();
  
  private Physiology physiologySystem;
  private PlayerActionRetriever playerActionRetriever;
  private PlayerAttributeRetriever playerAttributeRetriever;
  private PlayerEventUpdater playerEventUpdater;

  public ImmersiveHealth() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    modEventBus.addListener(this::commonSetup);

    MinecraftForge.EVENT_BUS.register(this);

    // Initialize and register event handlers FIRST (so they can be used by physiology system)
    playerActionRetriever = new PlayerActionRetriever();
    playerAttributeRetriever = new PlayerAttributeRetriever();
    playerEventUpdater = new PlayerEventUpdater();
    
    // Register event handlers with Forge event bus
    MinecraftForge.EVENT_BUS.register(playerActionRetriever);
    MinecraftForge.EVENT_BUS.register(playerAttributeRetriever);
    MinecraftForge.EVENT_BUS.register(playerEventUpdater);
    
    // Initialize physiology system with the event handlers
    physiologySystem = new Physiology();

    // retrieve player events from server

    // pass the player events to their respective systems
    // kinesis systems
    // metabolism
    // muscular systems
  
    // pass the results off to the capability systems
    // 

  }

  private void commonSetup(final FMLCommonSetupEvent event) {
    // Initialize physiology system
    physiologySystem.initialize();
    LOGGER.info("ImmersiveHealth common setup complete.");
  }

  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    LOGGER.info("ImmersiveHealth server starting.");
  }

  @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
  public static class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
      LOGGER.info("ImmersiveHealth client setup complete.");
    }
  }
  
  // Getters for other systems to access centralized event handlers
  public PlayerActionRetriever getPlayerActionRetriever() {
    return playerActionRetriever;
  }
  
  public PlayerAttributeRetriever getPlayerAttributeRetriever() {
    return playerAttributeRetriever;
  }
  
  public PlayerEventUpdater getPlayerEventUpdater() {
    return playerEventUpdater;
  }
  
  public Physiology getPhysiologySystem() {
    return physiologySystem;
  }
}
