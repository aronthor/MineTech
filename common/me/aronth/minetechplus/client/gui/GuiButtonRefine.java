package me.aronth.minetechplus.client.gui;

import me.aronth.minetechplus.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

public class GuiButtonRefine extends GuiButton{
    
    public GuiButtonRefine(int par1, int par2, int par3){
        super(par1, par2, par3, 20, 20, "");
    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft par1Minecraft, int par2, int par3){
        if (this.drawButton){
            par1Minecraft.renderEngine.bindTexture(Reference.GUI_BUTTON_REFINE);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            int k = 0;

            if (flag){
                k += this.height;
            }

            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, k, this.width, this.height);
        }
    }

}
