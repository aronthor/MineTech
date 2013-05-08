package me.aronth.minetechplus.inventory;

import me.aronth.minetechplus.blocks.tileentitys.TileCraftingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCraftingSilent extends Slot{

    private TileCraftingTable ctable;
    
    public SlotCraftingSilent(TileCraftingTable table, IInventory par3IInventory, int par4, int par5, int par6){
        super(par3IInventory, par4, par5, par6);
        ctable = table;
    }

    public boolean isItemValid(ItemStack par1ItemStack){
        return false;
    }

    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack){
        ctable.onCrafted();
    }
        
    
}
