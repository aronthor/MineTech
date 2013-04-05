package me.aronth.minetechplus.blocks.tileentitys;

import me.aronth.minetechplus.items.ItemIdea;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileWorkstation extends TileEntity implements IInventory{

	private ItemStack[] stack = new ItemStack[1];
	
	@Override
	public int getSizeInventory() {
		return stack.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return stack[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {

        if (stack[i] != null)
        {
            if (stack[i].stackSize <= j)
            {
                ItemStack itemstack = stack[i];
                stack[i] = null;
                onInventoryChanged();
                return itemstack;
            }
            ItemStack itemstack1 = stack[i].splitStack(j);
            if (stack[i].stackSize == 0)
            {
            	stack[i] = null;
            }
            onInventoryChanged();
            return itemstack1;
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return stack[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		stack[i] = itemstack;
	}

	@Override
	public String getInvName() {
		return "Workstation";
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		if(i == 0 && itemstack.getItem() instanceof ItemIdea)
			return true;
		return false;
	}
	
	
	
}
