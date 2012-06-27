package net.minecraft.src.BAPI.minecraft;

import java.io.File;

import net.minecraft.src.Chunk;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraft.src.mod_BAPI;
import net.minecraft.src.BAPI.Main;
import net.minecraft.src.forge.ISaveEventHandler;

public class SaveHandlerDefault implements ISaveEventHandler {

	public void onWorldLoad(World world) {
		File file = new File(mod_BAPI.getSave(world), "BAPI");
    	file.mkdirs();
		Main.loadNBT(file);
	}

	public void onWorldSave(World world) {
		File file = new File(mod_BAPI.getSave(world), "BAPI");
    	file.mkdirs();
		Main.saveNBT(file);
	}

	public void onChunkLoad(World world, Chunk chunk) {
		
	}

	public void onChunkUnload(World world, Chunk chunk) {
		
	}

	public void onChunkSaveData(World world, Chunk chunk, NBTTagCompound data) {
		
	}

	public void onChunkLoadData(World world, Chunk chunk, NBTTagCompound data) {
		
	}

}
