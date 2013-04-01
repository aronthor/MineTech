package me.aronth.minetechplus.ideas;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

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
	public boolean resourcesUsed(Object obj) {
		if(obj instanceof Block){
			if(((Block) obj).blockID == Block.cobblestone.blockID)
				return true;
		}
		
		if(obj instanceof Item){
			if(((Item) obj).itemID == Item.ingotIron.itemID)
				return true;
		}
		return false;
	}

}
