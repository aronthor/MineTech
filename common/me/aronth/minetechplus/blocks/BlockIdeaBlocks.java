package me.aronth.minetechplus.blocks;

import java.util.List;
import java.util.Random;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.blocks.tileentitys.TileDualFurnace;
import me.aronth.minetechplus.blocks.tileentitys.TileSuperContainer;
import me.aronth.minetechplus.core.ConfigHandler;
import me.aronth.minetechplus.core.helpers.ItemStackHelper;
import me.aronth.minetechplus.lib.GuiIds;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockIdeaBlocks extends MTBlockContainer{

    public static String[] names = {"DualFurnace", "SuperContainer"};
    
    public Icon[] blockIconFurnace = new Icon[5];
    
    public BlockIdeaBlocks(int id, Material material) {
        super(id, material);
        this.setCreativeTab(MineTechPlus.tab);
        this.setUnlocalizedName("blockIdeaBlocks");
    }
    
    public int damageDropped(int meta){
        return meta;
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer me, int par6, float par7, float par8, float par9) {
        if(!me.isSneaking()){
            int meta = world.getBlockMetadata(x, y, z);
            TileEntity tile = world.getBlockTileEntity(x, y, z);
            if(meta == 0 && tile != null && tile instanceof TileDualFurnace){
                PacketDispatcher.sendPacketToPlayer(tile.getDescriptionPacket(), (Player)me);
                me.openGui(MineTechPlus.instance, GuiIds.GUI_DUALFURNACE, world, x, y, z);
                return true;
            }
            if(meta == 1 && tile != null && tile instanceof TileSuperContainer){
                me.openGui(MineTechPlus.instance, GuiIds.GUI_SUPER_CONTAINER, world, x, y, z);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void breakBlock(World w, int x, int y, int z, int par5, int par6){
        TileEntity tile = w.getBlockTileEntity(x, y, z);
        if(tile instanceof TileDualFurnace)
            ItemStackHelper.dropStacks(((TileDualFurnace)tile).furnaceInv.inv ,w , x, y, z);
        if(tile instanceof TileSuperContainer)
            ItemStackHelper.dropStacks(((TileSuperContainer)tile).inv.getInventory() ,w , x, y, z);
        super.breakBlock(w, x, y, z, par5, par6);
    }
    
    /*public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z){
        int meta = world.getBlockMetadata(x, y, z);
        
        if(meta == 1){
            setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }else{
            setBlockBounds(0, 0, 0, 1, 1, 1);
        }
    }*/
    
    public TileEntity createTileEntity(World world, int metadata){
        if(metadata == 0)// Dual Furnace
            return new TileDualFurnace();
        if(metadata == 1)// Super Container
            return new TileSuperContainer();
        return null;
    }
    
    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving placer, ItemStack itemStack){
        int direction = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        
        TileEntity te = world.getBlockTileEntity(i, j, k);
        if (te != null && te instanceof TileDualFurnace){
            ((TileDualFurnace) te).facing = direction;
            world.markBlockForUpdate(i, j, k);
        }
        
        if (te != null && te instanceof TileSuperContainer){
            ((TileSuperContainer) te).facing = direction;
            world.markBlockForUpdate(i, j, k);
        }
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
    
    public Icon getIcon(int side, int meta){
        if(meta == 0){
            if(side == 0)
                return blockIconFurnace[3];
            if(side == 1)
                return blockIconFurnace[2];
            if(side == 5)
                return blockIconFurnace[1];
            return blockIconFurnace[0];
        }
        if(meta == 1){
            return blockIcon;
        }
        return blockIcon;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg){
       blockIcon = reg.registerIcon(getTexture("blockWorkstation"));
       blockIconFurnace[0] = reg.registerIcon(getTexture("blockDualFurnace"));
       blockIconFurnace[1] = reg.registerIcon(getTexture("blockDualFurnaceFront"));
       blockIconFurnace[2] = reg.registerIcon(getTexture("blockDualFurnaceTop"));
       blockIconFurnace[3] = reg.registerIcon(getTexture("blockDualFurnaceBottom"));
       blockIconFurnace[4] = reg.registerIcon(getTexture("blockDualFurnaceLit"));
    }

    @SuppressWarnings({
            "rawtypes", "unchecked"
    })
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List){
          for(int i = 0; i < names.length; i++){
                 par3List.add(new ItemStack(par1, 1, i));
          }
    }
    
    public Icon getIconFor(int block, int face){
        if(block == 0)
            return blockIconFurnace[face];
        return blockIcon;
    }
    
    public void randomDisplayTick(World world, int x, int y, int z, Random rand){
        
        int meta = world.getBlockMetadata(x, y, z);
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if(meta == 0 && tile != null && tile instanceof TileDualFurnace && ((TileDualFurnace)tile).isLit){ // Dual Furnace
            float pX = x + 0.5F;
            float pY = y + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
            float pZ = z + 0.5F;
            float pRand = rand.nextFloat() * 0.6F - 0.3F;
            float halfBlockLength = 0.52F;
            int direction = ((TileDualFurnace)tile).facing;
            if(direction == 0){
                world.spawnParticle("smoke", (double)(pX - pRand), (double)pY, (double)(pZ - halfBlockLength), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(pX - pRand), (double)pY, (double)(pZ - halfBlockLength), 0.0D, 0.0D, 0.0D);
            }
            if(direction == 1){
                world.spawnParticle("smoke", (double)(pX + halfBlockLength), (double)pY, (double)(pZ - pRand), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(pX + halfBlockLength), (double)pY, (double)(pZ - pRand), 0.0D, 0.0D, 0.0D);
            }
            if(direction == 2){
                world.spawnParticle("smoke", (double)(pX - pRand), (double)pY, (double)(pZ + halfBlockLength), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(pX - pRand), (double)pY, (double)(pZ + halfBlockLength), 0.0D, 0.0D, 0.0D);
            }
            if(direction == 3){
                world.spawnParticle("smoke", (double)(pX - halfBlockLength), (double)pY, (double)(pZ - pRand), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(pX - halfBlockLength), (double)pY, (double)(pZ - pRand), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
