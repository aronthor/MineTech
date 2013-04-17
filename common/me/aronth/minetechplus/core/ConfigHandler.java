package me.aronth.minetechplus.core;

import java.io.File;
import java.util.logging.Level;

import me.aronth.minetechplus.MineTechPlus;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigHandler {
	
	// Blocks
	public int IDOreBlock, IDWorkstation;
	// Items
	public int IDIdeaItem, IDWonderingBook, IDPencil;
	// Misc
	public int ideaChance;
	public static int renderId;
	
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
			
			Property blockOre = conf.getBlock("OreBlocks", 800);
			blockOre.comment = "Ore Block ID, this includes Tin, Copper and Others";
			IDOreBlock = blockOre.getInt();
			
			Property blockWorkstation = conf.getBlock("Workstation", 801);
			blockWorkstation.comment = "The workstation where you refine your ideas and craft items";
			IDWorkstation = blockWorkstation.getInt();
			
			// Load Item Ids
			
			Property itemIdea = conf.getItem("Idea", 4000);
			itemIdea.comment = "The idea paper that falls on the ground";
			IDIdeaItem = itemIdea.getInt();
			
			Property itemBookOfWondering = conf.getItem("BookOfWondering", 4001);
			itemBookOfWondering.comment = "The Book of Wondering, for you too store your ideas";
			IDWonderingBook = itemBookOfWondering.getInt();
			
			Property itemPencil = conf.getItem("Pencil", 4002);
			itemPencil.comment = "Pencil is an idea you have for editing signs";
            IDPencil = itemPencil.getInt();
			
		}catch(RuntimeException e){
			MineTechPlus.instance.log.log(Level.INFO, "Config file not found, creating new one");
		}finally{
			conf.save();
		}
	}
	
}
