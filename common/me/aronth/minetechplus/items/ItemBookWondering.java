package me.aronth.minetechplus.items;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.core.Reference;
import me.aronth.minetechplus.core.helpers.NBTTagHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBookWondering extends MTItem{

	public ItemBookWondering(int id) {
		super(id);
		this.setUnlocalizedName("bookOfWondering");
		this.setMaxStackSize(1);
		this.setCreativeTab(MineTechPlus.tab);
	}
	
	// This is for opening the gui of the book
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		/*if(NBTTagHelper.hasTag(stack, "owner") && NBTTagHelper.getString(stack, "owner") == player.username){
			player.sendChatToPlayer("A Gui should open !!");
		}else{
			if(NBTTagHelper.hasTag(stack, "owner")){
				player.sendChatToPlayer("-- This is not your book !!");
				if(Reference.DEBUG)System.out.println(player.username + " tried too open " + NBTTagHelper.getString(stack, "owner") + " 's book");
			}else{
				NBTTagHelper.setString(stack, "owner", player.username);
			}
		}*/
		
		/*if(world.isRemote){
			System.out.println("Remote");
		}else{
			System.out.println("Not Remote");
		}*/
		
		/*player.getEntityData().setString("test","testtest");
		
		NBTTagCompound tags = player.getEntityData();
		if(tags.hasKey(NBT_IDEAS_UNLOCKED)){
			player.sendChatToPlayer("you have ideas !!");
		}else{
			tags.setBoolean(NBT_IDEAS_UNLOCKED, true);
		}*/
		
		if(!player.isSneaking()){
			player.openGui(MineTechPlus.instance, Reference.GUI_BOOKOFWONDERING, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		
        return stack;
    }

	// For setting the books owner !!
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		NBTTagHelper.setString(stack, "owner", player.username);
	}

	// For displaying the books name correctly
    public String getItemDisplayName(ItemStack stack){
    	String name = "Book of Wondering";
    	
    	if(NBTTagHelper.hasTag(stack, "owner")){
			name = NBTTagHelper.getString(stack, "owner") + "'s " + name;
		}
    	
        return name;
    }
    
    /*
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack stack, EntityPlayer me, List list, boolean par4) {
    	NBTTagCompound tag = me.getEntityData();
    	for(int i = 0; i < IdeaManager.instance.ideas.size(); i++){
    		if(tag.hasKey(NBT_IDEA_UNLOCKED+i))
    			list.add(IdeaManager.instance.getIdea(i).getName());
    	}
    	if(me.getEntityData().hasKey("test"))
    		list.add(me.getEntityData().getString("test"));
    }*/
	
}
