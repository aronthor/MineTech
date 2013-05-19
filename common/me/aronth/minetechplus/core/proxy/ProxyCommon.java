package me.aronth.minetechplus.core.proxy;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.blocks.tileentitys.TileIdeaBuilder;
import me.aronth.minetechplus.blocks.tileentitys.TileWorkstation;
import me.aronth.minetechplus.client.gui.GuiBookOfWondering;
import me.aronth.minetechplus.client.gui.GuiCrafting;
import me.aronth.minetechplus.client.gui.GuiDualFurnace;
import me.aronth.minetechplus.client.gui.GuiIdea;
import me.aronth.minetechplus.client.gui.GuiIdeaBuilder;
import me.aronth.minetechplus.client.gui.GuiRefineIdea;
import me.aronth.minetechplus.client.gui.GuiSuperContainer;
import me.aronth.minetechplus.core.helpers.IdeaHandler;
import me.aronth.minetechplus.inventory.ContainerCrafting;
import me.aronth.minetechplus.inventory.ContainerDualFurnace;
import me.aronth.minetechplus.inventory.ContainerIdea;
import me.aronth.minetechplus.inventory.ContainerIdeaBuilder;
import me.aronth.minetechplus.inventory.ContainerSuperContainer;
import me.aronth.minetechplus.inventory.ContainerWorkstation;
import me.aronth.minetechplus.lib.GuiIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ProxyCommon implements IGuiHandler{
	
	public void registerHandlers(){
		GameRegistry.registerCraftingHandler(new IdeaHandler());
		NetworkRegistry.instance().registerGuiHandler(MineTechPlus.instance, this);
		
	}

	// Load all language files
	public void addNames(){
	    String langLocation = "/mods/minetechplus/lang/";
	    String[] files = { "en_US" };
	    
	    for(String file : files){
	        try{
	            LanguageRegistry.instance().loadLocalization(langLocation + file + ".xml", file, true);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
	    
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	    if(ID == GuiIds.GUI_WORKSTATION)
	        return new ContainerWorkstation((TileWorkstation) world.getBlockTileEntity(x, y, z), player.inventory);
		if(ID == GuiIds.GUI_IDEA)
			return new ContainerIdea(player);
		if(ID == GuiIds.GUI_IDEABUILDER)
		    return new ContainerIdeaBuilder(player.inventory, (TileIdeaBuilder)world.getBlockTileEntity(x, y, z));
		if(ID == GuiIds.GUI_CRAFTINGTABLE)
            return new ContainerCrafting(player.inventory, world, x, y, z);
		if(ID == GuiIds.GUI_DUALFURNACE)
		    return new ContainerDualFurnace(player.inventory, world, x, y, z);
		if(ID == GuiIds.GUI_SUPER_CONTAINER)
		    return new ContainerSuperContainer(player.inventory, world.getBlockTileEntity(x, y, z));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	    if(ID == GuiIds.GUI_WORKSTATION)
	        return new GuiRefineIdea(player.inventory, (TileWorkstation) world.getBlockTileEntity(x, y, z));
		if(ID == GuiIds.GUI_IDEA)
			return new GuiIdea(player);
		if(ID == GuiIds.GUI_BOOKOFWONDERING)
			return new GuiBookOfWondering(player);
		if(ID == GuiIds.GUI_IDEABUILDER)
		    return new GuiIdeaBuilder(player.inventory, (TileIdeaBuilder)world.getBlockTileEntity(x, y, z));
		if(ID == GuiIds.GUI_CRAFTINGTABLE)
            return new GuiCrafting(player.inventory, world, x, y, z);
		if(ID == GuiIds.GUI_DUALFURNACE)
		    return new GuiDualFurnace(player.inventory, world, x, y, z);
		if(ID == GuiIds.GUI_SUPER_CONTAINER)
		    return new GuiSuperContainer((ContainerSuperContainer)getServerGuiElement(ID, player, world, x, y, z));
		return null;
	}
	
}
