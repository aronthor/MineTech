package me.aronth.minetechplus.core;

import me.aronth.minetechplus.MineTechPlus;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingHandler {

	public CraftingHandler() {
	    addToOreDictionary();
		initCrafting();
		initSmelting();
		if(Reference.DEBUG){
		    initDebugRecipes();
		    initDebugSmelting();
		}
	}
	
	private void addToOreDictionary(){
	    //OreDictionary.registerOre(id, ore)
	}
	
	private void initSmelting(){
	    //GameRegistry.addSmelting(input, output, xp)
	}
	
	private void initDebugSmelting(){
	    
	}
	
	private void initCrafting(){
		GameRegistry.addShapedRecipe(new ItemStack(MineTechPlus.instance.blocks.blockWorkstation), new Object[]{
		   "ppp", "f f", 'p', Block.planks, 'f', Block.fence 
		});
	}
	
	private void initDebugRecipes(){
	    GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.bookOfWondering), new Object[]{ItemHandler.idea, Item.book});
	}

}
