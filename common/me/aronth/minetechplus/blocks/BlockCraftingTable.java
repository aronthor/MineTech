package me.aronth.minetechplus.blocks;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.blocks.tileentitys.TileCraftingTable;
import me.aronth.minetechplus.blocks.tileentitys.TileWorkstation;
import me.aronth.minetechplus.core.ConfigHandler;
import me.aronth.minetechplus.lib.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCraftingTable extends MTBlockContainer{

    public Icon iconSide;
    
    public BlockCraftingTable(int id, Material material) {
        super(id, material);
        this.setUnlocalizedName("blockCraftingTable");
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
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer me, int par6, float par7, float par8, float par9) {
        if(!me.isSneaking()){
            me.openGui(MineTechPlus.instance, Reference.GUI_CRAFTINGTABLE, world, x, y, z);
            return true;
        }
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTexture());
        this.iconSide = reg.registerIcon(this.getTexture("blockCraftingTableSide"));
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileCraftingTable();
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

}
