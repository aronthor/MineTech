package me.aronth.minetechplus.client.gui;

import java.util.Iterator;
import java.util.List;

import me.aronth.minetechplus.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiInfo extends GuiScreen{

    private int locX;
    private int locY;
    private String msg;
    private boolean error;
    
    protected static RenderItem itemRenderer = new RenderItem();
    public FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
    
    /*public GuiInfo(int x, int y, String text, boolean err){
        locX = x;
        locY = y;
        msg = text;
        error = err;
    }*/
    
    public void drawScreen(int mouseX, int mouseY, int x, int y, String text, boolean err){
        
        locX = x;
        locY = y;
        msg = text;
        error = err;
        
        if(error)
            Minecraft.getMinecraft().renderEngine.bindTexture(Reference.GUI_INFO_RED);
        else
            Minecraft.getMinecraft().renderEngine.bindTexture(Reference.GUI_INFO_GREEN);
        
        this.drawInfoIcon(locX, locY);
        
        if(isPointInRegion(locX, locY, 11, 11, mouseX, mouseY)){
            //System.out.println("Hover");
            if(msg != null){
                //this.drawHoveringText(Arrays.asList(new String[] {msg}), mouseX, mouseY, Minecraft.getMinecraft().fontRenderer);
                //GL11.glTranslatef(0, 0, 200.0F);
                fontRenderer.drawStringWithShadow(msg, mouseX+2, mouseY+2, 0xffffff);
                //GL11.glTranslatef(0, 0, 0.0F);
            }
        }
        
    }
    
    public void drawInfoIcon(int par1, int par2){
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 11), (double)this.zLevel, (double)0, (double)11);
        tessellator.addVertexWithUV((double)(par1 + 11), (double)(par2 + 11), (double)this.zLevel, (double)11, (double)11);
        tessellator.addVertexWithUV((double)(par1 + 11), (double)(par2 + 0), (double)this.zLevel, (double)11, (double)0);
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)0, (double)0);
        tessellator.draw();
    }
    
    protected boolean isPointInRegion(int x, int y, int w, int h, int mx, int my){
        if(mx >= x && mx <= x+w && my >= y && my <= y+h)
            return true;
        return false;
    }
    
    @SuppressWarnings("rawtypes")
    protected void drawHoveringText(List par1List, int par2, int par3, FontRenderer font)
    {
        if (!par1List.isEmpty())
        {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int k = 0;
            Iterator iterator = par1List.iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();
                int l = font.getStringWidth(s);

                if (l > k)
                {
                    k = l;
                }
            }

            int i1 = par2 + 12;
            int j1 = par3 - 12;
            int k1 = 8;

            if (par1List.size() > 1)
            {
                k1 += 2 + (par1List.size() - 1) * 10;
            }

            if (i1 + k > this.width)
            {
                i1 -= 28 + k;
            }

            if (j1 + k1 + 6 > this.height)
            {
                j1 = this.height - k1 - 6;
            }

            this.zLevel = 800.0F;
            //itemRenderer.zLevel = 800.0F;
            int l1 = -267386864;
            this.drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
            this.drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
            this.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
            this.drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
            this.drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
            int i2 = 1347420415;
            int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
            this.drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
            this.drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
            this.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
            this.drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);
            

            for (int k2 = 0; k2 < par1List.size(); ++k2)
            {
                String s1 = (String)par1List.get(k2);
                font.drawStringWithShadow(s1, i1, j1, -1);

                if (k2 == 0)
                {
                    j1 += 2;
                }

                j1 += 10;
            }

            this.zLevel = 0.0F;
            //itemRenderer.zLevel = 0.0F;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }
    
}
