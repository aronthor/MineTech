package me.aronth.minetechplus.blocks.tileentitys;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.core.helpers.IdeaHelper;
import me.aronth.minetechplus.items.ItemIdea;
import me.aronth.minetechplus.lib.Constants;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileWorkstation extends TileEntity implements IInventory{

	private ItemStack[] stack = new ItemStack[1];
	private int bookcases = 0;
	public int cooldown = 0;
	private String clicker;
	public int waitTime = 60*20;
	
	/*private int[] side = new int[6];
	private boolean open = false;
	
	public void openForModule(){
	    open = true;
	}
	
	public boolean addModule(int s){
	    if(side[s] != null)
	        return false;
	    
	    return true;
	}*/
	
	public boolean isRefining(EntityPlayer outsider){
	    if(clicker == null)
	        return false;
	    if(outsider.username != clicker && cooldown > 0)
	        return true;
	    return false;
	}
	
	@Override
	public void updateEntity(){
	    if(cooldown > 0){
	        --cooldown;
	        if(cooldown == 0){
	            this.refineIdea();
	        }
	        if(this.worldObj.getClosestPlayer((double)((float)this.xCoord + 0.5F), (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord + 0.5F), 10.0D) == null){
	            cooldown = 0;
	        }
	        System.out.println("Left:"+cooldown);
	    }
	}
	
	@Override
	public int getSizeInventory() {
		return stack.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return stack[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {

        if (stack[i] != null)
        {
            if (stack[i].stackSize <= j)
            {
                ItemStack itemstack = stack[i];
                stack[i] = null;
                onInventoryChanged();
                return itemstack;
            }
            ItemStack itemstack1 = stack[i].splitStack(j);
            if (stack[i].stackSize == 0)
            {
            	stack[i] = null;
            }
            onInventoryChanged();
            return itemstack1;
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return stack[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		stack[i] = itemstack;
	}

	@Override
	public String getInvName() {
		return "Workstation";
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		if(i == 0 && itemstack.getItem() instanceof ItemIdea)
			return true;
		return false;
	}
	
	public int findBookcases(){
	    bookcases = 0;
	    for(int y = 0; y < 2;y++){
    	    for(int x = 0; x < 5; x++){
    	        for(int z = 0; z < 5; z++){
    	            if(worldObj.getBlockId(xCoord+x-2, yCoord+y, zCoord+z-2) == Block.bookShelf.blockID)
    	                this.bookcases++;
    	        }
    	    }
	    }
	    //if(Reference.DEBUG)System.out.println("Found " + this.bookcases + " around");
	    return bookcases;
	}
	
	public boolean hasLevels(EntityPlayer me){
	    int levels = IdeaHelper.getRequiredLevels(findBookcases(), me);
	    if(me.experienceLevel >= levels)
	        return true;
	    return false;
	}

    public void refineIdea() {
        //if(hasLevels(player)){
            if(this.stack[0] != null){
                if(stack[0].getItem() instanceof ItemIdea){
                    NBTTagCompound tags = stack[0].stackTagCompound;
                    if(tags.hasKey(Constants.NBT_REFINED)){
                        if(tags.getInteger(Constants.NBT_REFINED) < 3){
                            tags.setInteger(Constants.NBT_REFINED, tags.getInteger(Constants.NBT_REFINED)+1);
                            //stack[1] = stack[0];
                            //stack[0] = null;
                            //player.experienceLevel -= IdeaHelper.getRequiredLevels(findBookcases(), player);
                        }
                    }else{
                        tags.setInteger(Constants.NBT_REFINED, 1);
                    }
                }
            }
        //}
    }
    
    public void readFromNBT(NBTTagCompound data){
        super.readFromNBT(data);
        if(data.hasKey("slot0"))
            stack[0] = getStackFromNBT(data.getCompoundTag("slot0"));
        if(data.hasKey("cooldown"))
            cooldown = data.getInteger("cooldown");
        if(data.hasKey("clicker") && MineTechPlus.instance.playerTracker.isPlayerOnline(data.getString("clicker")))
            clicker = data.getString("clicker");
    }
    
    public void writeToNBT(NBTTagCompound data){
        super.writeToNBT(data);
        if(this.stack[0] != null)
            data.setCompoundTag("slot0", getStackAsNBT(stack[0]));
        data.setInteger("cooldown", cooldown);
        if(clicker != null)
            data.setString("clicker", clicker);
    }
    
    public NBTTagCompound getStackAsNBT(ItemStack stack){
        NBTTagCompound comp = new NBTTagCompound();
        comp.setInteger("id", stack.itemID);
        comp.setInteger("size", stack.stackSize);
        comp.setInteger("damage", stack.getItemDamage());
        if(stack.hasTagCompound())
            comp.setCompoundTag("compound", stack.stackTagCompound);
        return comp;
    }
    
    public ItemStack getStackFromNBT(NBTTagCompound comp){
        ItemStack stack = new ItemStack(comp.getInteger("id"), comp.getInteger("damage"), comp.getInteger("size"));
        if(comp.hasKey("compound"))
            stack.stackTagCompound = comp.getCompoundTag("compound");
        return stack;
    }

    public void countdownToRefine(EntityPlayer me) {
        cooldown = waitTime;
        clicker = me.username;
    }
	
}
