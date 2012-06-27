package net.minecraft.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.src.BAPI.Main;
import net.minecraft.src.BAPI.VersionCheck;
import net.minecraft.src.BAPI.interfaces.ICreativeHandler;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;

import org.lwjgl.opengl.GL11;

public class mod_BAPI extends NetworkMod
{
    private GuiScreen lastGuiOpen;

    @MLProp(name = "Version check")
    private boolean versionCheck = false;
    private boolean hadWorld = false;

    public String getVersion()
    {
        return "BAPI V" + Main.APIVer + " for minecraft " + Main.MCVer;
    }
    
    public static File getSave(World world)
    {
    	return ((SaveHandler)world.saveHandler).getSaveDirectory();
    }
    
    public void load()
    {
    	MinecraftForge.versionDetectStrict("BAPI", 3, 2, 5);
    	if(Minecraft.getVersion() != Main.MCVer)
    	{
    		throw new RuntimeException("BAPI: Minecraft version not matching, required version:" + Main.MCVer);
    	}
        ModLoader.setInGameHook(this, true, false);
        ModLoader.setInGUIHook(this, true, false);
    }

    public boolean onTickInGame(float var1, Minecraft minecraft)
    {
        String s = VersionCheck.run("http://www2.arnes.si/~pmati/BAUC.html");
        if (versionCheck && !s.equals(Main.APIVer))
        {
            minecraft.thePlayer.addChatMessage("New BAPI version available(V" + s + ")");
        }

        return false;
    }
    
    public boolean onTickInGUI(float f, Minecraft minecraft, GuiScreen guiscreen)
    {
    	if (minecraft.currentScreen == null)
        {
            lastGuiOpen = null;
        }
        if ((guiscreen instanceof GuiContainerCreative) && !(lastGuiOpen instanceof GuiContainerCreative))
        {
            Container container = ((GuiContainer) guiscreen).inventorySlots;
            ((ContainerCreative)container).itemList.clear();
            List itemList = ((ContainerCreative) container).itemList;

            Block[] var2 = new Block[] {Block.cobblestone, Block.stone, Block.oreDiamond, Block.oreGold, Block.oreIron, Block.oreCoal, Block.oreLapis, Block.oreRedstone, Block.stoneBrick, Block.stoneBrick, Block.stoneBrick, Block.stoneBrick, Block.blockClay, Block.blockDiamond, Block.blockGold, Block.blockSteel, Block.bedrock, Block.blockLapis, Block.brick, Block.cobblestoneMossy, Block.stairSingle, Block.stairSingle, Block.stairSingle, Block.stairSingle, Block.stairSingle, Block.stairSingle, Block.obsidian, Block.netherrack, Block.slowSand, Block.glowStone, Block.wood, Block.wood, Block.wood, Block.wood, Block.leaves, Block.leaves, Block.leaves, Block.leaves, Block.dirt, Block.grass, Block.sand, Block.sandStone, Block.sandStone, Block.sandStone, Block.gravel, Block.web, Block.planks, Block.planks, Block.planks, Block.planks, Block.sapling, Block.sapling, Block.sapling, Block.sapling, Block.deadBush, Block.sponge, Block.ice, Block.blockSnow, Block.plantYellow, Block.plantRed, Block.mushroomBrown, Block.mushroomRed, Block.cactus, Block.melon, Block.pumpkin, Block.pumpkinLantern, Block.vine, Block.fenceIron, Block.thinGlass, Block.netherBrick, Block.netherFence, Block.stairsNetherBrick, Block.whiteStone, Block.mycelium, Block.waterlily, Block.tallGrass, Block.tallGrass, Block.chest, Block.workbench, Block.glass, Block.tnt, Block.bookShelf, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.dispenser, Block.stoneOvenIdle, Block.music, Block.jukebox, Block.pistonStickyBase, Block.pistonBase, Block.fence, Block.fenceGate, Block.ladder, Block.rail, Block.railPowered, Block.railDetector, Block.torchWood, Block.stairCompactPlanks, Block.stairCompactCobblestone, Block.stairsBrick, Block.stairsStoneBrickSmooth, Block.lever, Block.pressurePlateStone, Block.pressurePlatePlanks, Block.torchRedstoneActive, Block.button, Block.trapdoor, Block.enchantmentTable, Block.redstoneLampIdle};
            int var3 = 0;
            int var4 = 0;
            int var5 = 0;
            int var6 = 0;
            int var7 = 0;
            int var8 = 0;
            int var9 = 0;
            int var10 = 0;
            int var11 = 1;
            int var12;
            int var13;

            for (var12 = 0; var12 < var2.length; ++var12)
            {
                var13 = 0;

                if (var2[var12] == Block.cloth)
                {
                    var13 = var3++;
                }
                else if (var2[var12] == Block.stairSingle)
                {
                    var13 = var4++;
                }
                else if (var2[var12] == Block.wood)
                {
                    var13 = var5++;
                }
                else if (var2[var12] == Block.planks)
                {
                    var13 = var6++;
                }
                else if (var2[var12] == Block.sapling)
                {
                    var13 = var7++;
                }
                else if (var2[var12] == Block.stoneBrick)
                {
                    var13 = var8++;
                }
                else if (var2[var12] == Block.sandStone)
                {
                    var13 = var9++;
                }
                else if (var2[var12] == Block.tallGrass)
                {
                    var13 = var11++;
                }
                else if (var2[var12] == Block.leaves)
                {
                    var13 = var10++;
                }

                itemList.add(new ItemStack(var2[var12], 1, var13));
            }
            
            for (ICreativeHandler handler : Main.getCreativeHandlers())
            {
         		handler.addCreativeBlocks((ArrayList) itemList);
            }
            
            for (Block block : Block.blocksList)
            {
                if (block != null)
                {
                    block.addCreativeItems((ArrayList)itemList);
                }
            }
            
            int x = 0;
            for (Item item : Item.itemsList)
            {
                if (x++ >= 256 && item != null)
                {
                    item.addCreativeItems((ArrayList)itemList);
                }
            }
            
            for (ICreativeHandler handler : Main.getCreativeHandlers())
            {
         		handler.addCreativeItems((ArrayList) itemList);
            }

            Iterator var15 = EntityList.entityEggs.keySet().iterator();

            while (var15.hasNext())
            {
                Integer var17 = (Integer)var15.next();
                itemList.add(new ItemStack(Item.monsterPlacer.shiftedIndex, 1, var17.intValue()));
            }
            
            
            ((ContainerCreative) container).itemList = itemList;
            
        }

        lastGuiOpen = guiscreen;
        return true;
    }

    public boolean clientSideRequired()
    {
        return true;
    }

    public boolean serverSideRequired()
    {
        return false;
    }
}
