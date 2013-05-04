package me.aronth.minetechplus.client.gui;

import me.aronth.minetechplus.blocks.tileentitys.TileIdeaBuilder;
import me.aronth.minetechplus.core.Reference;
import me.aronth.minetechplus.inventory.ContainerIdeaBuilder;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

public class GuiIdeaBuilder extends GuiContainer{

    public GuiIdeaBuilder(IInventory inv, TileIdeaBuilder builder) {
        super(new ContainerIdeaBuilder(inv, builder));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        this.mc.renderEngine.bindTexture(Reference.GUI_IDAEBUILDER_TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        //this.drawStack(new ItemStack(Item.appleGold), 10, 10, "");
    }
    
    /*private void drawStack(ItemStack par1ItemStack, int par2, int par3, String par4Str){
        GL11.glTranslatef(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        itemRenderer.zLevel = 200.0F;
        FontRenderer font = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
        if (font == null) font = fontRenderer;
        itemRenderer.renderItemAndEffectIntoGUI(font, this.mc.renderEngine, par1ItemStack, par2, par3);
        itemRenderer.renderItemOverlayIntoGUI(font, this.mc.renderEngine, par1ItemStack, par2, par3 - 0, par4Str);
        this.zLevel = 0.0F;
        itemRenderer.zLevel = 0.0F;
    }*/
    
    /*@Override
    protected void drawSlotInventory(Slot slot){
        if(slot instanceof SlotGhost){
            IdeaBuilderMatrix matrix = (IdeaBuilderMatrix)slot.inventory;
            if(matrix.isIdeaBuildDefined()){
                Icon icon = matrix.getGhostIcon(slot.slotNumber);
                if(icon != null)
                    drawIcon(slot.xDisplayPosition, slot.yDisplayPosition, icon, 16, 16);
            }
        }else if(slot instanceof SlotIdea){
            
        }else{
            super.drawSlotInventory(slot);
        }
    }*/
    
    public void drawIcon(int slotX, int slotY, Icon icon, int width, int height){
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(slotX + 0), (double)(slotY + height), (double)this.zLevel, (double)icon.getMinU(), (double)icon.getMaxV());
        tessellator.addVertexWithUV((double)(slotX + width), (double)(slotY + height), (double)this.zLevel, (double)icon.getMaxU(), (double)icon.getMaxV());
        tessellator.addVertexWithUV((double)(slotX + width), (double)(slotY + 0), (double)this.zLevel, (double)icon.getMaxU(), (double)icon.getMinV());
        tessellator.addVertexWithUV((double)(slotX + 0), (double)(slotY + 0), (double)this.zLevel, (double)icon.getMinU(), (double)icon.getMinV());
        tessellator.draw();
    }

}
