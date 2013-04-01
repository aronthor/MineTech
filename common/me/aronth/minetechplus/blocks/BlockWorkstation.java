package me.aronth.minetechplus.blocks;

import me.aronth.minetechplus.core.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWorkstation extends MTBlockContainer{

	public BlockWorkstation(int id, Material material) {
		super(id, material);
		this.setUnlocalizedName("blockWorkstation");
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg){
        this.blockIcon = reg.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }
	
}
