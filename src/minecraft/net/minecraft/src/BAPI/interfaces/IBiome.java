package net.minecraft.src.BAPI.interfaces;

import net.minecraft.src.BiomeGenBase;

public interface IBiome
{
    /**
     * Return the biome
     * @return
     */
    public BiomeGenBase getBiome();

    /**
     * Return true if player can spawn in this biome
     * @return
     */

    public boolean canSpawnIn();

    /**
     * Return true if village can generate in this biome
     * @return
     */

    public boolean canGenerateVillage();

    /**
     * Return true if stronghold can generate in this biome
     * @return
     */

    public boolean canGenerateStronghold();
}