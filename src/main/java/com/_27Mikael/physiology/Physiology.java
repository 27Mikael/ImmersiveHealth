package com._27Mikael.physiology;

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

@Mod(Physiology.MOD_ID)
public class Physiology {
  public static final String MOD_ID = "physiology";
  public static final Logger LOGGER = LogUtils.getLogger();

  public Physiology() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    modEventBus.addListener(this::commonSetup);

    MinecraftForge.EVENT_BUS.register(this);

    // retrieve player events from server

    // pass the player events to their respective systems
    // kinesis systems
    // metabolism
    // muscular systems
  
    // pass the results off to the capability systems
    // 

  }

  private void commonSetup(final FMLCommonSetupEvent event) {
    LOGGER.info("Physiology common setup complete.");
  }

  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    LOGGER.info("Physiology server starting.");
  }

  @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
  public static class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
      LOGGER.info("Physiology client setup complete.");
    }
  }
}
