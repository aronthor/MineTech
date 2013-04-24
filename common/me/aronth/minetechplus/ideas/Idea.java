package me.aronth.minetechplus.ideas;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Idea {
    
    // The whole long idea list !!
    public static final Idea[] ideaList = new Idea[256];
    
    // All em' ideas initialized
    public static final Idea dualFurnace = new IdeaDualFurnace(1).setName("dualFurnace");
    public static final Idea superContainer = new IdeaSuperContainer(2).setName("superContainer");
    public static final Idea pencil = new IdeaPencil(3).setName("pencil");
    public static final Idea minersHelmet = new IdeaMinersHelmet(4).setName("minersHelmet");
    
    // Simple fields for idea
    public int ideaIndex;
    public String ideaName;
    private ArrayList<ItemStack> materials = new ArrayList<ItemStack>();
    private Object contentOfIdea;
    
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
    
    public void setContent(Object obj){
        if(obj instanceof Block)
            this.contentOfIdea = obj;
        if(obj instanceof Item)
            this.contentOfIdea = obj;
        if(obj instanceof ItemStack)
            this.contentOfIdea = obj;
        System.out.println("Invalid Content");
    }
    
    public Object getContent(){
        return contentOfIdea;
    }
    
    public boolean usesMaterial(ItemStack stack){
        for(ItemStack material : materials){
            if(areSameMaterials(stack, material))
                return true;
        }
        return false;
    }
    
    public boolean areSameMaterials(ItemStack stack1, ItemStack stack2){
        if(stack1 == null || stack2 == null)
            return false;
        
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
    
    public String getUnlocalizedName(){
        return "idea."+ideaName+".name";
    }
    
    public String getName(){
        return LanguageRegistry.instance().getStringLocalization("idea."+ideaName+".name");
        //return ideaName;
    }
    
    public int getIdeaId(){
        return ideaIndex;
    }
    
    public String getDescription() {
        return LanguageRegistry.instance().getStringLocalization("idea."+ideaName+".descr");
        //return "-";
    }
    
    // Method for calling all ideas, just for debugging
    public static void listIdea(){
        for(Idea idea : ideaList){
            System.out.println(idea.getName());
        }
    }
}