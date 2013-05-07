package me.aronth.minetechplus.ideas;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;


public class IdeaDualFurnace extends Idea{
    
    public IdeaDualFurnace(int id){
        super(id);
        setRecipe(0);
        addMaterial(new ItemStack(Block.cobblestone));
        addMaterial(new ItemStack(Block.furnaceIdle));
    }
    
    /*public ItemStack[] getGostIcon() {
        ItemStack[] icons = { 
                new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron), 
                new ItemStack(Block.furnaceIdle), new ItemStack(Item.redstone), new ItemStack(Block.furnaceIdle),
                new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron)
        };
        
        return icons;
    }*/

}
