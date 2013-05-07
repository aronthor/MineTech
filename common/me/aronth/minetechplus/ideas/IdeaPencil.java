package me.aronth.minetechplus.ideas;

import me.aronth.minetechplus.core.ItemHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class IdeaPencil extends Idea {

    public IdeaPencil(int id) {
        super(id);
        setRecipe(2);
        addMaterial(new ItemStack(Item.coal, 1));
        addMaterial(new ItemStack(Item.stick));
        addMaterial(new ItemStack(Item.sign));
        setContent(ItemHandler.pencil);
    }

}
