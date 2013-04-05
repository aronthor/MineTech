package me.aronth.minetechplus.ideas;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class IdeaDualFurnace implements IIdea {

	@Override
	public String getName() {
		return "Dual Furnace";
	}

	@Override
	public String getDescription() {
		return "This is the DualFurnace 3000 here.";
	}
	
	@Override
	public boolean resourcesUsed(ItemStack stack) {
		if(stack.isItemEqual(new ItemStack(Block.cobblestone)))
			return true;
		return false;
	}

}
