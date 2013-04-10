package me.aronth.minetechplus.inventory;

import me.aronth.minetechplus.ideas.IIdea;
import me.aronth.minetechplus.ideas.IdeaManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import static me.aronth.minetechplus.lib.Constants.*;

public class ContainerIdea extends Container{

	//private IInventory idea;
	private String ideaName, ideaDescr;
	
	public ContainerIdea(EntityPlayer me){
		ItemStack ideaStack = null;
		
		/*for(ItemStack stack : me.inventory.mainInventory){
			if(stack != null && stack.getItem() instanceof ItemIdea){
				if(stack.stackTagCompound.hasKey("open")){
					stack.stackTagCompound.removeTag("open");
					ideaStack = stack;
					//idea = ItemIdea.getInventory(me, stack);
					if(Reference.DEBUG)System.out.println("Found Inventory");
				}
			}
		}*/
		
		ideaStack = me.getCurrentEquippedItem();
		
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
        
        int index = 0;
		for(int a = 0; a < 3; a++){
			for(int b = 0; b < 3; b++){
				addSlotToContainer(new SlotDumb(8 + b * 18, 17 + a * 18, stacks[index]));
				++index;
			}
		}
		
		IIdea thought = IdeaManager.instance.getIdea(ideaStack.stackTagCompound.getInteger(NBT_IDEA));
		
		this.ideaName = thought.getName();
		this.ideaDescr = thought.getDescription();
		
		NBTTagCompound res = (NBTTagCompound) ideaStack.stackTagCompound.getTagList("Result").tagAt(0);
		ItemStack resultItem = ItemStack.loadItemStackFromNBT(res);
		
		addSlotToContainer(new SlotDumb(86, 17, resultItem));
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(me.inventory, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(me.inventory, i, 8 + i * 18, 142));
		}
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
