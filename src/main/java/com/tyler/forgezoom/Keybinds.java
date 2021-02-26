package com.tyler.forgezoom;
import net.minecraft.client.*;
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
import org.apache.logging.log4j.*;
import org.lwjgl.glfw.*;

@OnlyIn(Dist.CLIENT)
public class Keybinds {
	private static final Logger LOGGER = LogManager.getLogger();

	public static final KeyBinding KEY_ZOOM = new KeyBinding("key.forgezoom.zoom", GLFW.GLFW_KEY_C, "key.categories.forgezoom");

	@SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
	public void keyInputEvent(InputEvent.KeyInputEvent event) {
		if (event.getKey() != GLFW.GLFW_KEY_C) return;

		if (event.getAction() == GLFW.GLFW_PRESS) {
			ZoomHandler.enableZoom();
		}
		if (event.getAction() == GLFW.GLFW_RELEASE) {
			ZoomHandler.disableZoom();
		}
	}

	@SubscribeEvent
	public void mouseScrollEvent(InputEvent.MouseScrollEvent event) {
		if (KEY_ZOOM.isKeyDown()) {
			double wheelDelta = event.getScrollDelta();
			if (wheelDelta < 0) ZoomHandler.decrementZoom();
			if (wheelDelta > 0) ZoomHandler.incrementZoom();
			event.setCanceled(true);
		}
	}

	public static void registerKeys() {
		ClientRegistry.registerKeyBinding(KEY_ZOOM);
		LOGGER.info("Zoom key registered");
	}
}