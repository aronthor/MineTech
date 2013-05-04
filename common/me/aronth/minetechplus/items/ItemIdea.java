package me.aronth.minetechplus.items;

import java.util.List;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.core.Reference;
import me.aronth.minetechplus.ideas.Idea;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemIdea extends MTItem {

	public ItemIdea(int itemId) {
		super(itemId);
		this.setUnlocalizedName("idea");
		this.setCreativeTab(MineTechPlus.tab);
		this.setMaxStackSize(1);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
	    if(!player.isSneaking() && world.isRemote)
	        player.openGui(MineTechPlus.instance, Reference.GUI_IDEA, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		return stack;
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
				list.add("\u00a7o" + LanguageRegistry.instance().getStringLocalization("idea.info.crafted") + "\u00a78" + resultItem.getDisplayName());
			}
			
			if(comp.hasKey("refined")){
			    if(comp.getInteger("refined") < 3)
			        list.add("\u00a7o" + LanguageRegistry.instance().getStringLocalization("idea.info.refined") + "\u00a78" + comp.getInteger("refined"));
			    else
			        list.add("\u00a78" + LanguageRegistry.instance().getStringLocalization("idea.info.refinedComplete"));
			}else{
			    comp.setInteger("refined", 0);
			    list.add("\u00a7o" + LanguageRegistry.instance().getStringLocalization("idea.info.refined") + "\u00a78 0");
			}
			
			if(stack.stackTagCompound.hasKey("idea")){
			    Idea idea = Idea.getIdeaById(stack.stackTagCompound.getInteger("idea"));
			    if(idea != null)
			        list.add(idea.getName());
			}
			
		}catch(NullPointerException e){
			//System.out.println(e.getMessage());
		    // Do nothing !! There seems that this idea stack does not have any data
		}
	}
}
