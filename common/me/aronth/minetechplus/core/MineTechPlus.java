package me.aronth.minetechplus.core;

import java.util.logging.Logger;

import me.aronth.minetechplus.ideas.IdeaManager;
import me.aronth.minetechplus.ideas.IdeaPopper;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version=Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {"minetechplus"})
public class MineTechPlus {
	
	// A logger too log the mod.. dont know why i need it right now
	public Logger log = FMLLog.getLogger();//Logger.getLogger("MineTech+");
	
	// Mod instance as requested by forge
	@Instance("minetech")
	public static MineTechPlus instance;
	
	@SidedProxy(clientSide = "me.aronth.minetechplus.core.ProxyClient", serverSide = "me.aronth.minetechplus.core.ProxyCommon")
	public static ProxyCommon proxy;
	
	// Configuration Handler that handles the config file
	public ConfigHandler config;
	
	// Item, Block and Crafting Handlers, just for simple management and cleaner code
	public BlockHandler blocks;
	public ItemHandler items;
	public CraftingHandler crafting;
	
	// The Idea Manager.. for managing all'em ideas
	public IdeaManager ideaManager = new IdeaManager();
	
	@PreInit
	public void preInit(FMLPreInitializationEvent e){
		config = new ConfigHandler(e.getSuggestedConfigurationFile());
	}
	
	@Init
	public void initMod(FMLInitializationEvent e){
		items = new ItemHandler(config);
		blocks = new BlockHandler(config);
		crafting = new CraftingHandler();
		
		//proxy.registerHandlers();
		
		GameRegistry.registerCraftingHandler(new IdeaPopper());
	}
	
}
