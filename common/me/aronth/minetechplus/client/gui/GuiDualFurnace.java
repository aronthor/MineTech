package me.aronth.minetechplus.client.gui;

import me.aronth.minetechplus.blocks.tileentitys.TileDualFurnace;
import me.aronth.minetechplus.inventory.ContainerDualFurnace;
import me.aronth.minetechplus.lib.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class GuiDualFurnace extends GuiContainer{

    private TileDualFurnace furnace;
    
    public GuiDualFurnace(InventoryPlayer playerInv, World w, int x, int y, int z){
        super(new ContainerDualFurnace(playerInv, w, x, y, z));
        furnace = (TileDualFurnace)w.getBlockTileEntity(x, y, z);
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2){
        this.fontRenderer.drawString(LanguageRegistry.instance().getStringLocalization("gui.minetech.dualFurnace"), 8, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1, 1, 1, 1);
        this.mc.renderEngine.bindTexture(Textures.GUI_TEXTURE_DUALFURNACE);
        int x = (width-xSize)/2;
        int y = (height-ySize)/2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        // Draw furnace elemtents
        if(furnace.fuelLeft > 0){
            int fuelLeft = furnace.getFuelLeftScaled(15);
            drawTexturedModalRect(x+41, y+35-2+15-fuelLeft, 176, 15-fuelLeft, 15, fuelLeft+2);
        }
        
        
        int laneOneCook = furnace.getCookLeftScaled(0, 16);
        if(furnace.furnaceInv.getStackInSlot(0) != null)drawTexturedModalRect(x+43, y+31-laneOneCook, 191, 16-laneOneCook, 3, laneOneCook);
        
        int laneTwoCook = furnace.getCookLeftScaled(1, 16);
        if(furnace.furnaceInv.getStackInSlot(1) != null)drawTexturedModalRect(x+51, y+31-laneTwoCook, 191, 16-laneTwoCook, 3, laneTwoCook);
    }
    
    
    
}
