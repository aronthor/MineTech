package me.aronth.minetechplus.ideas;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class IdeaMinersHelmet extends Idea{

    public IdeaMinersHelmet(int id) {
        super(id);
        addMaterial(new ItemStack(Block.torchWood));
    }

}
