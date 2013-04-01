package me.aronth.minetechplus.ideas;

import net.minecraft.item.ItemStack;

public interface IIdea {

	public String getName();
	public String getDescription();
	public boolean resourcesUsed(ItemStack obj);
	
}
