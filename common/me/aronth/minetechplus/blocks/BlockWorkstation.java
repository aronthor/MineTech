package me.aronth.minetechplus.blocks;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.core.ConfigHandler;
import me.aronth.minetechplus.core.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWorkstation extends MTBlockContainer {

	public BlockWorkstation(int id, Material material) {
		super(id, material);
		this.setUnlocalizedName("blockWorkstation");
		this.setCreativeTab(MineTechPlus.tab);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer me, int par6, float par7, float par8, float par9) {
		if(!me.isSneaking() && !world.isRemote){
			//me.openGui(MineTechPlus.instance, Reference.GUI_WORKSTATION, world, x, y, z);
			//me.sendChatToPlayer("OPEN GUI NOW !!");
		}
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		this.blockIcon = reg.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
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
