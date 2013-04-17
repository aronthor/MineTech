package me.aronth.minetechplus.items;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.core.Reference;
import me.aronth.minetechplus.core.helpers.NBTTagHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
			player.openGui(MineTechPlus.instance, Reference.GUI_BOOKOFWONDERING, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		
        return stack;
    }

	// For setting the books owner !!
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		NBTTagHelper.setString(stack, "owner", player.username);
	}

	// For displaying the books name correctly
    public String getItemDisplayName(ItemStack stack){
    	String name = "Book of Wondering";
    	
    	if(NBTTagHelper.hasTag(stack, "owner")){
			name = NBTTagHelper.getString(stack, "owner") + "'s " + name;
		}
    	
        return name;
    }
	
}
