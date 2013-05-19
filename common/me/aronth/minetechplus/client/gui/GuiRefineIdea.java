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
import me.aronth.minetechplus.lib.Help;
import me.aronth.minetechplus.lib.Textures;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class GuiRefineIdea extends GuiContainer {

    private TileWorkstation station;
    
    public GuiRefineIdea(InventoryPlayer playerInv, World w, int x, int y, int z) {
        super(new ContainerWorkstation((TileWorkstation)w.getBlockTileEntity(x, y, z), playerInv));
        station = (TileWorkstation)w.getBlockTileEntity(x, y, z);
    }
    
    public GuiRefineIdea(InventoryPlayer playerInv, TileWorkstation tileWorkstation){
        super(new ContainerWorkstation(tileWorkstation, playerInv));
        station = tileWorkstation;
    }
    
    @SuppressWarnings({ "unchecked" })
    @Override
    public void initGui(){
        super.initGui();
        //GuiButton btnRefine = new GuiButton(1, this.guiLeft+65, this.guiTop+33, 18*2+10, 20, "Refine!");
        //this.buttonList.add(btnRefine);
        GuiButton btn = new GuiButtonRefine(1, this.guiLeft+132, this.guiTop+19+18+5);
        btn.enabled = station.cooldown == 0;
        this.buttonList.add(btn);
    }
    
    public void updateScreen(){
        super.updateScreen();
        if(station.cooldown == 0){
            //Help.log("Jarr");
            ((GuiButton)buttonList.get(0)).enabled = true;
        }
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
            par1GuiButton.enabled = false;
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
            station.countdownToRefine();
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
        mc.renderEngine.bindTexture(Textures.GUI_REFINEIDEA);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        drawRefineLevel();
    }
    
    public void drawRefineLevel(){
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        int refineLevel = 0;
        ItemStack stack = station.getStackInSlot(0);
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
                
                if(refineLevel > 0)
                    this.drawTexturedModalRect(x+6, y+18, this.xSize, 0, 41, 6);
                if(refineLevel > 1){
                    this.drawTexturedModalRect(x+6+42, y+18, this.xSize, 0, 41, 6);
                    this.drawTexturedModalRect(x+6+41, y+18, this.xSize, 6, 1, 6);
                }
                if(refineLevel > 2){
                    this.drawTexturedModalRect(x+6+84, y+18, this.xSize, 0, 41, 6);
                    this.drawTexturedModalRect(x+6+83, y+18, this.xSize, 6, 1, 6);
                }
                
                if(station.cooldown > 0){
                    int barWidth = station.getTimerSlide(41);
                    Help.log("W:"+barWidth + "R:"+refineLevel);
                    if(refineLevel == 0)
                        this.drawTexturedModalRect(x+6, y+18, this.xSize, 0, 41-barWidth, 6);
                    if(refineLevel == 1)
                        this.drawTexturedModalRect(x+6+42, y+18, this.xSize, 0, 41-barWidth, 6);
                    if(refineLevel == 2)
                        this.drawTexturedModalRect(x+6+84, y+18, this.xSize, 0, 41-barWidth, 6);
                    
                    //drawTexturedModalRect(x+43, y+31-laneOneCook, 191, 16-laneOneCook, 3, laneOneCook);
                    
                    
                }
            }
        }
    }

}
