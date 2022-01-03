package com.tyler.forgezoom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("forgezoom")
public class ForgeZoom {
	private static final Logger LOGGER = LogManager.getLogger();
	
	public ForgeZoom() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onDedicatedServerSetup);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void onDedicatedServerSetup(final FMLDedicatedServerSetupEvent event) 
	{
		LOGGER.warn("This mod is client-side only. Keeping this mod does not result in a crash, although it is unnecessary to keep it in your server's mod directory.");
	}
}
