package me.aronth.minetechplus;

import java.util.logging.Logger;

import me.aronth.minetechplus.core.BlockHandler;
import me.aronth.minetechplus.core.ConfigHandler;
import me.aronth.minetechplus.core.CraftingHandler;
import me.aronth.minetechplus.core.CreativeTabMineTech;
import me.aronth.minetechplus.core.ItemHandler;
import me.aronth.minetechplus.core.Reference;
import me.aronth.minetechplus.core.helpers.PlayerTracker;
import me.aronth.minetechplus.core.network.CommandMineTech;
import me.aronth.minetechplus.core.network.PacketHandler;
import me.aronth.minetechplus.core.network.ProxyCommon;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version=Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {Reference.MOD_ID}, packetHandler = PacketHandler.class)
public class MineTechPlus {
	
	// A logger too log the mod.. dont know why i need it right now
	public Logger log = FMLLog.getLogger();//Logger.getLogger("MineTech+");
	
	// Mod instance as requested by forge
	@Instance(Reference.MOD_ID)
	public static MineTechPlus instance;
	
	@SidedProxy(clientSide = "me.aronth.minetechplus.core.network.ProxyClient", serverSide = "me.aronth.minetechplus.core.network.ProxyCommon")
	public static ProxyCommon proxy;
	
	// Configuration Handler that handles the config file
	public ConfigHandler config;
	
	// Creative Tab for MineTech+
	public static CreativeTabs tab = new CreativeTabMineTech(CreativeTabs.getNextID(), Reference.MOD_ID);
	
	// The player tracker
	public PlayerTracker playerTracker = new PlayerTracker();
	
	// Item, Block and Crafting Handlers, just for simple management and cleaner code
	public BlockHandler blocks;
	public ItemHandler items;
	public CraftingHandler crafting;
	
	
	@PreInit
	public void preInit(FMLPreInitializationEvent e){
		config = new ConfigHandler(e.getSuggestedConfigurationFile());
		//TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);
	}
	
	@Init
	public void initMod(FMLInitializationEvent e){
		items = new ItemHandler(config);
		blocks = new BlockHandler(config);
		crafting = new CraftingHandler();
		GameRegistry.registerTileEntity(me.aronth.minetechplus.blocks.tileentitys.TileWorkstation.class, "Workstation");
		GameRegistry.registerTileEntity(me.aronth.minetechplus.blocks.tileentitys.TileIdeaBuilder.class, "IdeaBuilder");
		proxy.addNames();
		proxy.registerHandlers();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent e){
	    GameRegistry.registerPlayerTracker(playerTracker);
	}
	
	@ServerStarting
	public void serverStart(FMLServerStartingEvent e){
	    e.registerServerCommand(new CommandMineTech());
	}
}
