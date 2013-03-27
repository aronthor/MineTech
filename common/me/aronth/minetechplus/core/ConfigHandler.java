package me.aronth.minetechplus.core;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigHandler {
	
	// Blocks
	public int IDOreBlock;
	// Items
	public int IDIdeaItem;
	// Mish
	public int ideaChance;
	
	public ConfigHandler(File configFile){
		// Loads The Configuration File into Forges Configuration
		Configuration conf = new Configuration(configFile);
		try{
			conf.load();
			
			// Misc mod settings
			Property IdeaChanceRate = conf.get("Settings", "IdeaRate", 100);
			IdeaChanceRate.comment = "The chance of you getting an idea from crafting (fx. 1 in a 100)";
			ideaChance = IdeaChanceRate.getInt();
			
			// Load Block Ids
			
			Property OreBlock = conf.getBlock("OreBlocks", 800);
			OreBlock.comment = "Ore Block ID, this includes Tin, Copper and Others";
			IDOreBlock = OreBlock.getInt();
			
			// Load Item Ids
			
			Property ideaItem = conf.getItem("Idea", 4000);
			ideaItem.comment = "The idea paper that falls on the ground";
			IDIdeaItem = ideaItem.getInt();
			
		}catch(RuntimeException e){
			MineTechPlus.instance.log.log(Level.INFO, "Config file not found, creating new one");
		}finally{
			conf.save();
		}
	}
	
}
