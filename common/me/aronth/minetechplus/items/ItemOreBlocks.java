package me.aronth.minetechplus.items;

import me.aronth.minetechplus.blocks.BlockOres;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemOreBlocks extends ItemBlock{

    public ItemOreBlocks(int par1) {
        super(par1);
        setHasSubtypes(true);
    }
    
    public String getUnlocalizedName(ItemStack stack){
          int meta = stack.getItemDamage();
          return getUnlocalizedName() + ".ore" + BlockOres.names[meta];
    }
   
    public int getMetadata(int par1){
          return par1;
    }

}
