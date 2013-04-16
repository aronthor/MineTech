package me.aronth.minetechplus.ideas;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class Idea {
    
    // The whole long idea list !!
    public static final Idea[] ideaList = new Idea[256];
    
    // All em' ideas initialized
    public static final Idea dualFurnace = new IdeaDualFurnace(1).setName("DualFurnace");
    
    // Simple fields for idea
    public int ideaIndex;
    public String ideaName;
    private ArrayList<ItemStack> materials = new ArrayList<ItemStack>();
    
    public Idea(int id){
        if(ideaList[id] != null){
            throw new RuntimeException("Cannot override existing idea:"+ideaList[id].getName());
        }else{
            ideaList[id] = this;
            ideaIndex = id;
        }
    }
    
    // Usefull methods
    
    public void addMaterial(ItemStack material){
        if(!materials.contains(material))
            materials.add(material);
    }
    
    public static Idea getIdeaFromGrid(ItemStack[] stacks){
        for(Idea ideas : ideaList){
            if(ideas != null)
                for(ItemStack stack : stacks)
                    if(ideas.usesMaterial(stack))
                        return ideas;
        }
        return null;
    }
    
    public boolean usesMaterial(ItemStack stack){
        for(ItemStack material : materials){
            if(areSameMaterials(stack, material))
                return true;
        }
        return false;
    }
    
    public boolean areSameMaterials(ItemStack stack1, ItemStack stack2){
        
        if(stack1.itemID == stack2.itemID && stack1.getItemDamage() == stack2.getItemDamage())
            return true;
        
        return false;
    }
    
    public static Idea getIdeaById(int id){
        return ideaList[id];
    }
    
    // Getters and Setters
    public Idea setName(String name){
        this.ideaName = name;
        return this;
    }
    
    public String getName(){
        return ideaName;
    }
    
    public int getIdeaId(){
        return ideaIndex;
    }
    
    public String getDescription() {
        return "-";
    }
    
    // Method for calling all ideas, just for debugging
    public static void listIdea(){
        for(Idea idea : ideaList){
            System.out.println(idea.getName());
        }
    }
}