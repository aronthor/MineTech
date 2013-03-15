package me.aronth.minetechplus.core;

import java.util.logging.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "minetech", name = "MineTech+", version="0.1")
public class MineTechPlus {
	
	public Logger log = Logger.getLogger("MineTech+");
	
	@Instance("minetech")
	public static MineTechPlus instance;
	
	public ConfigHandler config;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent e){
		config = new ConfigHandler(e.getSuggestedConfigurationFile());
	}
	
	@Init
	public void initMod(FMLInitializationEvent e){
		
	}
	
}
