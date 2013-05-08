package me.aronth.minetechplus.blocks.tileentitys;

import me.aronth.minetechplus.crafting.CraftingMatrix;
import me.aronth.minetechplus.crafting.ICraftMaster;
import me.aronth.minetechplus.inventory.InventoryCraftingSilent;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileCraftingTable extends TileEntity implements ICraftMaster {

    public CraftingMatrix craftMatrix = new CraftingMatrix(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();

    public TileCraftingTable(){
        
    }
    
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
        if(nbt.hasKey("matrix"))
            craftMatrix.loadMatrix(nbt.getCompoundTag("matrix"));
    }

    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        nbt.setCompoundTag("matrix", craftMatrix.saveMatrix());
    }
    
    @Override
    public void onMatrixChange() {
        InventoryCrafting crafting = new InventoryCraftingSilent(null, 3, 3);
        for(int i = 0; i < 9; i++){
            crafting.setInventorySlotContents(i, craftMatrix.getStackInSlot(i));
        }
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(crafting, this.worldObj));
    }

    @Override
    public void onChange() {
        onMatrixChange();
    }

    public void onCrafted() {
        for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = this.craftMatrix.getStackInSlot(i);

            if (itemstack1 != null)
            {
                this.craftMatrix.decrStackSize(i, 1);

                if (itemstack1.getItem().hasContainerItem())
                {
                    ItemStack itemstack2 = itemstack1.getItem().getContainerItemStack(itemstack1);

                    if (itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage())
                    {
                        itemstack2 = null;
                    }

                    if (itemstack2 != null)
                    {
                        if (this.craftMatrix.getStackInSlot(i) == null)
                        {
                            this.craftMatrix.setInventorySlotContents(i, itemstack2);
                        }
                    }
                }
            }
        }
    }

}
