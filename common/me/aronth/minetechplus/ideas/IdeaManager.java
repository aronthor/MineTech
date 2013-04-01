package me.aronth.minetechplus.ideas;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class IdeaManager {

	public ArrayList<IIdea> ideas = new ArrayList<IIdea>();
	
	public IdeaManager(){
		
		addIdea(new IdeaDualFurnace());
		System.out.println(ideas.size() + " ideas loaded!");
	}
	
	public void addIdea(IIdea idea){
		ideas.add(idea);
	}
	
	public int getIdeaWithResource(ItemStack stack){
		int id = 0;
		
		for(int i = 0; i < ideas.size(); i++){
			if(ideas.get(i).resourcesUsed(stack)){
				id = i;
				break;
			}
		}
		
		return id;
	}
	
}
