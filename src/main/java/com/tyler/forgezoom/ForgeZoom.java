package com.tyler.forgezoom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("forgezoom")
public class ForgeZoom {
	private static final Logger LOGGER = LogManager.getLogger();
	
	public ForgeZoom() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void doClientStuff(final FMLClientSetupEvent event) {
		Keybinds.registerKeys();
		// LOGGER.info("Got game settings {}", Minecraft.getInstance().options);
		MinecraftForge.EVENT_BUS.register(new Keybinds());
		MinecraftForge.EVENT_BUS.register(new ZoomHandler());
	}
}
