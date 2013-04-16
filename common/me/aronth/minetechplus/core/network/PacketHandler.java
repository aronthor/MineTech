package me.aronth.minetechplus.core.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import me.aronth.minetechplus.core.Reference;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
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
