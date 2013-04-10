package me.aronth.minetechplus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDumb extends Slot{
	
	private ItemStack displayStack;
	
	public SlotDumb(int x, int y, ItemStack stack) {
		super(null, 0, x, y);
		displayStack = stack;
		if(displayStack != null)displayStack.stackSize = 1;
		setSlotImage();
	}
	
	public void setSlotImage(){
		//this.setBackgroundIconIndex(displayStack.getItem().getIconFromDamage(displayStack.getItemDamage()));
	}
	
	public boolean canTakeStack(EntityPlayer par1EntityPlayer){
        return false;
    }
	
	public ItemStack getStack(){
        return displayStack;
    }
	
	public void putStack(ItemStack par1ItemStack){
		
    }
	
	public void onSlotChanged(){
        
    }
	
	public int getSlotStackLimit() {
		return 0;
	}

	public ItemStack decrStackSize(int par1) {
		return null;
	}

	public boolean isSlotInInventory(IInventory par1IInventory, int par2) {
		return false;
	}

}
