package me.aronth.minetechplus.blocks;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.blocks.tileentitys.TileWorkstation;
import me.aronth.minetechplus.core.ConfigHandler;
import me.aronth.minetechplus.core.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWorkstation extends MTBlockContainer {
    
	public BlockWorkstation(int id, Material material) {
		super(id, material);
		this.setUnlocalizedName("blockWorkstation");
		this.setCreativeTab(MineTechPlus.tab);
	}

	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
	    //System.out.println(par2 + ":" + par3  + ":" + par4  + ":" + par5  + ":" + par6);
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
        if(par1World.getBlockId(par2+1, par3, par4) == MineTechPlus.instance.config.IDIdeaBuilder)
            par1World.setBlockToAir(par2+1, par3, par4);
        if(par1World.getBlockId(par2-1, par3, par4) == MineTechPlus.instance.config.IDIdeaBuilder)
            par1World.setBlockToAir(par2-1, par3, par4);
        if(par1World.getBlockId(par2, par3, par4+1) == MineTechPlus.instance.config.IDIdeaBuilder)
            par1World.setBlockToAir(par2, par3, par4+1);
        if(par1World.getBlockId(par2, par3, par4-1) == MineTechPlus.instance.config.IDIdeaBuilder)
            par1World.setBlockToAir(par2, par3, par4-1);
    }
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer me, int par6, float par7, float par8, float par9) {
	    if(me.getHeldItem() != null && me.getHeldItem().isItemEqual(new ItemStack(MineTechPlus.instance.blocks.blockIdeaBuilder))){
	        //System.out.println("IDAE BUILDER");
	        //TileWorkstation station = (TileWorkstation)world.getBlockTileEntity(x, y, z);
	        // station.
	        return false;
	    }
	    
		if(!me.isSneaking()){
			me.openGui(MineTechPlus.instance, Reference.GUI_WORKSTATION, world, x, y, z);
			//me.sendChatToPlayer("OPEN GUI NOW !!");
			((TileWorkstation)world.getBlockTileEntity(x, y, z)).findBookcases();
			return true;
		}
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		//this.blockIcon = reg.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	    this.blockIcon = reg.registerIcon(this.getTexture());
	}
	
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
	    return true;
	}
	
	@Override
    public TileEntity createNewTileEntity(World world) {
        return new TileWorkstation();
    }

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return ConfigHandler.renderId;
	}

}
