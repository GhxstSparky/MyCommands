package com.ghxstsparky.mycommands.command;

import java.util.ArrayList;
import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TopCommand {
	public TopCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("top").requires((command) -> {
			return command.hasPermission(2);
		}).executes((command) ->{
			return top(command.getSource());
		}));
	}

	private static int top(CommandSourceStack source) throws CommandSyntaxException {
		Player player = source.getPlayerOrException();
		BlockPos playerPos = player.blockPosition();
		int xPos = playerPos.getX();
		int yPos = playerPos.getY();
		int zPos = playerPos.getZ();
		BlockPos block = new BlockPos(xPos+1, 320, zPos+1);
		AABB aabb = new AABB(block, new BlockPos(xPos, yPos, zPos));
		
		Iterable<VoxelShape> shapes = player.getLevel().getBlockCollisions(player, aabb);
		
		if (!shapes.iterator().hasNext()) {
			source.sendSuccess(new TextComponent("Already at top"), true);
			return 1;
		}
		
		List<BlockPos> blocks = new ArrayList<BlockPos>(0);
		
		shapes.forEach((e) -> {
			double x = e.toAabbs().get(0).minX;
			double y = e.toAabbs().get(0).minY+1;
			double z = e.toAabbs().get(0).minZ;
			blocks.add(new BlockPos(x, y, z));
		});
		
		
		
		BlockPos topBlock = blocks.get(blocks.size()-1);
		int ammountTraveled = topBlock.getY()-yPos;
		
		player.teleportTo(topBlock.getX(), topBlock.getY(), topBlock.getZ());
		
		source.sendSuccess(new TextComponent("Teleported " + ammountTraveled + " blocks."), true);
		return 1;
	}
}
