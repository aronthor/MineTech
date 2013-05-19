package me.aronth.minetechplus.blocks.tileentitys;

import me.aronth.minetechplus.inventory.InventoryFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileDualFurnace extends TileEntity{
    
    public InventoryFurnace furnaceInv = new InventoryFurnace(2, 1);
    public int facing;
    public boolean isLit = false;
    public boolean wasLit = false;
    
    // Store timing for the lanes
    public int[] cookDone = new int[]{0, 0};
    public int itemBurnTime = 200;
    
    public int fuelLeft = 0;
    public int lastFuelAmt = 0;
    
    public TileDualFurnace(){
        
    }
    
    public void requestUpdate(){
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
    
    public void updateEntity(){
        isLit = fuelLeft > 0;
        
        if(wasLit != isLit){
            wasLit = isLit;
            requestUpdate();
        }
        
        if(hasWork()){
            if(fuelLeft == 0)
                consumeFuel();
            else{
                //if(!worldObj.isRemote){
                    if(isLit){
                        updateLane(0);
                        updateLane(1);
                    }
                //}

                --fuelLeft;
            }
        }
    }
    
    public void consumeFuel(){
        /*if(furnaceInv.getStackInSlot(2) == null)
            return;
        if(!TileEntityFurnace.isItemFuel(furnaceInv.getStackInSlot(2)))
            return;
        lastFuelAmt = TileEntityFurnace.getItemBurnTime(furnaceInv.getStackInSlot(2));
        fuelLeft += lastFuelAmt;
        furnaceInv.setInventorySlotContents(2, furnaceInv.decrStackSize(2, furnaceInv.getStackInSlot(2).stackSize-1));*/
        if (fuelLeft == 0){
            if (furnaceInv.getStackInSlot(2) != null){
                --furnaceInv.getStackInSlot(2).stackSize;
                lastFuelAmt = TileEntityFurnace.getItemBurnTime(furnaceInv.getStackInSlot(2));
                fuelLeft += lastFuelAmt;
                if (furnaceInv.getStackInSlot(2).stackSize == 0)
                    furnaceInv.setInventorySlotContents(2, (furnaceInv.getStackInSlot(2).getItem().hasContainerItem() ? 
                            furnaceInv.getStackInSlot(2).getItem().getContainerItemStack(furnaceInv.getStackInSlot(2)):null));
            }
        }
    }
    
    public boolean hasWork(){
        if(fuelLeft > 0)
            return true;
        if(furnaceInv.getStackInSlot(2) == null || !TileEntityFurnace.isItemFuel(furnaceInv.getStackInSlot(2)))
            return false;
        if(furnaceInv.getStackInSlot(0) != null && FurnaceRecipes.smelting().getSmeltingResult(furnaceInv.getStackInSlot(0)) != null)
            return true;
        if(furnaceInv.getStackInSlot(1) != null && FurnaceRecipes.smelting().getSmeltingResult(furnaceInv.getStackInSlot(1)) != null)
            return true;
        return false;
    }
    
    public void updateLane(int lane){
        /*if(cookDone[lane] == itemBurnTime){
            if(canSmelt(lane)){
                cookDone[lane] = 0;
                doSmelt(lane);
            }
        }else{
            cookDone[lane]++;
        }*/
        if(canSmelt(lane) && isLit){
            if(cookDone[lane] == itemBurnTime){
                cookDone[lane] = 0;
                doSmelt(lane);
            }else{
                ++cookDone[lane];
            }
        }
        //Help.log("Lane: "+lane + ", Done: "+cookDone[lane]);
    }
    
    public void doSmelt(int lane){
        if (canSmelt(lane)){
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceInv.getStackInSlot(lane));

            if (furnaceInv.getStackInSlot(3+lane) == null)
                furnaceInv.setInventorySlotContents(3+lane, itemstack.copy());
            else if (furnaceInv.getStackInSlot(3+lane).isItemEqual(itemstack))
                furnaceInv.getStackInSlot(3+lane).stackSize += itemstack.stackSize;

            --furnaceInv.getStackInSlot(lane).stackSize;

            if (furnaceInv.getStackInSlot(lane).stackSize <= 0)
                furnaceInv.setInventorySlotContents(lane, null);
        }
    }
    
    public int getCookLeftScaled(int lane, int scale){
        //Help.log("Lane: "+lane + " - Done: "+cookDone[lane]);
        return cookDone[lane] * scale / itemBurnTime;
    }
    
    private boolean canSmelt(int lane){
        if (furnaceInv.getStackInSlot(lane) == null){
            return false;
        }else{
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(furnaceInv.getStackInSlot(lane));
            if (itemstack == null) return false;
            if (furnaceInv.getStackInSlot(lane+3) == null) return true;
            if (!furnaceInv.getStackInSlot(lane+3).isItemEqual(itemstack)) return false;
            int result = furnaceInv.getStackInSlot(lane+3).stackSize + itemstack.stackSize;
            return (result <= furnaceInv.getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }
    
    public int getFuelLeftScaled(int scale){
        if(lastFuelAmt == 0)
            lastFuelAmt = 200;
        return fuelLeft*scale / lastFuelAmt;
    }
    
    public void readFromNBT(NBTTagCompound data){
        super.readFromNBT(data);
        furnaceInv.loadMatrix(data.getCompoundTag("furnaceInv"));
        facing = (data.getInteger("face"));
        isLit = data.getBoolean("lit");
        fuelLeft = data.getInteger("fuelLeft");
        lastFuelAmt = data.getInteger("lastFuelAmt");
        cookDone[0] = data.getInteger("cookDone0");
        cookDone[1] = data.getInteger("cookDone1");
    }

    public void writeToNBT(NBTTagCompound data){
        super.writeToNBT(data);
        data.setCompoundTag("furnaceInv", furnaceInv.saveMatrix());
        data.setInteger("face", facing);
        data.setBoolean("lit", isLit);
        data.setInteger("fuelLeft", fuelLeft);
        data.setInteger("lastFuelAmt", lastFuelAmt);
        data.setInteger("cookDone0", cookDone[0]);
        data.setInteger("cookDone1", cookDone[1]);
    }
    
    @Override
    public Packet getDescriptionPacket(){
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, tag);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet){
        NBTTagCompound tag = packet.customParam1;
        this.readFromNBT(tag);
    }
}
