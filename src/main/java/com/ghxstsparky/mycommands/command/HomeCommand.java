package com.ghxstsparky.mycommands.command;

import com.ghxstsparky.mycommands.MyCommands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;

public class HomeCommand {
	public HomeCommand (CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("home").then(Commands.argument("Name", StringArgumentType.string()).executes((command) -> {
			return home(command.getSource(), command.getInput().split(" ")[1]);
		})).executes((command) -> {
			return home(command.getSource(), "home");
		}));
	}
	
	private int home(CommandSourceStack source, String arg) throws CommandSyntaxException {
		ServerPlayer player = source.getPlayerOrException();
		boolean hasHomeSet = player.getPersistentData().getIntArray(MyCommands.MOD_ID + arg).length != 0;
		
		if (hasHomeSet) {
			int[] playerPos = player.getPersistentData().getIntArray(MyCommands.MOD_ID + arg);
			player.teleportTo(playerPos[0], playerPos[1], playerPos[2]);
			
			return 1;
		} else {
			source.sendFailure(new TextComponent(arg+ " has been not set!"));
			return -1;
		}
	}
}
