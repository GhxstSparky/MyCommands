package com.ghxstsparky.mycommands;

import java.util.Iterator;

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
}
