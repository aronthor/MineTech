package me.aronth.minetechplus.core.helpers;

import java.io.ByteArrayOutputStream;

import me.aronth.minetechplus.core.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHelper {

    /**
     * This is a PacketHelper i wrote because i HATE packets
     * @author SpeakersMind
     */
    
    /**
     * This method handles creating the packet and sending it, and if MineTech+ is in debug mode, it will log to the console
     * @param bos ByteArrayOutputStream holding DataOutputStream with packet information
     */
    public static void sendPacketToServer(ByteArrayOutputStream bos){
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = Reference.MOD_ID;
        packet.data = bos.toByteArray();
        packet.length = bos.size();
        PacketDispatcher.sendPacketToServer(packet);
        if(Reference.DEBUG)
            System.out.println("Packet send to server on channel: "+Reference.MOD_ID);
    }
    
    public static void sendPacketToPlayer(ByteArrayOutputStream bos, Player player){
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = Reference.MOD_ID;
        packet.data = bos.toByteArray();
        packet.length = bos.size();
        PacketDispatcher.sendPacketToPlayer(packet, player);
        if(Reference.DEBUG)
            System.out.println("Packet send to client(" + ((EntityPlayer)player).username + ") on channel: "+Reference.MOD_ID);
    }
    
}
