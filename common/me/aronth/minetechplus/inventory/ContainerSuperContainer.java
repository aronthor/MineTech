package me.aronth.minetechplus.inventory;

import me.aronth.minetechplus.blocks.tileentitys.TileSuperContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerSuperContainer extends Container{
    
    protected TileSuperContainer container;
    private int numRows = 6;
    
    public ContainerSuperContainer(InventoryPlayer playerInventory, TileEntity tile){
        container = (TileSuperContainer) tile;
        container.openChest();
        
        int slotId = 0;
        for(int row = 0; row < 6; row++){
            for(int slot = 0; slot < 9; slot++){
                addSlotToContainer(new Slot(container.inv, slotId, 8+slot*18, 18+row*18));
                ++slotId;
            }
        }
        
        for (int j = 0; j < 3; ++j)
            for (int k = 0; k < 9; ++k)
                this.addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 140 + j * 18));

        for (int j = 0; j < 9; ++j)
            this.addSlotToContainer(new Slot(playerInventory, j, 8 + j * 18, 198));
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }

    public void onCraftGuiClosed(EntityPlayer player){
        super.onCraftGuiClosed(player);
        container.closeChest();
    }
    
}
