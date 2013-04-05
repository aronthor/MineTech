package me.aronth.minetechplus.items;

import java.util.List;

import me.aronth.minetechplus.core.MineTechPlus;
import me.aronth.minetechplus.core.Reference;
import me.aronth.minetechplus.core.helpers.NBTTagHelper;
import me.aronth.minetechplus.ideas.IdeaManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemIdea extends MTItem {

	public ItemIdea(int itemId) {
		super(itemId);
		this.setUnlocalizedName("Idea");
		this.setCreativeTab(MineTechPlus.tab);
		this.setMaxStackSize(1);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack stack, EntityPlayer me, List list, boolean par4) {
		// Thanks to michaelwm for helping me find the italic and color codes !! and mDiyo for the code michaelwm referenced too
		try{
			NBTTagCompound comp = stack.stackTagCompound;
			
			ItemStack resultItem;
			
			// Gets the resulting item from the crafting and add it to the list
			if(comp.hasKey("Result")){
				NBTTagCompound res = (NBTTagCompound) comp.getTagList("Result").tagAt(0);
				resultItem = ItemStack.loadItemStackFromNBT(res);
				list.add("\u00a7oCrafted: \u00a78"+resultItem.getDisplayName());
			}
			
			/*for(int i = 0; i < NBTTagHelper.getInt(stack, "craftingSlots"); i++){
				if(!list.contains(NBTTagHelper.getString(stack, "matrix"+i)) && NBTTagHelper.getString(stack, "matrix"+i) != "-"){
					list.add("\u00a78"+NBTTagHelper.getString(stack, "matrix"+i));
				}
			}*/
			
			if(NBTTagHelper.hasTag(stack, "thought") && NBTTagHelper.getInt(stack, "thought") >= 0 && Reference.DEBUG){
				list.add(IdeaManager.instance.ideas.get(NBTTagHelper.getInt(stack, "thought")).getName());
			}
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
	}


}
