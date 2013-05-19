package me.aronth.minetechplus.items;

import me.aronth.minetechplus.MineTechPlus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;

public class ItemMinersHelmet extends MTItem implements ISpecialArmor{

    public ItemMinersHelmet(int id) {
        super(id);
        this.setUnlocalizedName("minersHelmet");
        this.setCreativeTab(MineTechPlus.tab);
        this.setMaxDamage(150);
    }

    public boolean isValidArmor(ItemStack stack, int armorType, Entity entity){
        return armorType == 0;
    }
    
    @Override
    public ArmorProperties getProperties(EntityLiving player, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, 2, 2);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return 0;
    }

    @Override
    public void damageArmor(EntityLiving entity, ItemStack stack, DamageSource source, int damage, int slot) {
        stack.damageItem(1, entity);
    }

}
