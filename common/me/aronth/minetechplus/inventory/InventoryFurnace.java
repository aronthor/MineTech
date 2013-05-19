package me.aronth.minetechplus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryFurnace implements IInventory{

    public ItemStack[] inv;
    public int furnaceLanes;
    public int fuelLanes;
    
    private String slot = "slot";
    private String stackName = "name";
    private String stackSize = "size";
    private String stackDamage = "damage";
    private String stackCompound = "compound";
    private String stackEmpty = "empty";
    
    public InventoryFurnace(int furnaces, int fuel){
        inv = new ItemStack[furnaces*2+fuel];
    }
    
 // loads the matrix
    public void loadMatrix(NBTTagCompound data){
        //Help.logSide("load");
        
        for(int i = 0; i < inv.length; i++){
            //if(data.hasKey(slot+i)){
                inv[i] = getStackFromNBT(data.getCompoundTag(slot+""+i));
            //}
                //Help.log(slot+""+i+" loaded");
        }
        
    }
    
    // Saves the matrix
    public NBTTagCompound saveMatrix(){
        //Help.logSide("save");
        NBTTagCompound data = new NBTTagCompound();
        
        for(int i = 0; i < inv.length; i++){
            //if(inventory[i] != null){
                data.setCompoundTag(slot+""+i, getStackAsNBT(inv[i]));
            //}
                //Help.log(slot+""+i+" saved");
        }
        
        return data;
    }
    
    // Loads a stack
    public NBTTagCompound getStackAsNBT(ItemStack stack){
        NBTTagCompound comp = new NBTTagCompound();
        
        if(stack == null){
            comp.setBoolean(stackEmpty, true);
            return comp;
        }
        
        comp.setInteger(stackName, stack.itemID);
        comp.setInteger(stackSize, stack.stackSize);
        comp.setInteger(stackDamage, stack.getItemDamage());
        if(stack.stackTagCompound != null)
            comp.setCompoundTag(stackCompound, stack.stackTagCompound);
        return comp;
    }
    
    // Saves a stack
    public ItemStack getStackFromNBT(NBTTagCompound comp){
        if(comp.hasKey(stackEmpty)){
            return null;
        }
        
        int id = comp.getInteger(stackName);
        int size = comp.getInteger(stackSize);
        int damage = comp.getInteger(stackDamage);
        
        ItemStack stack = new ItemStack(id, size, damage);
        
        if(comp.hasKey(stackCompound))
            stack.stackTagCompound = comp.getCompoundTag(stackCompound);
            
        return stack;
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
        if (inv[i] != null){
            if (inv[i].stackSize <= j){
                ItemStack itemstack = inv[i];
                inv[i] = null;
                onInventoryChanged();
                return itemstack;
            }
            ItemStack itemstack1 = inv[i].splitStack(j);
            if (inv[i].stackSize == 0)
                inv[i] = null;
            onInventoryChanged();
            return itemstack1;
        }else{
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return getStackInSlot(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        inv[i] = itemstack;
        onInventoryChanged();
    }

    @Override
    public String getInvName() {
        return "furnaceInv";
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
        return true;
    }

}
