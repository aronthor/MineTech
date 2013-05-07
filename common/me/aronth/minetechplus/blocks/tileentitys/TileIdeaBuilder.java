package me.aronth.minetechplus.blocks.tileentitys;

import me.aronth.minetechplus.crafting.CraftingMatrix;
import me.aronth.minetechplus.crafting.ICraftMaster;
import me.aronth.minetechplus.ideas.Idea;
import me.aronth.minetechplus.inventory.InventorySmall;
import me.aronth.minetechplus.items.ItemIdea;
import me.aronth.minetechplus.lib.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileIdeaBuilder extends TileEntity implements ICraftMaster{
    
    public IInventory idea = new InventorySmall(this, 1);
    public IInventory result = new InventoryCraftResult();
    public CraftingMatrix matrix = new CraftingMatrix(this, 3, 3);
    
    
    public TileIdeaBuilder(){
    }
    
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
        if(nbt.hasKey("idea"))
            idea.setInventorySlotContents(0, matrix.getStackFromNBT(nbt.getCompoundTag("idea")));
        if(nbt.hasKey("matrix"))
            matrix.loadMatrix(nbt.getCompoundTag("matrix"));
    }

    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        nbt.setCompoundTag("idea", matrix.getStackAsNBT(idea.getStackInSlot(0)));
        nbt.setCompoundTag("matrix", matrix.saveMatrix());
    }

    @Override
    public void onMatrixChange() {
        ItemStack ideaStack = idea.getStackInSlot(0);
        if(ideaStack == null)
            return;
        if(!(ideaStack.getItem() instanceof ItemIdea))
            return;
        if(!ideaStack.hasTagCompound())
            return;
        if(!ideaStack.stackTagCompound.hasKey(Constants.NBT_IDEA))
            return;
        if(!ideaStack.stackTagCompound.hasKey(Constants.NBT_REFINED))
            return;
        
        if(ideaStack.stackTagCompound.getInteger(Constants.NBT_REFINED) < 3)
            return;
        
        Idea i = Idea.getIdeaById(ideaStack.stackTagCompound.getInteger(Constants.NBT_IDEA));
        
        ItemStack ideaResult = Idea.recipeHandler.getRecipe(i.getRecipe()).getResault();
        ItemStack matrixResult = Idea.recipeHandler.findMatch(matrix);
        
        if(ideaResult == null || matrixResult == null)
            return;
        
        if(!matrixResult.isItemEqual(ideaResult))
            return;
        
        this.result.setInventorySlotContents(0, matrixResult);
    }

    public void onCrafted(EntityPlayer crafter, ItemStack pickupStack) {
        matrix.onCrafted(crafter, pickupStack);
    }

    @Override
    public void onChange() {
        this.onMatrixChange();
    }
}
