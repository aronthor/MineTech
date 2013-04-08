package me.aronth.minetechplus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryIdea implements IInventory {
	
	public ItemStack[] inv;
	public EntityPlayer me;
	
	public InventoryIdea(ItemStack[] stacks, EntityPlayer player){
		inv = stacks;
		me = player;
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inv[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(inv[i] == null)return null;
		if(inv[i].stackSize <= j)return null;
		if(inv[i].stackSize > j){
			ItemStack stack = ItemStack.copyItemStack(inv[i]);
			stack.stackSize = stack.stackSize - j;
			return stack;
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return inv[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		inv[i] = itemstack;
	}

	@Override
	public String getInvName() {
		return "Idea";
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
	public void onInventoryChanged() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

}
