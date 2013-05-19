package me.aronth.minetechplus.ideas;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class IdeaMinersHelmet extends Idea{

    public IdeaMinersHelmet(int id) {
        super(id);
        addMaterial(new ItemStack(Item.lightStoneDust));
        addMaterial(new ItemStack(Block.torchWood));
        this.setRecipe(3);
    }

}
