package me.aronth.minetechplus.core;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import me.aronth.minetechplus.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockHandler {

	private ConfigHandler config;
	
	public Block blockOre, blockWorkstation;
	
	public BlockHandler(ConfigHandler configHandler){
		config = configHandler;
		initBlocks();
	}
	
	public void initBlocks(){
		blockWorkstation = new BlockWorkstation(config.IDWorkstation, Material.wood);
		
		addLanguage();
		registerBlocks();
	}
	
	public void addLanguage(){
		LanguageRegistry.addName(blockWorkstation, "Workstation");
	}
	
	public void registerBlocks(){
		GameRegistry.registerBlock(blockWorkstation, "Workstation");
	}
	
}
