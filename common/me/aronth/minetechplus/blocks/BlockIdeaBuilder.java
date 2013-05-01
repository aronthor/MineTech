package me.aronth.minetechplus.blocks;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.blocks.tileentitys.TileIdeaBuilder;
import me.aronth.minetechplus.blocks.tileentitys.TileWorkstation;
import me.aronth.minetechplus.core.ConfigHandler;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockIdeaBuilder extends MTBlockContainer{
    
    @SideOnly(Side.CLIENT)
    private Icon iconSide;

    public BlockIdeaBuilder(int id, Material material) {
        super(id, material);
        this.setUnlocalizedName("blockIdeaBuilder");
        this.setCreativeTab(MineTechPlus.tab);
        this.setBlockBounds(0.1f, 0.7f, 0.1f, 0.9f, 0.9f, 0.9f);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2){
        if(par1 == 1)
            return blockIcon;
        return iconSide;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTexture());
        this.iconSide = reg.registerIcon(this.getTexture("blockIdeaBuilderSide"));
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileIdeaBuilder();
    }
    
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
        return true;
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
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z){
        if(world.getBlockTileEntity(x+1, y, z) instanceof TileWorkstation)
            return true;
        if(world.getBlockTileEntity(x-1, y, z) instanceof TileWorkstation)
            return true;
        if(world.getBlockTileEntity(x, y, z+1) instanceof TileWorkstation)
            return true;
        if(world.getBlockTileEntity(x, y, z-1) instanceof TileWorkstation)
            return true;
        return false;
    }
        
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta){
        /*
         * Check for the block to be a workstation
         * Workstation can only have 1 of each module
         * TileWorkstation remembers it "addons"
         * "addons" remember there owners !! :D
         */
        
        /*
         * This function runs before the block is actually placed
         * x, y and z are the locatin the block is being placed..
         * side is on what side you clicked on a block
         * hitX, hitY and hitZ is the coord of the pointer points at on that exact block
         */
        
        /*
        System.out.println("x:"+hitX);
        System.out.println("y:"+hitY);
        System.out.println("z:"+hitZ);
        */
        
        /*if(world.getBlockId((int)hitX, (int)hitY, (int)hitZ) == MineTechPlus.instance.config.IDIdeaBuilder){
            System.out.println("Yeahhpp");
        }else{
            System.out.println("Nooppee");
        }*/
        
        return meta;
    }

}
