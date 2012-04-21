package net.minecraft.src;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.src.BAPI.BAPIException;
import net.minecraft.src.BAPI.Main;
import net.minecraft.src.BAPI.VersionCheck;
import net.minecraft.src.BAPI.interfaces.ICreativeHandler;
import net.minecraft.src.forge.NetworkMod;

public class mod_BAPI extends NetworkMod
{
    private GuiScreen lastGuiOpen;

    @MLProp(name = "Version check")
    private boolean versionCheck = false;
    public static boolean disableNbt = true;
    private boolean ticked = false;
    private boolean guiTick;

    public String getVersion()
    {
        return "BAPI V" + Main.APIVer + " for minecraft " + Main.MCVer;
    }

    public void load()
    {
        ModLoader.setInGameHook(this, true, true);
        ModLoader.setInGUIHook(this, true, true);
    }

    public boolean onTickInGame(float var1, Minecraft minecraft)
    {
        if (minecraft.currentScreen == null)
        {
            lastGuiOpen = null;
            guiTick = false;
        }

        String s = VersionCheck.run("http://www2.arnes.si/~pmati/BAUC.html");

        if (!ticked && versionCheck && !s.equals(Main.APIVer))
        {
            minecraft.thePlayer.addChatMessage("New BAPI version available(V" + s + ")");
            ticked = true;
        }

        return true;
    }

    public boolean onTickInGUI(float f, Minecraft minecraft, GuiScreen guiscreen)
    {
        if (!guiTick && (guiscreen instanceof GuiContainerCreative) && !(lastGuiOpen instanceof GuiContainerCreative))
        {
            Container container = ((GuiContainer) guiscreen).inventorySlots;
            List list = ((ContainerCreative) container).itemList;

            for (Object o : Main.getCreativeHandlers())
            {
                if (o instanceof ICreativeHandler)
                {
                    ICreativeHandler handler = ((ICreativeHandler)o);
                    handler.addCreativeItems((ArrayList) list);
                }
            }

            guiTick = true;
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
