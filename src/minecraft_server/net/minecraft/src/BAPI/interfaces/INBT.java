package net.minecraft.src.BAPI.interfaces;

import net.minecraft.src.NBTTagCompound;

public interface INBT
{
    public String nbtName();

    public boolean savePerPlayer();

    public void writeToNBT(NBTTagCompound nbttagcompound);

    public void readFromNBT(NBTTagCompound nbttagcompound);
}
