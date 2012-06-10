package net.minecraft.src.BAPI.interfaces;

import java.util.ArrayList;

public interface ICreativeHandler
{
    /**
     * Provides you with array list to which you add your custom itemstacks
     * @param itemList
     */

    public void addCreativeItems(ArrayList itemList);
    
    /**
     * Provides you with array list to which you add your custom itemstacks
     * @param itemList
     */

    public void addCreativeBlocks(ArrayList itemList);
}
