package me.aronth.minetechplus.items;

import java.util.List;

import me.aronth.minetechplus.core.helpers.NBTTagHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemIdea extends MTItem {

	public ItemIdea(int itemId) {
		super(itemId);
		this.setUnlocalizedName("Idea");
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setMaxStackSize(1);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack stack, EntityPlayer me, List list, boolean par4) {
		try{
			list.add("Crafted:"+NBTTagHelper.getString(stack, "resault"));
			for(int i = 0; i < NBTTagHelper.getInt(stack, "craftingSlots"); i++){
				if(!list.contains(NBTTagHelper.getString(stack, "matrix"+i)) && NBTTagHelper.getString(stack, "matrix"+i) != "-"){
					list.add(NBTTagHelper.getString(stack, "matrix"+i));
				}
			}
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
	}


}
