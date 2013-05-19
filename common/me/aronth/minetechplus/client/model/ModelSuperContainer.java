package me.aronth.minetechplus.client.model;

import net.minecraft.client.model.ModelBase;

public class ModelSuperContainer extends ModelBase{

    //private IModelCustom model;
    //private ModelRenderer chestBase;
    
    public int textureWidth = 64;
    public int textureHeight = 64;
    
    
    public ModelSuperContainer(){
        //model = AdvancedModelLoader.loadModel(Models.SUPER_CONTAINER);
    }
    
    public void render(){
        //model.renderAll();
        //chestBase = new ModelRenderer(this);
        //chestBase.addBox(par1, par2, par3, par4, par5, par6);
    }
    
    public void renderPart(String part){
        //model.renderPart(part);
    }
    
}
