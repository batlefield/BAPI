package net.minecraft.src;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.BAPI.Main;
import net.minecraft.src.BAPI.VersionCheck;
import net.minecraft.src.forge.NetworkMod;

public class mod_BAPI extends NetworkMod
{
    @MLProp(name = "Version check")
    private boolean versionCheck = false;
    public static boolean disableNbt = true;
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

    public boolean onTickInGame(MinecraftServer minecraft)
    {
        String s = VersionCheck.run("http://www2.arnes.si/~pmati/BAUC.html");

        if (versionCheck && !s.equals(Main.APIVer))
        {
            minecraft.logWarning("New BAPI version available(" + s + ")");
        }

        return false;
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
