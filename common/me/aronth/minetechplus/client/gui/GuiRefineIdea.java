package me.aronth.minetechplus.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import me.aronth.minetechplus.blocks.tileentitys.TileWorkstation;
import me.aronth.minetechplus.core.Reference;
import me.aronth.minetechplus.core.helpers.IdeaHelper;
import me.aronth.minetechplus.core.helpers.PacketHelper;
import me.aronth.minetechplus.inventory.ContainerWorkstation;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

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
        GuiButton btnRefine = new GuiButton(1, this.guiLeft+65, this.guiTop+33, 18*2+10, 20, "Refine!");
        if(this.mc.thePlayer.experienceLevel < IdeaHelper.getRequiredLevels(station.findBookcases(), mc.thePlayer))
            btnRefine.enabled = false;
        this.buttonList.add(btnRefine);
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
        fontRenderer.drawString("Workstation", 8, 6, 4210752);
        
        int bookcases = this.station.findBookcases();
        if(this.mc.thePlayer.experienceLevel < IdeaHelper.getRequiredLevels(bookcases, mc.thePlayer))
            this.drawCenteredString(fontRenderer, "You need " + IdeaHelper.getRequiredLevels(bookcases, mc.thePlayer) + " levels", this.xSize/2, 61, 0x00ff00);
        //fontRenderer.setUnicodeFlag(false);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        mc.renderEngine.bindTexture(Reference.GUI_REFINEIDEA);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

}
