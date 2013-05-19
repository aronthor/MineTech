package me.aronth.minetechplus.core.proxy;

import me.aronth.minetechplus.blocks.tileentitys.TileSuperContainer;
import me.aronth.minetechplus.client.renderer.BlockRenderer;
import me.aronth.minetechplus.client.renderer.SuperContainerRenderer;
import me.aronth.minetechplus.client.renderer.item.SuperContainerItemRenderer;
import me.aronth.minetechplus.core.BlockHandler;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyCommon {
	
	public void registerHandlers(){
		super.registerHandlers();
		
		RenderingRegistry.registerBlockHandler(new BlockRenderer());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileSuperContainer.class, new SuperContainerRenderer());
		
		MinecraftForgeClient.registerItemRenderer(BlockHandler.blockIdeaBlocks.blockID, new SuperContainerItemRenderer());
	}

}
