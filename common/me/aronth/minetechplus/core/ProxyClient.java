package me.aronth.minetechplus.core;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyCommon {
	
	public void registerHandlers(){
		super.registerHandlers();
		RenderingRegistry.registerBlockHandler(new BlockRenderHandler());
	}

}
