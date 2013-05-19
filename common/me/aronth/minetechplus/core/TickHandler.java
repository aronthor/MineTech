package me.aronth.minetechplus.core;

import java.util.EnumSet;

import me.aronth.minetechplus.items.ItemMinersHelmet;
import me.aronth.minetechplus.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler{

    private boolean givenEffect = false;
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        for(TickType t : type){
            if(t == TickType.PLAYER){
                //Help.log("Checking...");
                EntityPlayer p = (EntityPlayer)tickData[0];
                //World w = (World)tickData[1];
                //if(p.getCurrentArmor(0) != null){
                if(p.inventory.armorItemInSlot(3) != null && p.inventory.armorItemInSlot(3).getItem() instanceof ItemMinersHelmet){
                    //Help.log("Give Buffs");
                    p.addPotionEffect(new PotionEffect(3, 20, 0, true));
                    givenEffect = true;
                }else{
                    if(givenEffect){
                        p.removePotionEffect(3);
                        givenEffect = false;
                    }
                }
            }
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        
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
