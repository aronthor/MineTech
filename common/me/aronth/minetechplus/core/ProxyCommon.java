package me.aronth.minetechplus.core;

import me.aronth.minetechplus.ideas.IdeaPopper;
import me.aronth.minetechplus.inventory.ContainerIdea;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class ProxyCommon implements IGuiHandler{
	
	public void registerHandlers(){
		GameRegistry.registerCraftingHandler(new IdeaPopper());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_IDEA)
			return new ContainerIdea(player, world, x, y, z);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
	
}
