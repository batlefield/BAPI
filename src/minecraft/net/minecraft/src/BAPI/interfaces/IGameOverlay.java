package net.minecraft.src.BAPI.interfaces;

import net.minecraft.client.Minecraft;

public interface IGameOverlay
{
	public void render(Minecraft mc, int height, int width, float partialTicks, boolean IsDisplayingGUI);
}
