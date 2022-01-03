package com.tyler.forgezoom;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

// We have to use the mod bus instead of the forge bus here because the event is fired on that bus
@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.MOD)
public class ClientSetup
{
	@SubscribeEvent
	public static void onClientInit(FMLClientSetupEvent event)
	{
		// Register keys on client setup
		Keybinds.registerKeys();
	}
}
