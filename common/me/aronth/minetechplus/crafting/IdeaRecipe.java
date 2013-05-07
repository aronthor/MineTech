package me.aronth.minetechplus.crafting;

import net.minecraft.item.ItemStack;

public class IdeaRecipe {
    
    private ItemStack[] recipe;
    private ItemStack resault;
    
    public IdeaRecipe(ItemStack[] items, ItemStack crafting){
        recipe = items;
        resault = crafting;
    }
    
    public boolean doMatch(IdeaRecipe recipe){
        return doMatch(recipe.getRecipe());
    }
    
    public boolean doMatch(ItemStack[] check){
        
        for(int i = 0; i < check.length; i++){
            if(!ItemStack.areItemStacksEqual(check[i], recipe[i]))
                return false;
        }
        
        return true;
    }
    
    public ItemStack[] getRecipe(){
        return recipe;
    }
    
    public ItemStack getResault(){
        return resault;
    }
    
}
