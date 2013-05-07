package me.aronth.minetechplus.items;

import me.aronth.minetechplus.lib.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MTItem extends Item {

    //private String iName;
    
	public MTItem(int id) {
		super(id);
	}
	
	@Override
	public Item setUnlocalizedName(String name){
	    super.setUnlocalizedName("minetech."+name);
	    return this;
	}
	
	/*@Override
	public String getLocalizedName(ItemStack stack){
	    return LanguageRegistry.instance().getStringLocalization("item.minetech."+iName+".name");
	}*/

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(14));
    }
	
}
