package me.aronth.minetechplus.core;

import cpw.mods.fml.common.registry.LanguageRegistry;
import me.aronth.minetechplus.items.ItemIdea;
import net.minecraft.item.Item;

public class ItemHandler {

	// For shorter referencing to the config handler
	private ConfigHandler config;
	
	// Empty fields for holding items
	public static Item idea;
	
	public ItemHandler(ConfigHandler handler){
		config = handler;
		
		// Initalizes all mod items
		initItems();
	}
	
	public void initItems(){
		idea = new ItemIdea(config.IDIdeaItem);
		
		addLanguage();
	}
	
	public void addLanguage(){
		LanguageRegistry.addName(idea, "Idea");
	}
}
