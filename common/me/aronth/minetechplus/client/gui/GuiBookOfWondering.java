package me.aronth.minetechplus.client.gui;

import me.aronth.minetechplus.core.Reference;
import me.aronth.minetechplus.ideas.IIdea;
import me.aronth.minetechplus.ideas.IdeaManager;
import me.aronth.minetechplus.inventory.ContainerBookOfWondering;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiBookOfWondering extends GuiContainer{

	private EntityPlayer me;
	//private ContainerBookOfWondering book;
	
	public GuiBookOfWondering(EntityPlayer player) {
		super(new ContainerBookOfWondering(player));
		//book = (ContainerBookOfWondering)this.inventorySlots;
		me = player;
		this.ySize = 236;
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
            fontRenderer.drawString(me.username + "'s Book Of Wondering", 8, 5, 4210752);
            fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
            
            int index = 1;
            for(IIdea idea : IdeaManager.instance.getUnlockedIdeas(me)){
            	fontRenderer.drawString(idea.getName(), 8, 57 + index * 7, 4210752);
            	index++;
            }
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(Reference.GUI_BOOKOFWONDERING_TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
