package me.aronth.minetechplus.core;

import net.minecraft.block.Block;

public class BlockHandler {

	private ConfigHandler config;
	
	public Block blockOre;
	
	public BlockHandler(ConfigHandler configHandler){
		config = configHandler;
		initBlocks();
	}
	
	public void initBlocks(){
		
		
		addLanguage();
		registerBlocks();
	}
	
	public void addLanguage(){
	}
	
	public void registerBlocks(){
	}
	
}
