package me.aronth.minetechplus.core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingHandler {

	public CraftingHandler() {
		initCrafting();
	}
	
	private void initCrafting(){
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.bookOfWondering), new Object[]{
			ItemHandler.idea, Item.book
		});
	}

}
