package me.aronth.minetechplus.items;

import me.aronth.minetechplus.core.MineTechPlus;
import me.aronth.minetechplus.core.Reference;
import me.aronth.minetechplus.core.helpers.NBTTagHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
		/*if(NBTTagHelper.hasTag(stack, "owner") && NBTTagHelper.getString(stack, "owner") == player.username){
			player.sendChatToPlayer("A Gui should open !!");
		}else{
			if(NBTTagHelper.hasTag(stack, "owner")){
				player.sendChatToPlayer("-- This is not your book !!");
				if(Reference.DEBUG)System.out.println(player.username + " tried too open " + NBTTagHelper.getString(stack, "owner") + " 's book");
			}else{
				NBTTagHelper.setString(stack, "owner", player.username);
			}
		}*/
		
		NBTTagCompound tags = player.getEntityData();
		tags.getCompoundTag(Reference.MOD_ID+":ideas");
		if(tags.hasKey("ideasGotten")){
			player.sendChatToPlayer("you have ideas !!");
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
