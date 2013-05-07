package me.aronth.minetechplus.core;

import java.io.File;
import java.util.logging.Level;

import me.aronth.minetechplus.MineTechPlus;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigHandler {
    
    private int blockRange = 800;
    private int itemRange = 7000;
	
	// Blocks
	public int IDOreBlock, IDWorkstation, IDIdeaBuilder;
	// Items
	public int IDIdeaItem, IDWonderingBook, IDPencil, IDResource;
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
			
			Property blockOre = conf.getBlock("OreBlocks", blockRange);
			blockOre.comment = "Ore Block ID, this includes Tin, Copper and Others";
			IDOreBlock = blockOre.getInt();
			
			Property blockWorkstation = conf.getBlock("Workstation", blockRange+1);
			blockWorkstation.comment = "The workstation where you refine your ideas and craft items";
			IDWorkstation = blockWorkstation.getInt();
			
			Property blockIdeaBuilder = conf.getBlock("IdeaBuilder", blockRange+2);
			blockIdeaBuilder.comment = "This is the extension too the workstation that lets you craft what you think off.";
            IDIdeaBuilder = blockIdeaBuilder.getInt();
			
			// Load Item Ids
			
			Property itemIdea = conf.getItem("Idea", itemRange);
			itemIdea.comment = "The idea paper that falls on the ground";
			IDIdeaItem = itemIdea.getInt();
			
			Property itemBookOfWondering = conf.getItem("BookOfWondering", itemRange+1);
			itemBookOfWondering.comment = "The Book of Wondering, for you too store your ideas";
			IDWonderingBook = itemBookOfWondering.getInt();
			
			Property itemPencil = conf.getItem("Pencil", itemRange+2);
			itemPencil.comment = "Pencil is an idea you have for editing signs";
            IDPencil = itemPencil.getInt();
            
            Property itemResource = conf.getItem("ItemResources", itemRange+3);
            itemResource.comment = "Item resources like copper and tin ingots";
            IDResource = itemResource.getInt();
			
		}catch(RuntimeException e){
			MineTechPlus.instance.log.log(Level.INFO, "Config file not found, creating new one");
		}finally{
			conf.save();
		}
	}
	
}
