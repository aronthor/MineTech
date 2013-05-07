package me.aronth.minetechplus.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGhost extends Slot{

    //private Icon ghostIcon;
    
    public SlotGhost(IInventory inv, int id, int x, int y) {
        super(inv, id, x, y);
    }
    
    public void onSlotChange(ItemStack par1ItemStack, ItemStack par2ItemStack){
        super.onSlotChange(par1ItemStack, par2ItemStack);
        
    }
    
    /*public void setGhost(Icon icns){
        ghostIcon = icns;
    }
    
    public void clearChost(){
        ghostIcon = null;
    }*/
    
    /*public Icon getBackgroundIconIndex(){
        return ((IdeaBuilderMatrix)this.inventory).getGhostIcon(getSlotIndex());
    }*/
    
}
