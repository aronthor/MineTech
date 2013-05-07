package me.aronth.minetechplus.inventory;

import me.aronth.minetechplus.crafting.ICraftMaster;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventorySmall implements IInventory{

    private ItemStack[] inv;
    private ICraftMaster master;
    
    public InventorySmall(ICraftMaster icraftmaster, int size){
        master= icraftmaster;
        inv = new ItemStack[size];
    }
    
    @Override
    public int getSizeInventory() {
        return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inv[i];
    }

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
        return inv[i];
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        inv[i] = itemstack;
        master.onChange();
    }

    @Override
    public String getInvName() {
        return "container.minetech.small";
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
        master.onChange();
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
