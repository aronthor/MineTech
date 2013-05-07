package me.aronth.minetechplus.core;

import java.util.EnumSet;

import me.aronth.minetechplus.lib.Reference;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler{

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        for(TickType tickType : type){
            if(tickType == TickType.PLAYER){
                //EntityPlayer me = (EntityPlayer)tickData[0];
                
            }
            if(tickType == TickType.RENDER){
                
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.PLAYER, TickType.RENDER);
    }

    @Override
    public String getLabel() {
        return Reference.MOD_ID;
    }

}
