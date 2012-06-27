package net.minecraft.src.BAPI.interfaces;

import net.minecraft.client.Minecraft;

public interface IGameOverlay
{
	/**
	 * This gets called every ingame tick and is used to render custom overlay for the player
	 * @param mc				Minecraft instance
	 * @param height			window height
	 * @param width				window width
	 * @param partialTicks		partial ticks
	 * @param isDisplayingGUI	true if there is any GUI open
	 */
	public void render(Minecraft mc, int height, int width, float partialTicks, boolean isDisplayingGUI);
}
