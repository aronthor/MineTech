package me.aronth.minetechplus.items;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.lib.GuiIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemBookWondering extends MTItem{

	public ItemBookWondering(int id) {
		super(id);
		this.setUnlocalizedName("bookOfWondering");
		this.setMaxStackSize(1);
		this.setCreativeTab(MineTechPlus.tab);
	}
	
	// This is for opening the gui of the book
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(!player.isSneaking()){
			player.openGui(MineTechPlus.instance, GuiIds.GUI_BOOKOFWONDERING, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		
        return stack;
    }

	// For setting the books owner !!
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
	    if(!stack.hasTagCompound())
	        stack.stackTagCompound = new NBTTagCompound();
	    stack.stackTagCompound.setString("owner", player.username);
	}

	// For displaying the books name correctly
    public String getItemDisplayName(ItemStack stack){
        // ".name" is added by vanilla Item.getItemDisplayName !!
    	String name = LanguageRegistry.instance().getStringLocalization(this.getUnlocalizedName()+".name");
    	
    	/*if(NBTTagHelper.hasTag(stack, "owner")){
			name = NBTTagHelper.getString(stack, "owner") + "'s " + name;
		}*/
    	if(stack.hasTagCompound() && stack.stackTagCompound.hasKey("owner"))
    	    name = stack.stackTagCompound.getString("owner") + "'s" + name;
    	
        return name;
    }
	
}
