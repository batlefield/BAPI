package net.minecraft.src.BAPI;

public class ItemKey
{
    public final int itemID;
    public final int itemDamage;

    public ItemKey(int item, int damage)
    {
        itemID = item;
        itemDamage = damage;
    }

    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + itemDamage;
        result = prime * result + itemID;
        return result;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (!(obj instanceof ItemKey))
        {
            return false;
        }

        ItemKey other = (ItemKey) obj;

        if (itemDamage != other.itemDamage)
        {
            return false;
        }

        if (itemID != other.itemID)
        {
            return false;
        }

        return true;
    }
}
