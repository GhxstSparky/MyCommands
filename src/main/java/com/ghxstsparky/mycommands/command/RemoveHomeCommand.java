package com.ghxstsparky.mycommands.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ghxstsparky.mycommands.MyCommands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;

public class RemoveHomeCommand {
	public RemoveHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("removehome").then(Commands.argument("Name", StringArgumentType.string()).executes((command) -> {
			return removeHome(command.getSource(), command.getInput().split(" ")[1]);
		})).executes((command) -> {
			return removeHome(command.getSource(), "home");
		}));
	}

	private int removeHome(CommandSourceStack source, String arg) throws CommandSyntaxException {
		ServerPlayer player = source.getPlayerOrException();
		String homes[] = player.getPersistentData().getString(MyCommands.MOD_ID + "homes").split(" ");
		boolean homeExists = false;
		
		for (String s : homes) {
			if (s == arg) {
				homeExists = true;
			}
		}
		
		if (homeExists) {
			source.sendFailure(new TextComponent(arg + "does not exist!"));
			return -1;
		} else {
			List<String> list = new ArrayList<String>(Arrays.asList(homes));
			list.remove(arg);
			homes = list.toArray(new String[0]);
			
			player.getPersistentData().putString(MyCommands.MOD_ID + "homes", MyCommands.stringFromArray(homes));
			player.getPersistentData().remove(MyCommands.MOD_ID + arg);
			source.sendSuccess(new TextComponent("Successfully removed " + arg), true);
			
			return 1;
		}
		
	}
}
