package net.minecraft.src.BAPI;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.BAPI.interfaces.IBiome;
import net.minecraft.src.BAPI.interfaces.ICreativeHandler;
import net.minecraft.src.BAPI.interfaces.IGameOverlay;
import net.minecraft.src.BAPI.interfaces.INBT;
import net.minecraft.src.BAPI.interfaces.IPlacable;

public class BAPI
{
	public static String getCustomStringColor()
	{
		return Main.getSColor();
	}
	
	public static void registerBiomeHandler(IBiome handler)
	{
		FMLClientHandler.instance().addBiomeToDefaultWorldGenerator(handler.getBiome());
		Main.biomeHandlers.add(handler);
		Main.stringer("Registered new biome handler " + handler.getClass().getSimpleName());
	}

	public static void registerBiomeHandler(BiomeGenBase biome)
	{
		if (biome instanceof IBiome)
		{
			IBiome handler = ((IBiome) biome);
			registerBiomeHandler(handler);
		} else
		{
			throw new BAPIException("Biome handler not implemented inside " + biome.getClass().getSimpleName() + " class.");
		}
	}

	public static void registerCreativeHandler(ICreativeHandler handler)
	{
		Main.creativeHandlers.add(handler);
		Main.stringer("Registered new creative handler " + handler.getClass().getSimpleName());
	}

	public static void registerGameOverlay(IGameOverlay handler)
	{
		Main.gameOverlays.add(handler);
		Main.stringer("Registered new game overlay " + handler.getClass().getSimpleName());
	}

	public static void registerModAuthor(String modname, String author)
	{
		Main.authors[Main.lastIndex][0] = modname;
		Main.authors[Main.lastIndex][1] = author;
		Main.lastIndex++;
	}

	public static void registerNBT(INBT handler)
	{
		if (!MathHelper.stringNullOrLengthZero(handler.nbtName()))
		{
			Main.nbtHandlers.add(handler);
			Main.stringer("Registered new NBT handler " + handler.getClass().getSimpleName());
		} else
		{
			throw new BAPIException("Invalid NBT name parameter for handler " + handler.getClass().getSimpleName());
		}
	}

	public static void registerPlacableHandler(IPlacable handler)
	{
		Main.placableHandlers.add(handler);
		Main.stringer("Registered new placable handler " + handler.getClass().getSimpleName());
	}

}
