package me.aronth.minetechplus.inventory;

import me.aronth.minetechplus.blocks.tileentitys.TileDualFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class ContainerDualFurnace extends Container{

    public ContainerDualFurnace(InventoryPlayer inv, World w, int x, int y, int z){
        
        TileDualFurnace furnace = (TileDualFurnace) w.getBlockTileEntity(x, y, z);
        
        // Inputs
        this.addSlotToContainer(new Slot(furnace.furnaceInv, 0, 26, 15));
        this.addSlotToContainer(new Slot(furnace.furnaceInv, 1, 55, 15));
        
        //Fuel
        this.addSlotToContainer(new Slot(furnace.furnaceInv, 2, 41, 49));
        
        // Output
        this.addSlotToContainer(new SlotOutput(furnace.furnaceInv, 3, 117, 33));
        this.addSlotToContainer(new SlotOutput(furnace.furnaceInv, 4, 135, 33));
        
        for (int l = 0; l < 3; ++l)
            for (int i1 = 0; i1 < 9; ++i1)
                this.addSlotToContainer(new Slot(inv, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));

        for (int l = 0; l < 9; ++l)
            this.addSlotToContainer(new Slot(inv, l, 8 + l * 18, 142));
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 5, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false) && !this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 2, 3, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 5 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 5, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 5, 39, false))
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

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}
