package me.aronth.minetechplus.items;

import java.util.List;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.lib.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemResources extends MTItem{

    public String[] names = {"ingotCopper", "ingotTin"};
    public Icon[] icons = new Icon[names.length];
    
    public ItemResources(int id) {
        super(id);
        this.setCreativeTab(MineTechPlus.tab);
        this.setUnlocalizedName("resource");
        this.setHasSubtypes(true);
    }
    
    public String getUnlocalizedName(ItemStack stack){
        if(stack.getItemDamage() > names.length)
            return "item.minetech.resource";
        return "item.minetech."+names[stack.getItemDamage()];
    }
    
    public Icon getIconFromDamage(int meta){
        return icons[meta];
    }
    
    @SuppressWarnings({
            "rawtypes", "unchecked"
    })
    @SideOnly(Side.CLIENT)

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List){
        for(int i = 0; i < names.length; i++){
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconReg){
        for(int i = 0; i < names.length; i++){
            icons[i] = iconReg.registerIcon(Reference.MOD_ID.toLowerCase()+":"+names[i]);
        }
    }

}
