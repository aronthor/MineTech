package me.aronth.minetechplus.inventory;

import me.aronth.minetechplus.blocks.tileentitys.TileIdeaBuilder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerIdeaBuilder extends Container{
    
    public ContainerIdeaBuilder(IInventory playerInv, TileIdeaBuilder builder){
        
        int x = 48;
        int y = 17;
        
        int slotId = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                addSlotToContainer(new SlotGhost(builder.matrix, slotId, x+(18*i), y+(18*j)));
                slotId++;
            }
        }
        
        addSlotToContainer(new SlotIdea(builder.matrix, 9, 18, 35));
        
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 9; j++) 
                    addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for (int i = 0; i < 9; i++) 
            addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
    }
    
    public ItemStack transferStackInSlot(EntityPlayer me, int slotId){
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slotId);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
                ItemStack stackInSlot = slotObject.getStack();
                stack = stackInSlot.copy();

                //merges the item into player inventory since its in the tileEntity
                if (slotId < 10) {
                        if (!this.mergeItemStack(stackInSlot, 10, 45, true)) {
                                return null;
                        }
                }
                //places it into the tileEntity is possible since its in the player inventory
                /*else if (stackInSlot != null && stackInSlot.getItem() instanceof ItemIdea && slotId == 9){
                    this.putStackInSlot(9, stackInSlot);
                    return null;
                }*/

                if (stackInSlot.stackSize == 0) {
                        slotObject.putStack(null);
                } else {
                        slotObject.onSlotChanged();
                }

                if (stackInSlot.stackSize == stack.stackSize) {
                        return null;
                }
                slotObject.onPickupFromSlot(me, stackInSlot);
        }
        return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }

    /*public void updateGhosts() {
        for(int i = 0; i < inventorySlots.size(); i++){
            if(inventorySlots.get(i) instanceof SlotGhost){
                ((SlotGhost)inventorySlots.get(i)).setGhost(Item.diamond.getIconFromDamage(0));
            }
        }
    }*/

}
