package me.aronth.minetechplus.inventory;

import static me.aronth.minetechplus.lib.Constants.NBT_IDEA;
import me.aronth.minetechplus.ideas.Idea;
import me.aronth.minetechplus.lib.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ContainerIdea extends Container{

	//private IInventory idea;
	private String ideaName, ideaDescr;
	
	public ContainerIdea(EntityPlayer me){
		ItemStack ideaStack = me.getCurrentEquippedItem();
		
		ItemStack[] stacks;
		
		NBTTagList nbttaglist = ideaStack.stackTagCompound.getTagList("Items");
        stacks = new ItemStack[ideaStack.stackTagCompound.getInteger("invSize")];

        for (int i = 0; i < nbttaglist.tagCount(); ++i){
            NBTTagCompound comp2 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = comp2.getByte("Slot") & 255;
            if (j >= 0 && j < stacks.length){
                stacks[j] = ItemStack.loadItemStackFromNBT(comp2);
            }
        }
        
        if(ideaStack.stackTagCompound.hasKey(Constants.NBT_INVENTORY_SIZE)){
            int invSize = ideaStack.stackTagCompound.getInteger(Constants.NBT_INVENTORY_SIZE);
            if(invSize == 9){
                int index = 0;
                for(int a = 0; a < 3; a++){
                    for(int b = 0; b < 3; b++){
                        addSlotToContainer(new SlotDumb(8 + b * 18, 17 + a * 18, stacks[index]));
                        ++index;
                    }
                }
            }else if(invSize == 4){
                int index = 0;
                for(int a = 0; a < 2; a++){
                    for(int b = 0; b < 2; b++){
                        addSlotToContainer(new SlotDumb(8 + b * 18, 17 + a * 18, stacks[index]));
                        ++index;
                    }
                }
            }
        }else{
            System.out.println("Errmmm.. Invalid idea");
        }
		
		Idea thought = Idea.getIdeaById(ideaStack.stackTagCompound.getInteger(NBT_IDEA));
		
		this.ideaName = thought.getName();
		this.ideaDescr = thought.getDescription();
		
		NBTTagCompound res = (NBTTagCompound) ideaStack.stackTagCompound.getTagList("Result").tagAt(0);
		ItemStack resultItem = ItemStack.loadItemStackFromNBT(res);
		
		addSlotToContainer(new SlotDumb(86, 17, resultItem));
	}
	
	public String getIdeaName(){
		return this.ideaName;
	}
	
	public String getIdeaDescription(){
		return this.ideaDescr;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotPos) {
        return null;
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
