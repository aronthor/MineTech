package me.aronth.minetechplus.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class ContainerIdea extends Container{

	private int x, y, z;
	private World w;
	private EntityPlayer player;
	//private ItemStack[] inv;
	
	public ContainerIdea(EntityPlayer me, World world, int x, int y, int z){
		player = me;
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = world;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
