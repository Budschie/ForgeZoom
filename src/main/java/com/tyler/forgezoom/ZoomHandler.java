package com.tyler.forgezoom;

import net.minecraftforge.client.event.FOVModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ZoomHandler {
	private static final float defaultFOVModifier = 4F;
	private static final float minFOVModifier = 50F;
	private static final float maxFOVModifier = 1.0F;

	private static float currentFOVModifier = defaultFOVModifier;
	//private static float userDefaultFOV = -1;
	private static boolean zoomEnabled = false;
	private static final float fovStep = 1F;

	public static void enableZoom() {
		resetZoom();
		zoomEnabled = true;
	}

	public static void disableZoom() {
		zoomEnabled = false;
		resetZoom();
	}

	public static void incrementZoom() {
		float newFOVModifier = currentFOVModifier + fovStep;
		if (newFOVModifier >= minFOVModifier) return;
		currentFOVModifier += fovStep;
	}

	public static void decrementZoom() {
		float newFOVModifier = currentFOVModifier - fovStep;
		if (newFOVModifier <= maxFOVModifier) return;
		currentFOVModifier -= fovStep;
	}

	public static void resetZoom() {
		currentFOVModifier = defaultFOVModifier;
	}

	@SubscribeEvent
	public void updateFOV(FOVModifierEvent event) {
		/*if  (!zoomEnabled && userDefaultFOV == -1) {
			userDefaultFOV = event.getFov();
		}*/
		if (zoomEnabled) {
			event.setNewfov(event.getFov()/currentFOVModifier);
		}
	}
}
