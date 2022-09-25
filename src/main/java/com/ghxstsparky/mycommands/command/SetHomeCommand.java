package com.ghxstsparky.mycommands.command;

import com.ghxstsparky.mycommands.MyCommands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;

public class SetHomeCommand{
	public SetHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("sethome").then(Commands.argument("Name", StringArgumentType.string()).executes((command) -> {
			return setHome(command.getSource(), command.getInput().split(" ")[1]);
		})).executes((command) -> {
			return setHome(command.getSource(), "home");
		}));
	}
	
	private int setHome(CommandSourceStack source, String arg) throws CommandSyntaxException {
		ServerPlayer player = source.getPlayerOrException();
		BlockPos playerPos = player.blockPosition();
		String homes[] = player.getPersistentData().getString(MyCommands.MOD_ID + "homes").split(" ");
		int maxHomes = 3;
		if (source.hasPermission(2)) {
			maxHomes = 20;
		}
		
		boolean homeExists = false;
		
		for (String s : homes) {
			if (s == arg) {
				homeExists = true;
				break;
			}
		}
		
		homes = MyCommands.push(homes, arg);
		
		if (homes.length < maxHomes && !homeExists) {
			if (homeExists) {
				player.getPersistentData().putString(MyCommands.MOD_ID + "homes", MyCommands.stringFromArray(homes));
			}
			
			
			player.getPersistentData().putIntArray(MyCommands.MOD_ID + arg,
					new int[]{ playerPos.getX(), playerPos.getY(), playerPos.getZ() });
			
			source.sendSuccess(new TextComponent("Set new home: " + arg), true);
			return 1;			
		} else {
			source.sendFailure(new TextComponent("You can only have " + maxHomes + " max homes!"));
			return -1;
		}
	}
}
