package me.aronth.minetechplus.client.gui;

import me.aronth.minetechplus.inventory.ContainerSuperContainer;
import me.aronth.minetechplus.lib.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class GuiSuperContainer extends GuiContainer{

    public GuiSuperContainer(ContainerSuperContainer par1Container) {
        super(par1Container);
        this.ySize = 222;
    }
    
    protected void drawGuiContainerForegroundLayer(int par1, int par2){
        this.fontRenderer.drawStringWithShadow(LanguageRegistry.instance().getStringLocalization("gui.minetech.superContainer"), 8, 4, 0xffffff);
        this.fontRenderer.drawStringWithShadow(StatCollector.translateToLocal("container.inventory"), 8, 128, 0xffffff);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        mc.renderEngine.bindTexture(Textures.GUI_SUPER_CONTAINER);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

}
