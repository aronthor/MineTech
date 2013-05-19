package me.aronth.minetechplus.core;

import me.aronth.minetechplus.items.ItemBookWondering;
import me.aronth.minetechplus.items.ItemIdea;
import me.aronth.minetechplus.items.ItemMinersHelmet;
import me.aronth.minetechplus.items.ItemPencil;
import me.aronth.minetechplus.items.ItemResources;
import net.minecraft.item.Item;

public class ItemHandler {

	// For shorter referencing to the config handler
	private ConfigHandler config;
	
	// Empty fields for holding items
	public static Item idea, bookOfWondering, pencil, resources, minersHelmet;
	
	public ItemHandler(ConfigHandler handler){
		config = handler;
		
		// Initalizes all mod items
		initItems();
	}
	
	public void initItems(){
		idea = new ItemIdea(config.IDIdeaItem);
		bookOfWondering = new ItemBookWondering(config.IDWonderingBook);
		pencil = new ItemPencil(config.IDPencil);
		resources = new ItemResources(config.IDResource);
		minersHelmet = new ItemMinersHelmet(config.IDMinersHelmet);
	}
}
