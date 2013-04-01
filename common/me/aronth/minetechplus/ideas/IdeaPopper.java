package me.aronth.minetechplus.ideas;

import java.util.Random;

import me.aronth.minetechplus.core.ItemHandler;
import me.aronth.minetechplus.core.helpers.NBTTagHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.ICraftingHandler;

public class IdeaPopper implements ICraftingHandler{
	
	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,	IInventory craftMatrix) {
		popOutAnIdea(player.worldObj, player, item, craftMatrix);
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		//popOutAnIdea(player.worldObj, player);
	}
	
	public void popOutAnIdea(World world, EntityPlayer player, ItemStack item, IInventory crafting){
		Random rand = new Random();
		
		if(rand.nextInt(10) == 1 && !world.isRemote){ // Thanks to Zorn_Taov for helping fixing phantom items
			
			player.sendChatToPlayer("-- You just got an idea! --");
			ItemStack idea = new ItemStack(ItemHandler.idea, 1);
				
			for(int i = 0; i < crafting.getSizeInventory(); i++){
				if(crafting.getStackInSlot(i) != null){
					NBTTagHelper.setString(idea, "matrix"+i, crafting.getStackInSlot(i).getDisplayName());
				}else{
					NBTTagHelper.setString(idea, "matrix"+i, "-");
				}
			}
			
			NBTTagHelper.setInteger(idea, "craftingSlots", crafting.getSizeInventory());
			NBTTagHelper.setString(idea, "resault", item.getDisplayName());
				
			EntityItem entityIdea = new EntityItem(world, player.posX, player.posY, player.posZ, idea);
			world.spawnEntityInWorld(entityIdea);		
		}
	}

}
