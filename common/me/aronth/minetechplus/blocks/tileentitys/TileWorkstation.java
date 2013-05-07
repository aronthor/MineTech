package me.aronth.minetechplus.blocks.tileentitys;

import me.aronth.minetechplus.core.helpers.IdeaHelper;
import me.aronth.minetechplus.items.ItemIdea;
import me.aronth.minetechplus.lib.Constants;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileWorkstation extends TileEntity implements IInventory{

	private ItemStack[] stack = new ItemStack[2];
	private int bookcases = 0;
	/*private int[] side = new int[6];
	private boolean open = false;
	
	public void openForModule(){
	    open = true;
	}
	
	public boolean addModule(int s){
	    if(side[s] != null)
	        return false;
	    
	    return true;
	}*/
	
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
	
	public int findBookcases(){
	    bookcases = 0;
	    for(int y = 0; y < 2;y++){
    	    for(int x = 0; x < 5; x++){
    	        for(int z = 0; z < 5; z++){
    	            if(worldObj.getBlockId(xCoord+x-2, yCoord+y, zCoord+z-2) == Block.bookShelf.blockID)
    	                this.bookcases++;
    	        }
    	    }
	    }
	    //if(Reference.DEBUG)System.out.println("Found " + this.bookcases + " around");
	    return bookcases;
	}
	
	public boolean hasLevels(EntityPlayer me){
	    int levels = IdeaHelper.getRequiredLevels(findBookcases(), me);
	    if(me.experienceLevel >= levels)
	        return true;
	    return false;
	}

    public void refineIdea(EntityPlayer player) {
        if(hasLevels(player)){
            if(this.stack[0] != null){
                if(stack[0].getItem() instanceof ItemIdea){
                    NBTTagCompound tags = stack[0].stackTagCompound;
                    if(tags.hasKey(Constants.NBT_REFINED)){
                        if(tags.getInteger(Constants.NBT_REFINED) < 3){
                            tags.setInteger(Constants.NBT_REFINED, tags.getInteger(Constants.NBT_REFINED)+1);
                            //stack[1] = stack[0];
                            //stack[0] = null;
                            //player.experienceLevel -= IdeaHelper.getRequiredLevels(findBookcases(), player);
                        }
                    }else{
                        tags.setInteger(Constants.NBT_REFINED, 1);
                    }
                }
            }
        }
    }
	
}
