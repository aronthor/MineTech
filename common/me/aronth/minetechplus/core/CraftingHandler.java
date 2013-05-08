package me.aronth.minetechplus.core;

import me.aronth.minetechplus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
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
	    OreDictionary.registerOre("oreCopper", new ItemStack(BlockHandler.blockOre, 1, 0));
	    OreDictionary.registerOre("oreTin", new ItemStack(BlockHandler.blockOre, 1, 1));
	    
	    OreDictionary.registerOre("ingotCopper", new ItemStack(ItemHandler.resources, 1, 0));
	    OreDictionary.registerOre("ingotTin", new ItemStack(ItemHandler.resources, 1, 1));
	}
	
	private void initSmelting(){
	    //GameRegistry.addSmelting(input, output, xp)
	    addMetaSmelting(BlockHandler.blockOre.blockID, 0, new ItemStack(ItemHandler.resources, 1, 0), 3f);
	    addMetaSmelting(BlockHandler.blockOre.blockID, 1, new ItemStack(ItemHandler.resources, 1, 1), 3f);
	}
	
	private void addMetaSmelting(int id, int meta, ItemStack resault, float xp){
	    FurnaceRecipes.smelting().addSmelting(id, meta, resault, xp);
	}
	
	private void initDebugSmelting(){
	    
	}
	
	private void initCrafting(){
		GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.blockWorkstation), new Object[]{
		   "ppp", "f f", 'p', Block.planks, 'f', Block.fence 
		});
		GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.blockCraftingTable), new Object[]{
	       "pcp", "f f", 'p', Block.planks, 'f', Block.fence , 'c', Block.workbench
	    });
		GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.blockWorkstation), new Object[]{
	       " s ", "pcp", "f f", 'p', Block.planks, 'f', Block.fence, 's', Item.paper
	    });
	}
	
	private void initDebugRecipes(){
	    GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.bookOfWondering), new Object[]{ItemHandler.idea, Item.book});
	}

}
