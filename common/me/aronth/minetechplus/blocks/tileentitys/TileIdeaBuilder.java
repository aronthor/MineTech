package me.aronth.minetechplus.blocks.tileentitys;

import me.aronth.minetechplus.inventory.IdeaBuilderMatrix;
import me.aronth.minetechplus.items.ItemIdea;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileIdeaBuilder extends TileEntity{

    private ItemStack[] stacks = new ItemStack[1];
    public IdeaBuilderMatrix matrix = new IdeaBuilderMatrix();
    
    public TileIdeaBuilder(){
        
    }
    
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
        if(nbt.hasKey("matrix"))
            matrix.loadMatrix(nbt.getCompoundTag("matrix"));
    }

    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        nbt.setCompoundTag("matrix", matrix.saveMatrix());
    }
    
    public int getSizeInventory() {
        return stacks.length;
    }

    public ItemStack getStackInSlot(int i) {
        return stacks[i];
    }

    public ItemStack decrStackSize(int i, int j) {
        // TODO Auto-generated method stub
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int i) {
        return stacks[i];
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        stacks[i] = itemstack;
    }

    public String getInvName() {
        return "IdeaBuilder";
    }

    public boolean isInvNameLocalized() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return true;
    }

    public void openChest() {}

    public void closeChest() {}

    public boolean isStackValidForSlot(int i, ItemStack itemstack) {
        if(i == 0)
            if(!(itemstack.getItem() instanceof ItemIdea))
                return false;
        return true;
    }
    
    /*
     * TODO
     * Make the GUI for the idea builder.. image is ready
     * make the crafting manager
     * make the slots
     * make ideas hold recipes for items
     * make container
     */

}
