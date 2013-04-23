package me.aronth.minetechplus.core.network;

import me.aronth.minetechplus.core.helpers.IdeaHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandMineTech extends CommandBase {

    @Override
    public int getRequiredPermissionLevel(){
        return 0;
    }
    
    @Override
    public String getCommandName() {
        return "minetech";
    }

    @Override
    public void processCommand(ICommandSender icommandsender, String[] astring) {
        if(astring[0].equals("forget")){
            IdeaHelper.forgetAllIdeas(icommandsender.getCommandSenderName());
        }
    }

}
