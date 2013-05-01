package me.aronth.minetechplus.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.core.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockOres extends MTBlock{

    public static String[] names = {"Copper", "Tin"};
    private Icon[] icons = new Icon[names.length];
    
    public BlockOres(int id){
        super(id, Material.rock);
        this.setCreativeTab(MineTechPlus.tab);
    }
    
    public Icon getIcon(int side, int meta){
        return icons[meta];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg){
       for(int i = 0; i < names.length; i++){
           icons[i] = reg.registerIcon(Reference.MOD_ID.toLowerCase()+":"+"ore"+names[i]);
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
