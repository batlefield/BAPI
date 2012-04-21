package net.minecraft.src;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.BAPI.BAPIException;
import net.minecraft.src.BAPI.Main;
import net.minecraft.src.BAPI.VersionCheck;
import net.minecraft.src.BAPI.interfaces.ICreativeHandler;
import net.minecraft.src.forge.NetworkMod;

public class mod_BAPI extends NetworkMod
{
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
    }

    public boolean onTickInGame(MinecraftServer minecraftServer)
    {
        String s = VersionCheck.run("http://www2.arnes.si/~pmati/BAUC.html");

        if (!ticked && versionCheck && !s.equals(Main.APIVer))
        {
            minecraftServer.logWarning("New BAPI version available(V" + s + ")");
            ticked = true;
        }

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
