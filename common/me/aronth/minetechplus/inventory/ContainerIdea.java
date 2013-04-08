package me.aronth.minetechplus.inventory;

import me.aronth.minetechplus.items.ItemIdea;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerIdea extends Container{

	private int x, y, z;
	private World w;
	private EntityPlayer player;
	private IInventory idea;
	
	public ContainerIdea(EntityPlayer me, World world, int x, int y, int z){
		player = me;
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = world;
		for(ItemStack stack : me.inventory.mainInventory){
			if(stack.getItem() instanceof ItemIdea){
				if(stack.stackTagCompound.hasKey("open")){
					stack.stackTagCompound.removeTag("open");
					idea = ((ItemIdea)stack.getItem()).getInventory(me, stack);
				}
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
