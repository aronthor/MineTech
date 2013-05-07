package me.aronth.minetechplus.inventory;

import me.aronth.minetechplus.blocks.tileentitys.TileIdeaBuilder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotIdeaCrafting extends Slot {

    private TileIdeaBuilder builder;
    
    public SlotIdeaCrafting(IInventory inv, TileIdeaBuilder builder, int id, int x, int y) {
        super(inv, id, x, y);
        this.builder = builder;
    }
    
    public void onPickupFromSlot(EntityPlayer player, ItemStack pickupStack){
        //matrix.subtractOneFromGrid();
        builder.onCrafted(player, pickupStack);
    }
    
    public boolean isItemValid(ItemStack par1ItemStack){
        return false;
    }

}
