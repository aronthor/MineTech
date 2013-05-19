package me.aronth.minetechplus.crafting;

import java.util.ArrayList;

import me.aronth.minetechplus.core.BlockHandler;
import me.aronth.minetechplus.core.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesIdeaBuilder {
    
    private ArrayList<IdeaRecipe> recipeList = new ArrayList<IdeaRecipe>();
    
    public RecipesIdeaBuilder(){
        addRecipe(new IdeaRecipe(new ItemStack[]{ // Dual Furnace
                new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron), 
                new ItemStack(Block.furnaceIdle), new ItemStack(Item.redstone), new ItemStack(Block.furnaceIdle),
                new ItemStack(Block.cobblestone), new ItemStack(Block.cobblestone), new ItemStack(Block.cobblestone)
        }, new ItemStack(BlockHandler.blockIdeaBlocks, 1, 0)));
        addRecipe(new IdeaRecipe(new ItemStack[]{ // Super Container
                new ItemStack(Block.planks), new ItemStack(Block.hopperBlock), new ItemStack(Block.planks),
                new ItemStack(Block.chest), null, new ItemStack(Block.chest),
                new ItemStack(Block.planks), new ItemStack(Block.planks), new ItemStack(Block.planks)
        }, new ItemStack(BlockHandler.blockIdeaBlocks, 1, 1)));
        addRecipe(new IdeaRecipe(new ItemStack[]{
                new ItemStack(Item.stick), null, null,
                null, new ItemStack(Item.dyePowder, 1, 0), null,
                null, null, null
        }, new ItemStack(ItemHandler.pencil)));
        addRecipe(new IdeaRecipe(new ItemStack[]{
                new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Block.torchWood), new ItemStack(Item.dyePowder, 1, 0),
                new ItemStack(Item.dyePowder, 1, 11), new ItemStack(Item.helmetIron), new ItemStack(Item.dyePowder, 1, 11),
                new ItemStack(Item.silk), null, new ItemStack(Item.silk)
        }, new ItemStack(Item.helmetGold)));
    }
    
    public void addRecipe(IdeaRecipe recipe){
        recipeList.add(recipe);
    }
    
    public IdeaRecipe getMatch(ItemStack[] stacks){
        
        for(IdeaRecipe recipe : recipeList){
            if(recipe.doMatch(stacks))
                return recipe;
        }
        
        return null;
    }

    public ItemStack findMatch(CraftingMatrix matrix){
        for(IdeaRecipe recipe : recipeList){
            if(doRecipesMatch(recipe, matrix)){
                return recipe.getResault().copy();
            }
        }
        
        return null;
    }
    
    private boolean doRecipesMatch(IdeaRecipe recipe, CraftingMatrix matrix) {
        if(recipe.getRecipe().length != matrix.getSizeInventory())
            return false;
        for(int i = 0; i < matrix.getSizeInventory(); i++){
            ItemStack r = recipe.getRecipe()[i];
            ItemStack g = matrix.getStackInSlot(i);
            if(r == null && g != null)
                return false;
            if(r != null && g == null)
                return false;
            if(r == null && g == null)
                continue;
            if(!stacksMatch(r, g))
                return false;
        }
        return true;
    }

    private boolean stacksMatch(ItemStack r, ItemStack g){
        if(r.isItemEqual(g))
            return true;
        return false;
    }
    
    public ItemStack[] getRecipeStacks(int recipe) {
        return recipeList.get(recipe).getRecipe();
    }

    public IdeaRecipe getRecipe(int ideaRecipe) {
        return recipeList.get(ideaRecipe);
    }
    
}
