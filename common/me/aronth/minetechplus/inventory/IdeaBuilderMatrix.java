package me.aronth.minetechplus.inventory;

import me.aronth.minetechplus.ideas.Idea;
import me.aronth.minetechplus.items.ItemIdea;
import me.aronth.minetechplus.lib.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;

public class IdeaBuilderMatrix implements IInventory {
    
    private ItemStack[] matrix = new ItemStack[10];
    //private Icon[] ghostIcons = new Icon[9];
    
    public NBTTagCompound saveMatrix(){
        NBTTagCompound comp = new NBTTagCompound();
        
        for(int i = 0; i < matrix.length; i++){
            comp.setCompoundTag("slot"+i, getStackAsNBT(matrix[i]));
        }
        
        return comp;
    }
    
    public void loadMatrix(NBTTagCompound comp){
        for(int i = 0; i < matrix.length; i++){
            matrix[i] = getStockFromNBT(comp.getCompoundTag("slot"+i));
        }
    }
    
    public NBTTagCompound getStackAsNBT(ItemStack stack){
        NBTTagCompound comp = new NBTTagCompound();
        
        if(stack == null){
            comp.setBoolean("empty", true);
            return comp;
        }
        
        comp.setInteger("id", stack.itemID);
        comp.setInteger("size", stack.stackSize);
        comp.setInteger("meta", stack.getItemDamage());
        if(stack.stackTagCompound != null)
            comp.setCompoundTag("compound", stack.stackTagCompound);
        return comp;
    }
    
    public ItemStack getStockFromNBT(NBTTagCompound comp){
        if(comp.hasKey("empty")){
            return null;
        }
        
        int id = comp.getInteger("id");
        int size = comp.getInteger("size");
        int damage = comp.getInteger("meta");
        
        ItemStack stack = new ItemStack(id, size, damage);
        
        if(comp.hasKey("compound"))
            stack.stackTagCompound = comp.getCompoundTag("compound");
            
        return stack;
    }

    /*public void setGhost(int slot, Icon icon){
        this.ghostIcons[slot] = icon;
    }*/
    
    public Icon getGhostIcon(int slot){
        //return ghostIcons[slot];
        ItemStack stack = matrix[slot];
        if(stack == null)
            return null;
        if(!stack.stackTagCompound.hasKey(Constants.NBT_IDEA))
            return null;
        Idea idea = Idea.getIdeaById(stack.stackTagCompound.getInteger(Constants.NBT_IDEA));
        if(idea == null)
            return null;
        
        return idea.getGostIcon(slot);
    }
    
    public boolean isIdeaBuildDefined(){
        if(matrix[9] != null && matrix[9].getItem() instanceof ItemIdea && matrix[9].stackTagCompound != null && matrix[9].stackTagCompound.hasKey(Constants.NBT_IDEA))
            return true;
        return false;
    }
    
    @Override
    public int getSizeInventory() {
        return matrix.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return matrix[i];
    }

    public ItemStack decrStackSize(int i, int j) {
        if (matrix[i] != null){
            if (matrix[i].stackSize <= j){
                ItemStack itemstack = matrix[i];
                matrix[i] = null;
                onInventoryChanged();
                return itemstack;
            }
            ItemStack itemstack1 = matrix[i].splitStack(j);
            if (matrix[i].stackSize == 0)
                matrix[i] = null;
            onInventoryChanged();
            return itemstack1;
        }else{
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return matrix[i];
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        matrix[i] = itemstack;
    }

    @Override
    public String getInvName() {
        return "IdeaBuilder";
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void onInventoryChanged() {
        // For testing the rendering thing thing thing
        //this.setGhost(2, Item.axeGold.getIconFromDamage(0));
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
        return true;
    }
}
