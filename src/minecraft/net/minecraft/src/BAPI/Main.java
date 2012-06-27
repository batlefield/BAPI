package net.minecraft.src.BAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.BAPI.interfaces.IBiome;
import net.minecraft.src.BAPI.interfaces.ICreativeHandler;
import net.minecraft.src.BAPI.interfaces.IGameOverlay;
import net.minecraft.src.BAPI.interfaces.INBT;
import net.minecraft.src.BAPI.interfaces.IPlacable;
import net.minecraft.src.BAPI.minecraft.DefaultPlacingHandler;
import net.minecraft.src.BAPI.minecraft.SaveHandlerDefault;
import net.minecraft.src.forge.MinecraftForge;

public class Main
{
    private static boolean init = false;
    public static final String APIVer = "1.5.3";
    public static final String MCVer = "1.2.5";

    protected static LinkedList<ICreativeHandler> creativeHandlers = new LinkedList<ICreativeHandler>();
    protected static LinkedList<IBiome> biomeHandlers = new LinkedList<IBiome>();
    protected static LinkedList<IPlacable>placableHandlers = new LinkedList<IPlacable>();
    protected static LinkedList<INBT>nbtHandlers = new LinkedList<INBT>();
    protected static LinkedList<IGameOverlay>gameOverlays = new LinkedList<IGameOverlay>();
    protected static String[][] authors = new String[128][2];
    public static int lastIndex = 0;
    private static List biomesVillage = new ArrayList();
    private static List biomesStronghold = new ArrayList();
    private static List biomesSpawn = new ArrayList();
    private static boolean biomeInit = false;
    protected static int liquidTex;

    private static void initialize()
    {
        if (!init)
        {
            stringer("Initialized BAPI V" + APIVer);
            init = true;
        }
    }

    public static LinkedList<ICreativeHandler> getCreativeHandlers()
    {
        if (!init)
        {
            initialize();
        }

        return creativeHandlers;
    }

