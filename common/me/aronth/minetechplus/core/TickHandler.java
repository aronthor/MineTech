package me.aronth.minetechplus.core;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler{

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        /*for(TickType tickType : type){
            if(tickType == TickType.PLAYER){
                EntityPlayer me = (EntityPlayer)tickData[0];
                me.cloakUrl = "http://www.mccapes.com/GalleryImages6x/1cb3cec5800327dfd22a1e0beced2c0c.png";
                //me.
            }
        }*/
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {
        return Reference.MOD_ID;
    }

}
