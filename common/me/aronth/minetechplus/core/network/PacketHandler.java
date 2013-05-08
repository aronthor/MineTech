package me.aronth.minetechplus.core.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.blocks.tileentitys.TileWorkstation;
import me.aronth.minetechplus.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
	    if(Reference.DEBUG)System.out.println("Ooo.. a packet");
		if(packet.channel == Reference.MOD_ID){
			if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
				handleClientPacket(packet, player);
			}else{
				handleServerPacket(packet, player);
			}
		}
	}

	private void handleServerPacket(Packet250CustomPayload packet, Player player) {
	    if(Reference.DEBUG)System.out.println("Server Packet Received");
	    DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
	    try {
            int packetId = dis.readInt();
            
            if(Reference.DEBUG)System.out.println("Handling packet with id:"+packetId);
            
            if(packetId == 1){
                String username = dis.readUTF();
                if(Reference.DEBUG)System.out.println("received username:"+username);
                //if(Reference.DEBUG)System.out.println("Player:"+((EntityPlayer)player).username);
                
                // Create a packet to send back !! :P
                /*ArrayList<IIdea> list = IdeaManager.instance.getUnlockedIdeas(((EntityPlayer)player));
                ByteArrayOutputStream bos = new ByteArrayOutputStream(4+list.size()*4);
                DataOutputStream dos = new DataOutputStream(bos);
                try{
                    dos.writeInt(1);
                    for(IIdea id : list){
                        dos.writeInt(IdeaManager.instance.getIdeaId(id));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                PacketHelper.sendPacketToPlayer(bos, player);*/
            }
            
            if(packetId == 2){ // Idea refined in workstation
                //if(FMLCommonHandler.instance().getEffectiveSide().isServer())System.out.println("We are on a server");
                //World w = FMLClientHandler.instance().getClient().theWorld;
                //if(w != null){
                    String username = dis.readUTF();
                    EntityPlayer me = MineTechPlus.instance.playerTracker.getPlayerByUsername(username);
                    int x = dis.readInt();
                    int y = dis.readInt();
                    int z = dis.readInt();
                    World w = DimensionManager.getWorld(me.dimension);
                    TileEntity tile = w.getBlockTileEntity(x, y, z);
                    if(tile instanceof TileWorkstation){
                        //((TileWorkstation) tile).refineIdea(me);
                        ((TileWorkstation)tile).countdownToRefine(me);
                    }
                    if(Reference.DEBUG)System.out.println("Idea Refined !!");
                //}
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
	}

	private void handleClientPacket(Packet250CustomPayload packet, Player player) {
	    if(Reference.DEBUG)System.out.println("Client Packet Received");
	    DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        try {
            int packetId = dis.readInt();
            
            if(Reference.DEBUG)System.out.println("Handling packet with id:"+packetId);
            
            if(packetId == 1){
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
	}

}
