package me.aronth.minetechplus.inventory;

import me.aronth.minetechplus.items.ItemIdea;
import me.aronth.minetechplus.lib.Constants;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotIdea extends Slot {

    public SlotIdea(IInventory inv, int id, int x, int y) {
        super(inv, id, x, y);
    }
    
    public boolean isItemValid(ItemStack stack){
        if(stack.getItem() instanceof ItemIdea && stack.hasTagCompound() && stack.stackTagCompound.hasKey(Constants.NBT_IDEA))
            return true;
        
        return false;
    }
}
