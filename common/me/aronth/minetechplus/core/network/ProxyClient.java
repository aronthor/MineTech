package me.aronth.minetechplus.core.network;

import me.aronth.minetechplus.core.BlockRenderHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyCommon {
	
	public void registerHandlers(){
		super.registerHandlers();
		RenderingRegistry.registerBlockHandler(new BlockRenderHandler());
	}

}
