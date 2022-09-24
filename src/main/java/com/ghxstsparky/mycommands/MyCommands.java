package com.ghxstsparky.mycommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
}
