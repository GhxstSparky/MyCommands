package com.ghxstsparky.mycommands.command;

import com.ghxstsparky.mycommands.MyCommands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;

public class TopCommand {
	public TopCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("top").requires((command) -> {
			return command.hasPermission(2);
		}).executes((command) ->{
			return top(command.getSource());
		}));
	}

	private static int top(CommandSourceStack source) throws CommandSyntaxException {
		ServerPlayer player = source.getPlayerOrException();
		BlockPos playerPos = player.blockPosition();
		int yPos = playerPos.getY();
		
		if (MyCommands.atTop(playerPos, player)) {
			source.sendSuccess(new TextComponent("Already at top"), true);
			return 1;
		}
		
		BlockPos topBlock = MyCommands.GetTopBlock(playerPos, player);
		int ammountTraveled = topBlock.getY()-yPos;
		
		player.teleportTo(topBlock.getX(), topBlock.getY(), topBlock.getZ());
		
		source.sendSuccess(new TextComponent("Teleported " + ammountTraveled + " blocks."), true);
		return 1;
	}
	
	
}
