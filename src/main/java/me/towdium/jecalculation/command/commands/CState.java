package me.towdium.jecalculation.command.commands;

import me.towdium.jecalculation.JustEnoughCalculation;
import me.towdium.jecalculation.command.ISubCommand;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Author: towdium
 * Date:   8/10/17.
 */
@ParametersAreNonnullByDefault
public class CState implements ISubCommand {
    @Override
    public String getName() {
        return "state";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/jeca state";
    }

    @Override
    public void execute(ICommandSender sender, String[] args) {
        sender.addChatMessage(new ChatComponentTranslation("inactive"));
    }
}
