package me.aronth.minetechplus.blocks.tileentitys;

import java.util.List;

import me.aronth.minetechplus.inventory.InventoryBig;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileSuperContainer extends TileEntity{
    
    public InventoryBig inv = new InventoryBig(54);
    
    private int itemGrab = 0;
    
    public int facing;
    
    public int playersOpen = 0;
    
    public float lidAngle = 0F;
    private float lidSpeed = 0.2F;
    
    public void openChest(){
        ++playersOpen;
        //this.worldObj.playSoundEffect(xCoord, (double)this.yCoord + 0.5D, yCoord, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
    }
    
    public void closeChest(){
        --playersOpen;
        if(playersOpen < 0)
            playersOpen = 0;
        //this.worldObj.playSoundEffect(xCoord, (double)this.yCoord + 0.5D, yCoord, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
    }
    
    public void updateLid(){
        if(playersOpen > 0){
            if(lidAngle < 1F)
                lidAngle += lidSpeed;
        }else{
            if(lidAngle > 0F)
                lidAngle -= lidSpeed;
        }
        if(lidAngle > 1F)
            lidAngle = 1F;
        if(lidAngle < 0F)
            lidAngle = 0F;
    }
    
    public boolean spaceInInventory(){
        for(int i = 0; i < inv.getSizeInventory(); i++){
            if(inv.getStackInSlot(i) == null)
                return true;
        }
        return false;
    }
    
    public void putInEmptySlot(ItemStack stack){
        for(int i = 0; i < inv.getSizeInventory(); i++){
            if(inv.getStackInSlot(i) != null && inv.getStackInSlot(i).isItemEqual(stack)){
                ItemStack itemStack = inv.getStackInSlot(i);
                if(itemStack.stackSize < itemStack.getMaxStackSize()){
                    if(itemStack.stackSize + stack.stackSize <= itemStack.getMaxStackSize()){
                        ItemStack newStack = new ItemStack(itemStack.itemID, itemStack.stackSize+stack.stackSize, itemStack.getItemDamage());
                        if(itemStack.hasTagCompound())
                            newStack.stackTagCompound = itemStack.stackTagCompound;
                        inv.setInventorySlotContents(i, newStack);
                        return;
                    }
                }
            }
            if(inv.getStackInSlot(i) == null){
                inv.setInventorySlotContents(i, stack);
                return;
            }
        }
    }
    
    public void grabItems(){
        if(!spaceInInventory())
            return;
        
        @SuppressWarnings("rawtypes")
        List eList = this.worldObj.getEntitiesWithinAABB(net.minecraft.entity.item.EntityItem.class, 
                AxisAlignedBB.getBoundingBox(xCoord - 2, yCoord, zCoord - 2, xCoord + 2, yCoord + 1.5, zCoord + 2));
        
        if(!eList.isEmpty()){
            if(eList.get(0) instanceof EntityItem){
                EntityItem item = ((EntityItem)eList.get(0));
                putInEmptySlot(item.getEntityItem().copy());
                worldObj.spawnParticle("smoke", item.lastTickPosX, item.lastTickPosY, item.lastTickPosZ, 0, 0.1, 0);
                item.setDead();
            }
        }
        
        itemGrab = 0;
    }
    
    @Override
    public void updateEntity(){
        itemGrab++;
        if(itemGrab == 20)
            grabItems();
        updateLid();
    }
    
    public void updateChest(){
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, worldObj.getBlockId(xCoord, yCoord, zCoord), 1, playersOpen);
    }

    public boolean receiveClientEvent(int eventId, int param){
        if (eventId == 1){
            playersOpen = param;
            return true;
        }else{
            return super.receiveClientEvent(eventId, param);
        }
    }
    
    public void readFromNBT(NBTTagCompound data){
        super.readFromNBT(data);
        facing = (data.getInteger("face"));
        if(data.hasKey("inv"))
            inv.loadInventory(data.getCompoundTag("inv"));
    }

    public void writeToNBT(NBTTagCompound data){
        super.writeToNBT(data);
        data.setInteger("face", facing);
        data.setCompoundTag("inv", inv.saveInventory());
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
