package me.aronth.minetechplus.core;

import me.aronth.minetechplus.blocks.BlockIdeaBuilder;
import me.aronth.minetechplus.blocks.BlockOres;
import me.aronth.minetechplus.blocks.BlockWorkstation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockHandler {

	private ConfigHandler config;
	
	public Block blockOre, blockWorkstation, blockIdeaBuilder;
	
	public BlockHandler(ConfigHandler configHandler){
		config = configHandler;
		initBlocks();
	}
	
	public void initBlocks(){
		blockWorkstation = new BlockWorkstation(config.IDWorkstation, Material.wood);
		blockIdeaBuilder = new BlockIdeaBuilder(config.IDIdeaBuilder, Material.wood);
		blockOre = new BlockOres(config.IDOreBlock);
		
		addLanguage();
		registerBlocks();
	}
	
	public void addLanguage(){
		LanguageRegistry.addName(blockWorkstation, "Workstation");
		//LanguageRegistry.addName(blockWorkstation, "Workstation");
	}
	
	public void registerBlocks(){
		GameRegistry.registerBlock(blockWorkstation, "Workstation");
		GameRegistry.registerBlock(blockIdeaBuilder, "IdeaBuilder");
		GameRegistry.registerBlock(blockOre, me.aronth.minetechplus.items.ItemOreBlocks.class, "OreBlocks");
	}
	
}