    public static boolean canPlace(int flowerID, int plantableID, int plantableMeta)
    {
        if (!init)
        {
            initialize();
        }

        for (IPlacable handler : placableHandlers)
        {
            if (handler.canPlantFlower(flowerID, plantableID, plantableMeta))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean canPlaceR(int plantableID, int plantableMeta)
    {
        if (!init)
        {
            initialize();
        }

        for (IPlacable handler : placableHandlers)
        {
            if (handler.canReedGrowOn(plantableID, plantableMeta))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean canPlaceC(int plantableID, int plantableMeta)
    {
        if (!init)
        {
            initialize();
        }

        for (IPlacable handler : placableHandlers)
        {
            if (handler.canCactusGrowOn(plantableID, plantableMeta))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean canPlaceT(int placeableID, int placeableMeta)
    {
        if (!init)
        {
            initialize();
        }

        for (IPlacable handler : placableHandlers)
        {
            if (handler.canPlaceTorchOn(placeableID, placeableMeta))
            {
                return true;
            }
        }

        return false;
    }

    protected static void stringer(String s)
    {
        ModLoader.getLogger().fine("BAPI:" + s);
        System.out.println("BAPI:" + s);
    }

    private static void initBiomes()
    {
        for (IBiome handler : biomeHandlers)
        {
            if (handler.canSpawnIn())
            {
                biomesSpawn.add(handler.getBiome());
            }

            if (handler.canGenerateVillage())
            {
                biomesVillage.add(handler.getBiome());
            }

            if (handler.canGenerateStronghold())
            {
                biomesStronghold.add(handler.getBiome());
            }
        }

        biomeInit = true;
    }

    public static List getBiome(int i)
    {
        if (!init)
        {
            initialize();
        }

        if (!biomeInit)
        {
            initBiomes();
        }

        if (i <= 0 || i >= 4)
        {
            throw new BAPIException("BAPI: Invalid integer");
        }

        switch (i)
        {
            case 1:
                return biomesVillage;
            case 2:
                return biomesStronghold;
            case 3:
                return biomesSpawn;
            default:
                return null;	
        }
    }

    public static String getSColor()
    {
        if (!init)
        {
            initialize();
        }

        Random random = new Random();
        int color = random.nextInt(15);
        String colorS;

        switch (color)
        {
            case 0:
                colorS = "§4";
                break;

            case 1:
                colorS = "§c";
                break;

            case 2:
                colorS = "§6";
                break;

            case 3:
                colorS = "§e";
                break;

            case 4:
                colorS = "§2";
                break;

            case 5:
                colorS = "§a";
                break;

            case 6:
                colorS = "§b";
                break;

            case 7:
                colorS = "§3";
                break;

            case 8:
                colorS = "§1";
                break;

            case 9:
                colorS = "§9";
                break;

            case 10:
                colorS = "§d";
                break;

            case 11:
                colorS = "§5";
                break;

            case 12:
                colorS = "§f";
                break;

            case 13:
                colorS = "§7";
                break;

            case 14:
                colorS = "§8";
                break;

            case 15:
                colorS = "§0";
                break;

            default:
                colorS = "§f";
                break;
        }

        return colorS;
    }

    public static void saveNBT(File file)
    {
        if (!init)
        {
            initialize();
        }

        try
        {
            File file1;

            for (INBT handler : nbtHandlers)
            {
                NBTTagCompound nbt1 = new NBTTagCompound();
                file1 = new File(file, handler.nbtName() + ".dat");

                if (!file1.exists())
                {
                    CompressedStreamTools.writeCompressed(nbt1, new FileOutputStream(file1));
                }

                NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file1));
                handler.writeToNBT(nbttagcompound);
                CompressedStreamTools.writeCompressed(nbttagcompound, new FileOutputStream(file1));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void loadNBT(File file)
    {
        if (!init)
        {
            initialize();
        }

        for(INBT handler : nbtHandlers)
        {
        	handler.beforeWorldLoad();
        }
        
        try
        {
            for (INBT handler : nbtHandlers)
            {
                NBTTagCompound nbt1 = new NBTTagCompound();
                File file1 = new File(file, handler.nbtName() + ".dat");

                if (!file1.exists())
                {
                    CompressedStreamTools.writeCompressed(nbt1, new FileOutputStream(file1));
                }

                NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file1));
                handler.readFromNBT(nbttagcompound);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
        
    public static void tickOverlay(Minecraft mc, int height, int width, float partialTicks, boolean isDisplayingGUI)
    {
    	for (IGameOverlay handler : gameOverlays)
		{
			handler.render(mc, height, width, partialTicks, isDisplayingGUI);
		}
    }
    
    public static void handleCredits(List lines)
    {
    	lines.add("[C]------------- Mod credits ---------------");
    	
    	for(int i = 0; i < lastIndex - 1; i++)
    	{
    		lines.add("");
    		lines.add("§7" + authors[i][0] + ":");
    		lines.add("    §f" + Main.authors[i][1]);
    	}
    	
    	for(int i = 0; i < 8; i++)
    	{
    		lines.add("");
    	}
    }
        
    
    
    static
    {
    	MinecraftForge.registerSaveHandler(new SaveHandlerDefault());
    	
    	BAPI.registerModAuthor("BAPI", "Battlefield");
    	
        placableHandlers.add(new DefaultPlacingHandler());
        biomesSpawn.add(BiomeGenBase.forest);
        biomesSpawn.add(BiomeGenBase.plains);
        biomesSpawn.add(BiomeGenBase.taiga);
        biomesSpawn.add(BiomeGenBase.taigaHills);
        biomesSpawn.add(BiomeGenBase.forestHills);
        biomesSpawn.add(BiomeGenBase.jungle);
        biomesSpawn.add(BiomeGenBase.jungleHills);
        biomesStronghold.add(BiomeGenBase.desert);
        biomesStronghold.add(BiomeGenBase.forest);
        biomesStronghold.add(BiomeGenBase.extremeHills);
        biomesStronghold.add(BiomeGenBase.swampland);
        biomesStronghold.add(BiomeGenBase.taiga);
        biomesStronghold.add(BiomeGenBase.icePlains);
        biomesStronghold.add(BiomeGenBase.iceMountains);
        biomesStronghold.add(BiomeGenBase.desertHills);
        biomesStronghold.add(BiomeGenBase.forestHills);
        biomesStronghold.add(BiomeGenBase.extremeHillsEdge);
        biomesStronghold.add(BiomeGenBase.jungle);
        biomesStronghold.add(BiomeGenBase.jungleHills);
        biomesVillage.add(BiomeGenBase.desert);
        biomesVillage.add(BiomeGenBase.plains);
    }
}