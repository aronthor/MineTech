package me.aronth.minetechplus.core.helpers;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerTracker implements IPlayerTracker{

    private HashMap<String, EntityPlayer> players = new HashMap<String, EntityPlayer>();
    
    @Override
    public void onPlayerLogin(EntityPlayer player) {
        players.put(player.username, player);
    }

    @Override
    public void onPlayerLogout(EntityPlayer player) {
        players.remove(player.username);
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player) {
        // Nothing
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player) {
        // Nothing
    }
    
    public EntityPlayer getPlayerByUsername(String username){
        if(players.containsKey(username))
            return players.get(username);
        else
            return null;
    }
    
    public boolean isPlayerOnline(String username){
        return players.containsKey(username);
    }

}
