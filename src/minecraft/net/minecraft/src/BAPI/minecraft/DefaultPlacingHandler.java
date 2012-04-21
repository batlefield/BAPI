package net.minecraft.src.BAPI.minecraft;

import net.minecraft.src.Block;
import net.minecraft.src.BAPI.interfaces.IPlacable;

public class DefaultPlacingHandler implements IPlacable
{
    public boolean canPlantFlower(int flowerID, int plantableID, int plantableMeta)
    {
        return false;
    }

    public boolean canCactusGrowOn(int plantableID, int plantableMeta)
    {
        return false;
    }

    public boolean canReedGrowOn(int plantableID, int plantableMeta)
    {
        return plantableID == Block.dirt.blockID || plantableID == Block.grass.blockID || plantableID == Block.sand.blockID;
    }

    public boolean canPlaceTorchOn(int placeableID, int placeableMeta)
    {
        return placeableID == Block.fence.blockID || placeableID == Block.netherFence.blockID || placeableID == Block.glass.blockID;
    }
}
