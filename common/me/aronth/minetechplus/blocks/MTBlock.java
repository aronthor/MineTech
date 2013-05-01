package me.aronth.minetechplus.blocks;

import me.aronth.minetechplus.core.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MTBlock extends Block{

    public String bName;
    
	public MTBlock(int id, Material material) {
		super(id, material);
	}
	
	@Override
	public Block setUnlocalizedName(String blockName){
	    bName = blockName;
	    return this;
	}
	
	public String getTexture(){
	    return Reference.MOD_ID.toLowerCase()+":"+bName;
	}
	
	public String getTexture(String s){
        return Reference.MOD_ID.toLowerCase()+":"+s;
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
