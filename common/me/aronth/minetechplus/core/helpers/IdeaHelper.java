package me.aronth.minetechplus.core.helpers;

import me.aronth.minetechplus.core.Reference;
import me.aronth.minetechplus.ideas.Idea;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class IdeaHelper {

    public static final String NBT_IDEA = "ideaUnlocked";
    
    public static void initPlayer(EntityPlayer player){
        if(!player.getEntityData().hasKey(Reference.MOD_ID)){
            player.getEntityData().setCompoundTag(Reference.MOD_ID, new NBTTagCompound());
        }
    }
    
    public static void unlockIdea(int id, EntityPlayer player){
        initPlayer(player);
        NBTTagCompound tag = getPlayerData(player);
        if(!tag.hasKey(NBT_IDEA+id))
            tag.setBoolean(NBT_IDEA+id, true);
    }
    
    public static boolean hasPlayerUnlockedIdea(int id, EntityPlayer player){
        initPlayer(player);
        NBTTagCompound tag = getPlayerData(player);
        if(tag.hasKey(NBT_IDEA+id))
            return true;
        return false;
    }
    
    public static NBTTagCompound getPlayerData(EntityPlayer player){
        initPlayer(player);
        return player.getEntityData().getCompoundTag(Reference.MOD_ID);
    }
    
    public static Idea getIdeaFromGrid(ItemStack[] grid){
        return Idea.getIdeaFromGrid(grid);
    }
    
}
