package me.aronth.minetechplus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerBookOfWondering extends Container {

	private EntityPlayer me;
	
	public ContainerBookOfWondering(EntityPlayer player) {
		me = player;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(me.inventory, j + i * 9 + 9,
						8 + j * 18, 154 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(me.inventory, i, 8 + i * 18, 212));
		}
	}
	
	public EntityPlayer getPlayer(){
		return me;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
