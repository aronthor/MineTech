package me.aronth.minetechplus.blocks;

import java.util.List;

import me.aronth.minetechplus.MineTechPlus;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOres extends MTBlock{

    public static String[] names = {"Copper", "Tin"};
    private Icon[] icons = new Icon[names.length];
    
    public BlockOres(int id){
        super(id, Material.rock);
        this.setCreativeTab(MineTechPlus.tab);
        this.setUnlocalizedName("oreBlocks");
    }
    
    public int damageDropped(int meta){
        return meta;
    }
    
    public Icon getIcon(int side, int meta){
        return icons[meta];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg){
       for(int i = 0; i < names.length; i++){
           icons[i] = reg.registerIcon(this.getTexture("ore"+names[i]));
       }
    }
    
    @SuppressWarnings({
            "unchecked", "rawtypes"
    })
    public void getSubBlocks(int par1, CreativeTabs tabs, List list){
        for(int i = 0; i < names.length; i++)
            list.add(new ItemStack(this, 1, i));
    }
    
}
