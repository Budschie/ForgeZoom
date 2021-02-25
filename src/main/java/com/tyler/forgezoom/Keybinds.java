package com.tyler.forgezoom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftGame;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class Keybinds {
	private static final Logger LOGGER = LogManager.getLogger();
	
	public static final KeyBinding KEY_ZOOM = new KeyBinding("key.forgezoom.zoom", GLFW.GLFW_KEY_C, "key.categories.forgezoom");
	public static final KeyBinding KEY_INCREASE_ZOOM = new KeyBinding("key.forgezoom.increase", GLFW.GLFW_KEY_O, "key.categories.forgezoom");
	public static final KeyBinding KEY_DECREASE_ZOOM = new KeyBinding("key.forgezoom.decrease", GLFW.GLFW_KEY_P, "key.categories.forgezoom");
	
	@SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
	public void keyInputEvent(InputEvent.KeyInputEvent event) {
		Minecraft instance = Minecraft.getInstance();
		GameRenderer renderer = instance.gameRenderer;
		if (instance == null || Minecraft.getInstance().currentScreen != null) {
			LOGGER.info("KeyInputEvent returning because instance is null or currentScreen is not(?) null");
			return;
		}
		//handle keypress, remember to check which key it is
		instance.player.sendChatMessage("key event: action="+event.getAction()+", key="+event.getKey());
		//LOGGER.info("key event: action="+event.getAction()+", key="+event.getKey());
		EntityViewRenderEvent.FOVModifier modifier = new EntityViewRenderEvent.FOVModifier(renderer, renderer.getActiveRenderInfo(), Minecraft.getInstance().getRenderPartialTicks(), 110);
		if (event.getKey() == GLFW.GLFW_KEY_C) {
			instance.player.sendChatMessage("C");
			if (event.getAction() == GLFW.GLFW_PRESS) {
				instance.player.sendChatMessage("Setting FOV to 50");
				modifier.setFOV(50);
				
			}
			if (event.getAction() == GLFW.GLFW_RELEASE) {
				instance.player.sendChatMessage("Setting FOV to 110");
				modifier.setFOV(110);
			}
			
		}
		
		
	}
	public static void registerKeys() {
		ClientRegistry.registerKeyBinding(KEY_ZOOM);
		ClientRegistry.registerKeyBinding(KEY_INCREASE_ZOOM);
		ClientRegistry.registerKeyBinding(KEY_DECREASE_ZOOM);
		LOGGER.info("Keys registered");
		
	}
}