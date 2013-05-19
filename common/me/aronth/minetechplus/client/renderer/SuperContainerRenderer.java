package me.aronth.minetechplus.client.renderer;

import me.aronth.minetechplus.blocks.tileentitys.TileSuperContainer;
import me.aronth.minetechplus.lib.Textures;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;

public class SuperContainerRenderer extends TileEntitySpecialRenderer{

    //public ModelSuperContainer model = new ModelSuperContainer();
    
    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float tick) {
        
        ModelChest modelchest = new ModelChest();
        
        /*glPushMatrix();
        //glDisable(GL_LIGHTING);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        //glRotatef(180f, 1f, 0f, 0f);
        int f = ((TileSuperContainer)tile).facing;
        if(f == 0)
            glTranslated(x, y+1, z);
        if(f == 1)
            glTranslated(0, 0, 0);
        if(f == 2)
            glTranslated(0, 0, 0);
        if(f == 3)
            glTranslated(0, 0, 0);

        glRotatef((90*((TileSuperContainer)tile).facing)-180, 0, 1, 0);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.SUPER_CONTAINER);
        
        (new ModelChest()).renderAll();
        //model.render();
        

        //glEnable(GL_LIGHTING);
        glPopMatrix();*/
        
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_LIGHT1);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)x, (float)y + 1.0F, (float)z + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        
        short short1 = 0;
        int i = ((TileSuperContainer)tile).facing;
        
        if (i == 0)
        {
            short1 = -180;
        }

        if (i == 1)
        {
            short1 = -90;
        }

        if (i == 2)
        {
            short1 = 0;
        }

        if (i == 3)
        {
            short1 = 90;
        }

        /*if (i == 2 && par1TileEntityChest.adjacentChestXPos != null)
        {
            GL11.glTranslatef(1.0F, 0.0F, 0.0F);
        }

        if (i == 5 && par1TileEntityChest.adjacentChestZPosition != null)
        {
            GL11.glTranslatef(0.0F, 0.0F, -1.0F);
        }*/

        TileSuperContainer superContainer = (TileSuperContainer)tile;
        
        GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        /*float f1 = superContainer.prevLidAngle + (superContainer.lidAngle - superContainer.prevLidAngle) * tick;

        f1 = 1.0F - f1;
        f1 = 1.0F - f1 * f1 * f1;*/
        modelchest.chestLid.rotateAngleX = 0-superContainer.lidAngle;//-(f1 * (float)Math.PI / 2.0F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.SUPER_CONTAINER);
        modelchest.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

}
