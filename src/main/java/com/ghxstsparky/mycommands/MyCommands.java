package com.ghxstsparky.mycommands;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MyCommands.MOD_ID)
public class MyCommands {
	public static final String MOD_ID = "mycommands";
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	public MyCommands() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("");
	}
	
	public static String[] push(String[] array, String push) {
	    String[] longer = new String[array.length + 1];
	    System.arraycopy(array, 0, longer, 0, array.length);
	    longer[array.length] = push;
	    return longer;
	}
	
	public static String stringFromArray(String[] array) {
		String s = "";
		for (int i = 0; i < array.length; i++) {
			String c = array[i];
			if (i == 0) {
				s += c;
			} else {
				s += " " + c;
			}
		}
		return s;
	}
	
	public static BlockPos GetTopBlock(BlockPos blockPos, Player player) {
		if (atTop(blockPos, player)) {
			return blockPos;
		}
		
		BlockPos block = new BlockPos(blockPos.getX()+1, 320, blockPos.getZ()+1);
		AABB aabb = new AABB(block, blockPos);
		
		Iterable<VoxelShape> shapes = player.getLevel().getBlockCollisions(null, aabb);
		
		List<BlockPos> blocks = new ArrayList<BlockPos>(0);
		
		shapes.forEach((e) -> {
			double x = e.toAabbs().get(0).minX;
			double y = e.toAabbs().get(0).minY+1;
			double z = e.toAabbs().get(0).minZ;
			blocks.add(new BlockPos(x, y, z));
		});
		
		return blocks.get(blocks.size()-1);
	}
	
	public static boolean atTop(BlockPos blockPos, Player player) {
		BlockPos block = new BlockPos(blockPos.getX()+1, 320, blockPos.getZ()+1);
		AABB aabb = new AABB(block, blockPos);
		Iterable<VoxelShape> shapes = player.getLevel().getBlockCollisions(player, aabb);
		return !shapes.iterator().hasNext();
	}
}
