package me.aronth.minetechplus.core;

import me.aronth.minetechplus.blocks.BlockIdeaBuilder;
import me.aronth.minetechplus.blocks.BlockOres;
import me.aronth.minetechplus.blocks.BlockWorkstation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockHandler {

	private ConfigHandler config;
	
	public static Block blockOre, blockWorkstation, blockIdeaBuilder;
	
	public BlockHandler(ConfigHandler configHandler){
		config = configHandler;
		initBlocks();
	}
	
	public void initBlocks(){
		blockWorkstation = new BlockWorkstation(config.IDWorkstation, Material.wood).setHardness(2.5F);
		blockIdeaBuilder = new BlockIdeaBuilder(config.IDIdeaBuilder, Material.wood).setHardness(2.5F);
		blockOre = new BlockOres(config.IDOreBlock).setHardness(3.0F).setResistance(5.0F);
		
		registerBlocks();
	}
	
	public void registerBlocks(){
		GameRegistry.registerBlock(blockWorkstation, "Workstation");
		GameRegistry.registerBlock(blockIdeaBuilder, "IdeaBuilder");
		GameRegistry.registerBlock(blockOre, me.aronth.minetechplus.items.ItemOreBlocks.class, "OreBlocks");
	}
	
}
