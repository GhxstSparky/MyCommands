package com.ghxstsparky.mycommands.event;

import com.ghxstsparky.mycommands.MyCommands;
import com.ghxstsparky.mycommands.command.HomeCommand;
import com.ghxstsparky.mycommands.command.SetHomeCommand;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = MyCommands.MOD_ID)
public class ModEvents {
	@SubscribeEvent
	public static void onCommandsRegister(RegisterCommandsEvent event) {
		new SetHomeCommand(event.getDispatcher());
		new HomeCommand(event.getDispatcher());
		
		ConfigCommand.register(event.getDispatcher());
	}
}