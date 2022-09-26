package com.ghxstsparky.mycommands.command;

import java.util.Random;

import com.ghxstsparky.mycommands.MyCommands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class RtpCommand {
	public RtpCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("rtp").then(Commands.argument("Range", IntegerArgumentType.integer()).executes((command) -> {
			return rtp(command.getSource(), Integer.parseInt(command.getInput().split(" ")[1]));
		})).executes((command) -> {
			return rtp(command.getSource(), 5000);
		}));
	}

	private static int rtp(CommandSourceStack source, int range) throws CommandSyntaxException {
		ServerPlayer player = source.getPlayerOrException();
		BlockPos center = new BlockPos(0,0,0);
		
		BlockPos destination = BlockPos.randomInCube(new Random(), 10, center, range).iterator().next();
		destination = destination.atY(0);
		
		if (!player.level.isLoaded(destination)) {
			player.level.getChunkAt(destination).setLoaded(true);
		}
		
		destination = MyCommands.GetTopBlock(destination, player);
		
		player.teleportTo(destination.getX(), destination.getY(), destination.getZ());
		
		return 1;
	}
	
}
