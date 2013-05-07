package me.aronth.minetechplus.core.helpers;

import net.minecraft.item.ItemStack;

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
    
}
