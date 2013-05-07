package me.aronth.minetechplus.client.gui;

import me.aronth.minetechplus.inventory.ContainerIdea;
import me.aronth.minetechplus.lib.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

public class GuiIdea extends GuiContainer{

	public GuiIdea(EntityPlayer me) {
		super(new ContainerIdea(me));
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
	    fontRenderer.setUnicodeFlag(true);
        fontRenderer.drawString(((ContainerIdea)this.inventorySlots).getIdeaName(), 8, 6, 4210752);
        fontRenderer.drawSplitString(((ContainerIdea)this.inventorySlots).getIdeaDescription(), 8, 75, 165, 4210752);
        fontRenderer.setUnicodeFlag(false);
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(Reference.GUI_IDEA_TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

	
	
}
