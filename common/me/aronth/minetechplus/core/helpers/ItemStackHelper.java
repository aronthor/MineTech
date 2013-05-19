package me.aronth.minetechplus.core.helpers;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemStackHelper {
    
    /**
     * Give it an ItemStack and specify how much you want subtracted from it
     * @param stack the stack you want
     * @param amt how much you want subtracted from the stack
     * @return ItemStack that is amt smaller
     */
    public ItemStack getSmallerStack(ItemStack stack, int amt){
        if(amt > stack.stackSize)
            return null;
        return new ItemStack(stack.itemID, stack.getItemDamage(), stack.stackSize-amt);
    }
    
    public static NBTTagCompound getStackAsNBT(ItemStack stack){
        NBTTagCompound comp = new NBTTagCompound();
        comp.setInteger("id", stack.itemID);
        comp.setInteger("size", stack.stackSize);
        comp.setInteger("damage", stack.getItemDamage());
        if(stack.hasTagCompound())
            comp.setCompoundTag("compound", stack.stackTagCompound);
        return comp;
    }
    
    public static ItemStack getStackFromNBT(NBTTagCompound comp){
        ItemStack stack = new ItemStack(comp.getInteger("id"), comp.getInteger("damage"), comp.getInteger("size"));
        if(comp.hasKey("compound"))
            stack.stackTagCompound = comp.getCompoundTag("compound");
        return stack;
    }
    
    public static void dropStack(ItemStack stack, World w, int x, int y, int z){
        if(stack == null)
            return;
        Random rand = new Random();
        double posX = x + ((rand.nextDouble()*2)-1);
        double posY = y + ((rand.nextDouble()*1));
        double posZ = z + ((rand.nextDouble()*2)-1);
        EntityItem item = new EntityItem(w, posX, posY, posZ, stack);
        w.spawnEntityInWorld(item);
    }
    
    public static void dropStacks(ItemStack[] stacks, World w, int x, int y, int z){
        for(ItemStack stack : stacks){
            dropStack(stack, w, x, y, z);
        }
    }
    
}
