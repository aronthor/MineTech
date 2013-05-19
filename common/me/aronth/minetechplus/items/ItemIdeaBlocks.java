package me.aronth.minetechplus.items;

import me.aronth.minetechplus.blocks.BlockIdeaBlocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemIdeaBlocks extends ItemBlock{
    
    public ItemIdeaBlocks(int par1) {
        super(par1);
        setHasSubtypes(true);
    }
    
    public String getUnlocalizedName(ItemStack stack){
          int meta = stack.getItemDamage();
          return getUnlocalizedName() + "." + BlockIdeaBlocks.names[meta];
    }
   
    public int getMetadata(int par1){
          return par1;
    }
    
}
