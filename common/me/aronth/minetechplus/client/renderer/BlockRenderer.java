package me.aronth.minetechplus.client.renderer;

import me.aronth.minetechplus.blocks.BlockCraftingTable;
import me.aronth.minetechplus.blocks.BlockIdeaBlocks;
import me.aronth.minetechplus.blocks.BlockIdeaBuilder;
import me.aronth.minetechplus.blocks.BlockWorkstation;
import me.aronth.minetechplus.blocks.tileentitys.TileDualFurnace;
import me.aronth.minetechplus.core.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
//import org.lwjgl.opengl.;

public class BlockRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if(block instanceof BlockWorkstation){ // Workstation
			// Renders the table it self
			renderer.setRenderBounds(0, 0.7, 0, 1, 1, 1);
			renderDo(renderer, block, metadata);
			
			// All !! the feet of the table ! :D
			renderer.setRenderBounds(0.05, 0, 0.05, 0.3, 0.99, 0.3);
			renderDo(renderer, block, metadata);
			renderer.setRenderBounds(0.7, 0, 0.05, 0.95, 0.99, 0.3);
			renderDo(renderer, block, metadata);
			renderer.setRenderBounds(0.7, 0, 0.7, 0.95, 0.99, 0.95);
			renderDo(renderer, block, metadata);
			renderer.setRenderBounds(0.05, 0, 0.7, 0.3, 0.99, 0.95);
			renderDo(renderer, block, metadata);	
		}
		if(block instanceof BlockIdeaBuilder || block instanceof BlockCraftingTable){
		    renderer.setRenderBounds(0, 0.7, 0, 1, 0.9, 1);
		    renderDo(renderer, block, metadata);
		}
		if(block instanceof BlockIdeaBlocks){
		    if(metadata == 0){
		        renderer.setRenderBoundsFromBlock(block);
		        renderDo(renderer, block, metadata);
		    }
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(block instanceof BlockWorkstation){
			renderWorkstation(world, x, y, z, block, modelId, renderer);
		}
		if(block instanceof BlockIdeaBuilder || block instanceof BlockCraftingTable)
		    renderIdeaBuilder(world, x, y, z, block, modelId, renderer);
		if(block instanceof BlockIdeaBlocks)
		    renderBlockIdeaBlocks(world, x, y, z, (BlockIdeaBlocks)block, modelId, renderer);
		
		return false;
	}

	private void renderBlockIdeaBlocks(IBlockAccess world, int x, int y, int z, BlockIdeaBlocks block, int modelId, RenderBlocks renderer) {
        int meta = world.getBlockMetadata(x, y, z);
        if(meta == 0)
            renderDualFurnace(world, x, y, z, block, modelId, renderer);
    }

    private void renderDualFurnace(IBlockAccess world, int x, int y, int z, BlockIdeaBlocks block, int modelId, RenderBlocks renderer) {
        TileDualFurnace tile = (TileDualFurnace)world.getBlockTileEntity(x, y, z);
        int direction = tile.facing;
        boolean isLit = tile.isLit;
        if(direction > 3)direction = 0;
        
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);
        
        renderer.renderFaceZNeg(block, x, y, z, block.getIconFor(0, (direction == 0 ? (isLit ? 4 : 1) : 0)));
        renderer.renderFaceXPos(block, x, y, z, block.getIconFor(0, (direction == 1 ? (isLit ? 4 : 1) : 0)));
        renderer.renderFaceZPos(block, x, y, z, block.getIconFor(0, (direction == 2 ? (isLit ? 4 : 1) : 0)));
        renderer.renderFaceXNeg(block, x, y, z, block.getIconFor(0, (direction == 3 ? (isLit ? 4 : 1) : 0)));
        
        //System.out.println("RERENDERED");
    }

    private void renderIdeaBuilder(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
	    // Renders the table it self
	    renderer.setRenderBounds(0.1, 0.7, 0.1, 0.9, 0.9, 0.9);
	    //renderer.renderFaceYPos(block, x, y, z, block.getBlockTextureFromSide(1));
        
	    renderer.renderStandardBlock(block, x, y, z);
    }
	
    private void renderWorkstation(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        
        // Renders the table it self
		renderer.setRenderBounds(0, 0.7, 0, 1, 1, 1);
		renderer.renderStandardBlock(block, x, y, z);
		
		// All !! the feet of the table ! :D
		renderer.setRenderBounds(0.05, 0, 0.05, 0.3, 0.99, 0.3);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.setRenderBounds(0.7, 0, 0.05, 0.95, 0.99, 0.3);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.setRenderBounds(0.7, 0, 0.7, 0.95, 0.99, 0.95);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.setRenderBounds(0.05, 0, 0.7, 0.3, 0.99, 0.95);
		renderer.renderStandardBlock(block, x, y, z);
		
		renderer.setOverrideBlockTexture(Block.blockIron.getBlockTextureFromSide(1));
		
		if(BlockWorkstation.isBlockAModule(world.getBlockTileEntity(x, y, z+1))){
		    renderer.setRenderBounds(0.45, 0.6, 0.5, 0.55, 0.7, 1.5);
		    renderer.renderStandardBlock(block, x, y, z);
		}
		
		if(BlockWorkstation.isBlockAModule(world.getBlockTileEntity(x, y, z-1))){
            renderer.setRenderBounds(0.45, 0.6, -0.5, 0.55, 0.7, 0.5);
            renderer.renderStandardBlock(block, x, y, z);
        }
		
		if(BlockWorkstation.isBlockAModule(world.getBlockTileEntity(x+1, y, z))){
            renderer.setRenderBounds(0.5, 0.6, 0.45, 1.5, 0.7, 0.55);
            renderer.renderStandardBlock(block, x, y, z);
        }
		
		if(BlockWorkstation.isBlockAModule(world.getBlockTileEntity(x-1, y, z))){
            renderer.setRenderBounds(-0.5, 0.6, 0.45, 0.5, 0.7, 0.55);
            renderer.renderStandardBlock(block, x, y, z);
        }
		
		renderer.clearOverrideBlockTexture();
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	private void renderDo(RenderBlocks renderblocks, Block block, int meta){
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
	
	@Override
	public int getRenderId() {
		ConfigHandler.renderId = RenderingRegistry.getNextAvailableRenderId();
		return ConfigHandler.renderId;
	}

}
