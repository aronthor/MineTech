package me.aronth.minetechplus.inventory;

import me.aronth.minetechplus.blocks.tileentitys.TileWorkstation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerWorkstation extends Container{
	
	public ContainerWorkstation(TileWorkstation tile, IInventory playerInv){
		
		addSlotToContainer(new Slot(tile, 0, 44, 35));
		addSlotToContainer(new Slot(tile, 1, 116, 35));
		
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                    addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
		}

		for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
		return null;
    }
}
