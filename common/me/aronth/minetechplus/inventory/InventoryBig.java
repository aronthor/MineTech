package me.aronth.minetechplus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryBig implements IInventory {
    // Inventory Size size
    public int size;

    // Contents
    private ItemStack[] inventory;

    // Few Constants for constant NBT tag names
    private String slot = "slot";
    private String stackName = "name";
    private String stackSize = "size";
    private String stackDamage = "damage";
    private String stackCompound = "compound";
    private String stackEmpty = "empty";

    public InventoryBig(int size) {
        this.size = size;
        inventory = new ItemStack[size];
    }

    public ItemStack[] getInventory(){
        return inventory;
    }
    
    public void loadInventory(NBTTagCompound data) {
        for (int i = 0; i < inventory.length; i++) {
            inventory[i] = getStackFromNBT(data.getCompoundTag(slot + "" + i));
        }
    }

    public NBTTagCompound saveInventory() {
        NBTTagCompound data = new NBTTagCompound();

        for (int i = 0; i < inventory.length; i++) {
            data.setCompoundTag(slot + "" + i, getStackAsNBT(inventory[i]));
        }
        return data;
    }

    // Loads a stack
    public NBTTagCompound getStackAsNBT(ItemStack stack) {
        NBTTagCompound comp = new NBTTagCompound();

        if (stack == null) {
            comp.setBoolean(stackEmpty, true);
            return comp;
        }

        comp.setInteger(stackName, stack.itemID);
        comp.setInteger(stackSize, stack.stackSize);
        comp.setInteger(stackDamage, stack.getItemDamage());
        if (stack.stackTagCompound != null)
            comp.setCompoundTag(stackCompound, stack.stackTagCompound);
        return comp;
    }

    // Saves a stack
    public ItemStack getStackFromNBT(NBTTagCompound comp) {
        if (comp.hasKey(stackEmpty)) {
            return null;
        }

        int id = comp.getInteger(stackName);
        int size = comp.getInteger(stackSize);
        int damage = comp.getInteger(stackDamage);

        ItemStack stack = new ItemStack(id, size, damage);

        if (comp.hasKey(stackCompound))
            stack.stackTagCompound = comp.getCompoundTag(stackCompound);

        return stack;
    }

    // -- Methods implemented from IInventory

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (inventory[i] != null) {
            if (inventory[i].stackSize <= j) {
                ItemStack itemstack = inventory[i];
                inventory[i] = null;
                onInventoryChanged();
                return itemstack;
            }
            ItemStack itemstack1 = inventory[i].splitStack(j);
            if (inventory[i].stackSize == 0)
                inventory[i] = null;
            onInventoryChanged();
            return itemstack1;
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return getStackInSlot(slot);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        inventory[slot] = itemstack;
        onInventoryChanged();
    }

    @Override
    public String getInvName() {
        return "container.minetech.inventoryBig";
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
    public void onInventoryChanged() {}

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
