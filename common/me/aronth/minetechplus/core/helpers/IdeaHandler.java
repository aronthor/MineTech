package me.aronth.minetechplus.core.helpers;

import static me.aronth.minetechplus.lib.Constants.NBT_IDEA;
import static me.aronth.minetechplus.lib.Constants.NBT_INVENTORY_SIZE;

import java.util.Random;

import me.aronth.minetechplus.core.ItemHandler;
import me.aronth.minetechplus.core.Reference;
import me.aronth.minetechplus.ideas.Idea;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class IdeaHandler implements ICraftingHandler{
	
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
		
		int chance = 68;
		
		if(Reference.DEBUG)
		    chance = 3;
		
		if(rand.nextInt(chance) == 1){ // Thanks to Zorn_Taov for helping fixing phantom items
			
			// Make the idea !!
			ItemStack idea = new ItemStack(ItemHandler.idea, 1);
			
			// Just while making the idea popper
			if(Reference.DEBUG)System.out.println("You hit the random chance");
			
			// get the whole crafting grid
			ItemStack[] inv = new ItemStack[crafting.getSizeInventory()];
			for(int i = 0; i < inv.length; i++)
				inv[i] = crafting.getStackInSlot(i);
			
			// get an idea from the items in the grid, if no idea is available, return nothing
			
			
			Idea thought = Idea.getIdeaFromGrid(inv);
			
			if(thought == null){
			    if(Reference.DEBUG)System.out.println("No idea found witht these materials");
			    return;
			}
			
			if(IdeaHelper.hasPlayerUnlockedIdea(thought.getIdeaId(), player)){
			    if(Reference.DEBUG) System.out.println("Already had idea");
			    return;
			}
			
			IdeaHelper.unlockIdea(thought.getIdeaId(), player);
			
			// Save the crafting grid in the idea item
			NBTTagCompound compound = new NBTTagCompound();
			NBTTagList craftGrid = new NBTTagList();

	        for (int i = 0; i < inv.length; ++i){
	            if (inv[i] != null){
	                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	                nbttagcompound1.setByte("Slot", (byte)i);
	                inv[i].writeToNBT(nbttagcompound1);
	                craftGrid.appendTag(nbttagcompound1);
	            }
	        }

	        // Save the information too the compound
	        compound.setTag("Items", craftGrid);
	        compound.setInteger(NBT_IDEA, thought.getIdeaId());
	        compound.setInteger(NBT_INVENTORY_SIZE, crafting.getSizeInventory());
			
	        NBTTagList result = new NBTTagList();
	        
	        if (item != null){
                NBTTagCompound comp = new NBTTagCompound();
                item.writeToNBT(comp);
                result.appendTag(comp);
            }
	        
	        // Save the resault tag too the compound
	        compound.setTag("Result", result);
	        
	        // Lets not forget too save the tag compound to the item stack (again)
	        idea.setTagCompound(compound);
	        
	        if(!world.isRemote){
	        	// spawn the item in world
	        	EntityItem entItem = new EntityItem(world, player.posX, player.posY, player.posZ, idea);
	        	entItem.delayBeforeCanPickup = 5;
	        	world.spawnEntityInWorld(entItem);
	        	
	        	// And finally let the player know of the idea
				player.sendChatToPlayer(LanguageRegistry.instance().getStringLocalization("chat.minetech.gotIdea"));
	        }
		}
	}

}
