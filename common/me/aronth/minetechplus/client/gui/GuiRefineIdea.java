package me.aronth.minetechplus.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import me.aronth.minetechplus.blocks.tileentitys.TileWorkstation;
import me.aronth.minetechplus.core.helpers.PacketHelper;
import me.aronth.minetechplus.ideas.Idea;
import me.aronth.minetechplus.inventory.ContainerWorkstation;
import me.aronth.minetechplus.items.ItemIdea;
import me.aronth.minetechplus.lib.Constants;
import me.aronth.minetechplus.lib.Reference;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class GuiRefineIdea extends GuiContainer {

    private TileWorkstation station;
    
    public GuiRefineIdea(InventoryPlayer playerInv, TileWorkstation te) {
        super(new ContainerWorkstation(te, playerInv));
        station = te;
    }
    
    @SuppressWarnings({ "unchecked" })
    @Override
    public void initGui(){
        super.initGui();
        //GuiButton btnRefine = new GuiButton(1, this.guiLeft+65, this.guiTop+33, 18*2+10, 20, "Refine!");
        //this.buttonList.add(btnRefine);
        this.buttonList.add(new GuiButtonRefine(1, this.guiLeft+132, this.guiTop+19+18+5));
    }
    
    public void updateScreen(){
        super.updateScreen();
        /*GuiButton btnRefine = (GuiButton) buttonList.get(0);
        if(this.mc.thePlayer.experienceLevel < IdeaHelper.getRequiredLevels(station.findBookcases(), mc.thePlayer))
            btnRefine.enabled = false;
        if(station.getStackInSlot(0) != null && station.getStackInSlot(0).stackTagCompound.getInteger("refined") < 3)
            btnRefine.enabled = false;
        if(station.getStackInSlot(0) == null)
            btnRefine.enabled = false;*/
    }
    
    protected void actionPerformed(GuiButton par1GuiButton) {
        int btn = par1GuiButton.id;
        if(btn == 1){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos);
            try {
                dos.writeInt(2);
                dos.writeUTF(this.mc.thePlayer.username);
                dos.writeInt(this.station.xCoord);
                dos.writeInt(this.station.yCoord);
                dos.writeInt(this.station.zCoord);
                PacketHelper.sendPacketToServer(bos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //this.station.refineIdea(mc.thePlayer);
            /*PacketMineTech packet = new PacketRefineIdea(this.mc.thePlayer.username);
            packet.send();*/
            
        }
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        //fontRenderer.setUnicodeFlag(true);
        fontRenderer.drawString(LanguageRegistry.instance().getStringLocalization("gui.minetech.workstation"), 8, 6, 4210752);
        fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
        
        /*int bookcases = this.station.findBookcases();
        if(this.mc.thePlayer.experienceLevel < IdeaHelper.getRequiredLevels(bookcases, mc.thePlayer))
            this.drawCenteredString(fontRenderer, "You need " + IdeaHelper.getRequiredLevels(bookcases, mc.thePlayer) + " levels", this.xSize/2, 61, 0x00ff00);*/
        fontRenderer.setUnicodeFlag(true);
        
        //if(this.inventorySlots.getSlot(0).getStack().getItem() != null && this.inventorySlots.getSlot(0).getStack().getItem() instanceof ItemIdea){
        if(station.getStackInSlot(0) != null){
            if(station.getStackInSlot(0).getItem() instanceof ItemIdea){
                if(station.getStackInSlot(0).stackTagCompound != null && station.getStackInSlot(0).stackTagCompound.hasKey(Constants.NBT_IDEA)){
                    Idea idea = Idea.getIdeaById(station.getStackInSlot(0).stackTagCompound.getInteger(Constants.NBT_IDEA));
                    if(idea != null){
                        fontRenderer.drawString(idea.getName(), 8, 6+20, 4210752);
                        fontRenderer.drawSplitString(idea.getDescription(), 8, 6+30, 120, 4210752);
                    }
                }
            }
        }
        
        fontRenderer.setUnicodeFlag(false);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        mc.renderEngine.bindTexture(Reference.GUI_REFINEIDEA);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        drawRefineLevel();
    }
    
    public void drawRefineLevel(){
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        int refineLevel = 0;
        ItemStack stack = this.inventorySlots.getSlot(0).getStack();
        if(stack == null)
            return;
        if(stack.hasTagCompound()){
            if(stack.stackTagCompound.hasKey(Constants.NBT_REFINED)){
                refineLevel = stack.stackTagCompound.getInteger(Constants.NBT_REFINED);
                /*double percentDone = 0;
                TileWorkstation workstation = ((TileWorkstation)((ContainerWorkstation)inventorySlots).station);
                if(workstation.cooldown > 0)
                    percentDone = workstation.cooldown / workstation.waitTime;
                int width = (int) percentDone * 41;
                System.out.println(width + " ; " + percentDone + " : " + workstation.cooldown);*/
                int width = 0;
                if(refineLevel > 0)
                    this.drawTexturedModalRect(x+6, y+18, this.xSize, 0, (refineLevel == 0 ? width : 41), 6);
                if(refineLevel > 1){
                    this.drawTexturedModalRect(x+6+42, y+18, this.xSize, 0, (refineLevel == 1 ? width : 41), 6);
                    this.drawTexturedModalRect(x+6+41, y+18, this.xSize, 6, 1, 6);
                }
                if(refineLevel > 2){
                    this.drawTexturedModalRect(x+6+84, y+18, this.xSize, 0, (refineLevel == 2 ? width : 41), 6);
                    this.drawTexturedModalRect(x+6+83, y+18, this.xSize, 6, 1, 6);
                }
            }
        }
    }

}
