package me.aronth.minetechplus.ideas;

import static me.aronth.minetechplus.lib.Constants.NBT_IDEAS_UNLOCKED;
import static me.aronth.minetechplus.lib.Constants.NBT_IDEA_UNLOCKED;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class IdeaManager {

	public ArrayList<IIdea> ideas = new ArrayList<IIdea>();
	
	// THE INSTANCE !!! AAAAA !!!
	public static IdeaManager instance = new IdeaManager();
	
	public IdeaManager(){
		
		addIdea(new IdeaDualFurnace());
		System.out.println(ideas.size() + " ideas loaded!");
	}
	
	public void addIdea(IIdea idea){
		ideas.add(idea);
	}
	
	public int getIdeaWithResource(ItemStack stack){
		for(int i = 0; i < ideas.size(); i++){
			if(ideas.get(i).resourcesUsed(stack)){
				return i;
			}
		}
		
		return -1;
	}
	
	public boolean hasPlayerUnlockIdea(EntityPlayer me, int idea){
		// Thanks too excalibr23 for helping me with this information
		/*NBTTagCompound tag =  me.getEntityData();
		if(tag.hasKey(Reference.MOD_ID+":ideas")){
			NBTTagCompound ideas = tag.getCompoundTag(Reference.MOD_ID+":ideas");
			if(ideas.hasKey("ideasGotten")){
				int gotten[] = ideas.getIntArray("ideasGotten");
				for(int i = 0; i < gotten.length; i++){
					if(gotten[i] == idea)
						return true;
				}
			}
		}
		return false;*/
		NBTTagCompound tag = me.getEntityData();
		if(tag.hasKey(NBT_IDEAS_UNLOCKED)){
			if(tag.hasKey(NBT_IDEA_UNLOCKED+idea))
				return true;
		}
		return false;
	}
	
	public IIdea getIdea(int i){
		return this.ideas.get(i);
	}
	
	public void unlockIdea(EntityPlayer me, int idea){
		/*NBTTagCompound tag =  me.getEntityData();
		if(tag.hasKey(Reference.MOD_ID+":ideas")){
			NBTTagCompound ideas = tag.getCompoundTag(Reference.MOD_ID+":ideas");
			if(ideas.hasKey("ideasGotten")){
				int gotten[] = ideas.getIntArray("ideasGotten");
				gotten[gotten.length] = idea;
				ideas.setIntArray("ideasGotten", gotten);
				tag.setCompoundTag(Reference.MOD_ID+":ideas",ideas);
			}
		}*/
		NBTTagCompound tag = me.getEntityData();
		if(tag.hasKey(NBT_IDEAS_UNLOCKED)){
			if(!tag.hasKey(NBT_IDEA_UNLOCKED)){
				tag.setBoolean(NBT_IDEA_UNLOCKED+idea, true);
			}
		}
	}
	
	public int getIdeaFromGrid(ItemStack[] inv){
		int idea = -1;
		
		for(int i = 0; i < inv.length; i++){
			if(inv[i] != null){
				idea = getIdeaWithResource(inv[i]);
				if(idea >= 0)
					break;
			}
		}
		
		return idea;
	}
	
}
