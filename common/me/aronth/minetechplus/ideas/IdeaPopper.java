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

	private boolean phantom = false;
	
	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,	IInventory craftMatrix) {
		popOutAnIdea(player.worldObj, player, craftMatrix);
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		//popOutAnIdea(player.worldObj, player);
	}
	
	public void popOutAnIdea(World world, EntityPlayer player, IInventory crafting){
		Random rand = new Random();
		
		//if(rand.nextInt(MineTechPlus.instance.config.ideaChance) == 1){
		if(rand.nextInt(10) == 1){
			if(phantom == true){
				//player.sendChatToPlayer("An idea popped out !");
				//ItemStack idea = new ItemStack(MineTechPlus.instance.items.idea);
				ItemStack idea = new ItemStack(ItemHandler.idea);
				
				//NBTTagHelper.setString(idea, "matrix1", "Stone"); // Just Testing
				/*if(crafting instanceof InventoryCrafting){
					int craftingWidth = 10, craftingHeight = 10;
					while(((InventoryCrafting) crafting).getStackInRowAndColumn(craftingWidth, 0) == null)craftingWidth--;
					while(((InventoryCrafting) crafting).getStackInRowAndColumn(0, craftingHeight) == null)craftingHeight--;
					
					int num = 1;
					
					for(int h = 0; h < craftingHeight; h++){
						for(int w = 0; w < craftingWidth; w++){
							NBTTagHelper.setString(idea, "matrix"+num, ((InventoryCrafting) crafting).getStackInRowAndColumn(w, h).getDisplayName());
							num++;
						}
					}
					crafting.
					NBTTagHelper.setInteger(idea, "craftingSlots", num);
					
				}*/
				
				for(int i = 0; i < crafting.getSizeInventory(); i++){
					if(crafting.getStackInSlot(i) != null){
						NBTTagHelper.setString(idea, "matrix"+i, crafting.getStackInSlot(i).getDisplayName());
					}else{
						NBTTagHelper.setString(idea, "matrix"+i, "-");
					}
				}
				NBTTagHelper.setInteger(idea, "craftingSlots", crafting.getSizeInventory());
				
				
				
				EntityItem entityIdea = new EntityItem(world, player.posX, player.posY, player.posZ, idea);
				
				world.spawnEntityInWorld(entityIdea);
				phantom = false;
			}else{
				phantom = true;
			}
			
		}
	}

}
