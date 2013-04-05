package me.aronth.minetechplus.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabMineTech extends CreativeTabs{

	public CreativeTabMineTech(int id, String name) {
		super(id, name);
	}
	
	@SideOnly(Side.CLIENT)
    public Item getTabIconItem(){
        return ItemHandler.bookOfWondering;
    }
	
	public String getTranslatedTabLabel(){
        return "MineTech+";
    }

}
