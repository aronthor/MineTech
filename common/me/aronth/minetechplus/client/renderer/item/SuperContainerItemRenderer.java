package me.aronth.minetechplus.client.renderer.item;

import me.aronth.minetechplus.lib.Textures;
import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class SuperContainerItemRenderer implements IItemRenderer{

    private ModelChest model = new ModelChest();
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        int meta = item.getItemDamage();
        
        if(meta == 1)
            return true;
        
        return false;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type) {
            case ENTITY: {
                renderContainer(0.5F, 0.7F, 0.5F, 1.0F);
                return;
            }
            case EQUIPPED: {
                renderContainer(1.0F, 1.0F, 1.0F, 1.0F);
                return;
            }
            case INVENTORY: {
                renderContainer(0.0F, 0.1F, 0.0F, 1.0F);
                return;
            }
            default:
                return;
        }
    }

    public void renderContainer(float x, float y, float z, float scale){
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        // Scale, Translate, Rotate
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(180F, 1F, 0, 0);
        GL11.glRotatef(-90f, 0, 1, 0);

        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.SUPER_CONTAINER);

        // Render
        model.renderAll();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    
}
