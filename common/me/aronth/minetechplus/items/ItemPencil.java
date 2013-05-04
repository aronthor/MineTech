package me.aronth.minetechplus.items;

import me.aronth.minetechplus.MineTechPlus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.world.World;

public class ItemPencil extends MTItem{

    public ItemPencil(int id) {
        super(id);
        this.setMaxStackSize(1);
        //this.setMaxDamage(7);
        this.setUnlocalizedName("pencil");
        this.setCreativeTab(MineTechPlus.tab);
    }
    
    public boolean onItemUse(ItemStack stack, EntityPlayer me, World world, int x, int y, int z, int par7, float par8, float par9, float par10){
        
        TileEntity clicked = world.getBlockTileEntity(x, y, z);
        if(clicked != null && clicked instanceof TileEntitySign){
            ((TileEntitySign)clicked).setEditable(true);
            me.displayGUIEditSign(clicked);
        }
        
        //stack.setItemDamage(stack.getItemDamage()-1);
        
        return false;
    }

}
