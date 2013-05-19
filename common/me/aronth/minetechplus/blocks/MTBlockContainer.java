package me.aronth.minetechplus.blocks;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.aronth.minetechplus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MTBlockContainer extends BlockContainer{

    private String bName;
    
	public MTBlockContainer(int id, Material material) {
		super(id, material);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	public String getTexture(){
        return Reference.MOD_ID.toLowerCase()+":"+bName;
    }
    
    public String getTexture(String s){
        return Reference.MOD_ID.toLowerCase()+":"+s;
    }
	
	@Override
    public Block setUnlocalizedName(String blockName){
        bName = blockName;
        return this;
    }
    
    public String getUnlocalizedName(){
        return "tile.minetech." + this.bName;
    }
    
    @SideOnly(Side.CLIENT)
    public String getUnlocalizedName2(){
        return "tile.minetech." + this.bName;
    }
    
    public String getLocalizedName(){
        //return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
        return LanguageRegistry.instance().getStringLocalization(getUnlocalizedName()+".name");
    }

}
