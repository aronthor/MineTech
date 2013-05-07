package me.aronth.minetechplus.lib;

import cpw.mods.fml.common.FMLCommonHandler;

public class Help {
    
    public static void log(String s){
        if(Reference.DEBUG)
            System.out.println("[MineTech+]"+s);
    }

    public static void logSide(){
        if(FMLCommonHandler.instance().getEffectiveSide().isClient())
            log("CLIENT");
        else
            log("SERVER");
    }
    
    public static void logSide(String s){
        if(FMLCommonHandler.instance().getEffectiveSide().isClient())
            log("CLIENT:"+s);
        else
            log("SERVER:"+s);
    }
    
}
