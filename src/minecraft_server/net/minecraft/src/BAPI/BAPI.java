package net.minecraft.src.BAPI;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MathHelper;
import net.minecraft.src.mod_BAPI;
import net.minecraft.src.BAPI.interfaces.IBiome;
import net.minecraft.src.BAPI.interfaces.ICreativeHandler;
import net.minecraft.src.BAPI.interfaces.INBT;
import net.minecraft.src.BAPI.interfaces.IPlacable;

public class BAPI
{
    public static void registerBiomeHandler(IBiome handler)
    {
        Main.biomeHandlers.add(handler);
        Main.stringer("Registered new biome handler " + handler.getClass().getSimpleName());
    }

    public static void registerBiomeHandler(BiomeGenBase biome)
    {
        if (biome instanceof IBiome)
        {
            IBiome handler = ((IBiome)biome);
            Main.biomeHandlers.add(handler);
            Main.stringer("Registered new biome " + biome.getClass().getSimpleName());
        }
        else
        {
            throw new BAPIException("Biome handler not implemented inside " + biome.getClass().getSimpleName() + " class.");
        }
    }

    public static void registerNBT(INBT handler)
    {
        if (handler.nbtName() != null && handler.nbtName().length() != 0)
        {
            Main.nbtHandlers.add(handler);
            Main.stringer("Registered new NBT handler " + handler.getClass().getSimpleName());
        }
        else
        {
            throw new BAPIException("Invalid NBT name parameter for handler " + handler.getClass().getSimpleName());
        }
    }

    public static void registerCreativeHandler(ICreativeHandler handler)
    {
        Main.creativeHandlers.add(handler);
        Main.stringer("Registered new creative handler " + handler.getClass().getSimpleName());
    }

    public static void registerPlacableHandler(IPlacable handler)
    {
        Main.placableHandlers.add(handler);
        Main.stringer("Registered new placable handler " + handler.getClass().getSimpleName());
    }

    public static String getCustomStringColor()
    {
        return Main.getSColor();
    }
}
