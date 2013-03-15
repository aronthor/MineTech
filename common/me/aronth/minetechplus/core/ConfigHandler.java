package me.aronth.minetechplus.core;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	public int blockStatic;
	
	public ConfigHandler(File configFile){
		// Loads The Configuration File into Forges Configuration
		Configuration conf = new Configuration(configFile);
		try{
			conf.load();
			// Load Block Ids
			
			
			
			
			
		}catch(RuntimeException e){
			MineTechPlus.instance.log.log(Level.INFO, "Config file not found, creating new one");
		}finally{
			conf.save();
		}
	}
	
}
