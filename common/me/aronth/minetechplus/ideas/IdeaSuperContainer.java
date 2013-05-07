package me.aronth.minetechplus.ideas;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class IdeaSuperContainer extends Idea{

    public IdeaSuperContainer(int id) {
        super(id);
        setRecipe(1);
        addMaterial(new ItemStack(Block.planks));
        addMaterial(new ItemStack(Block.chest));
        addMaterial(new ItemStack(Block.chestTrapped));
        addMaterial(new ItemStack(Item.bucketEmpty));
        addMaterial(new ItemStack(Item.bucketLava));
        addMaterial(new ItemStack(Item.bucketMilk));
        addMaterial(new ItemStack(Item.bucketWater));
    }
    
    public String getDescription() {
        return "This container is bigger and has some unique features too it.";
    }

}
