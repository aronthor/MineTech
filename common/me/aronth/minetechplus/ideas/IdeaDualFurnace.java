package me.aronth.minetechplus.ideas;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;


public class IdeaDualFurnace extends Idea{
    
    public IdeaDualFurnace(int id){
        super(id);
        addMaterial(new ItemStack(Block.cobblestone));
    }

}
