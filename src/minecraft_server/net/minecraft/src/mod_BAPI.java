package net.minecraft.src;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.BAPI.Main;
import net.minecraft.src.BAPI.VersionCheck;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;

public class mod_BAPI extends NetworkMod
{
    @MLProp(name = "Version check")
    private boolean versionCheck = false;

    public String getVersion()
    {
        return "BAPI V" + Main.APIVer + " for minecraft " + Main.MCVer;
    }
    
    public void load()
    {
    	MinecraftForge.versionDetectStrict("BAPI", 3, 2, 5);
    	if(ModLoader.getMinecraftServerInstance().getVersion() != Main.MCVer)
    	{
    		throw new RuntimeException("BAPI: Minecraft version not matching, required version:" + Main.MCVer);
    	}
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
