package me.aronth.minetechplus.core.network.packets;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import me.aronth.minetechplus.core.Reference;
import cpw.mods.fml.common.FMLCommonHandler;

public class PacketMineTech {

    protected ByteArrayOutputStream bos;
    protected DataOutputStream dos;
    
    /*
     * This constructs the packet at first !!
     */
    public PacketMineTech(){
        bos = new ByteArrayOutputStream();
    }
    
    public void writePacket(DataOutputStream dos){
        
    }
    
    public void readPacket(DataInputStream dos){
        
    }
    
    public void execute(){
        
    }
    
    public void buildPacket(){
        if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
            if(Reference.DEBUG)System.out.println("Building packet on client");
        }else{
            if(Reference.DEBUG)System.out.println("Building packet on server");
        }
    }
    
    public void send(){
        
    }
    
}
