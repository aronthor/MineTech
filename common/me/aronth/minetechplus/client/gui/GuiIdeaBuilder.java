package me.aronth.minetechplus.client.gui;

import me.aronth.minetechplus.blocks.tileentitys.TileIdeaBuilder;
import me.aronth.minetechplus.ideas.Idea;
import me.aronth.minetechplus.inventory.ContainerIdeaBuilder;
import me.aronth.minetechplus.items.ItemIdea;
import me.aronth.minetechplus.lib.Constants;
import me.aronth.minetechplus.lib.Reference;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class GuiIdeaBuilder extends GuiContainer{

    private TileIdeaBuilder master;
    private GuiInfo bubble = new GuiInfo();
    
    public GuiIdeaBuilder(IInventory inv, TileIdeaBuilder builder) {
        super(new ContainerIdeaBuilder(inv, builder));
        master = builder;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        //this.zLevel = -10f;
        GL11.glColor4f(1f, 1f, 1f, 1f);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.mc.renderEngine.bindTexture(Reference.GUI_IDAEBUILDER_TEXTURE);
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        //this.zLevel = 0f;
        //GuiInfo bubble = new GuiInfo(x+20, y+53, master.getInfoMessage(), master.getHasError());
        //bubble.render();
        bubble.drawScreen(i, j, x+20, y+53, master.getInfoMessage(), master.getHasError());
        
        //this.drawGhostRecipe();
        //drawRecipeOverlay();
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        fontRenderer.drawString(LanguageRegistry.instance().getStringLocalization("gui.minetech.ideabuilder"), 8, 6, 4210752);
        fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
        
    }
    
    public void drawGhostRecipe(){
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        ItemStack idea = master.idea.getStackInSlot(0);
        if (idea != null){
            if (idea.getItem() instanceof ItemIdea) {
                if (idea.hasTagCompound()) {
                    if (idea.stackTagCompound.hasKey(Constants.NBT_IDEA)) {
                        Idea i = Idea.getIdeaById(idea.stackTagCompound.getInteger(Constants.NBT_IDEA));
                        ItemStack[] recipe = i.getGostIcon();
                        int index = 0;
                        for (int row = 0; row < 3; row++) {
                            for (int slot = 0; slot < 3; slot++) {
                                if(recipe[index]!=null)
                                    drawItemStack(recipe[index], x+48+(slot*18), y+17+(row*18), "");
                                
                                ++index;
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void drawItemStack(ItemStack par1ItemStack, int par2, int par3, String par4Str){
        RenderHelper.disableStandardItemLighting();
        GL11.glTranslatef(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        itemRenderer.zLevel = 200.0F;
        FontRenderer font = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
        if (font == null) font = fontRenderer;
        itemRenderer.renderItemAndEffectIntoGUI(font, this.mc.renderEngine, par1ItemStack, par2, par3);
        itemRenderer.renderItemOverlayIntoGUI(font, this.mc.renderEngine, par1ItemStack, par2, par3 - 0, par4Str);
        this.zLevel = 0.0F;
        itemRenderer.zLevel = 0.0F;
        RenderHelper.enableStandardItemLighting();
    }
    
    public void drawRecipeOverlay(){
        RenderHelper.disableStandardItemLighting();
        //this.zLevel = -1.0F;
        int x = ( (width - xSize) / 2 ) + 47;
        int y = ( (height - ySize) / 2 ) + 16;
        int size = 54;
        
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1f, 1f, 1f, 0.3f);
        this.mc.renderEngine.bindTexture(Reference.GUI_IDAEBUILDER_TEXTURE);
        this.drawCraftingGrid(x, y, xSize, 0, size, size);
        
        //this.zLevel = 0.0F;
        RenderHelper.enableStandardItemLighting();
    }
    
    
    public void drawCraftingGrid(int par1, int par2, int par3, int par4, int par5, int par6){
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
        tessellator.draw();
    }
}
