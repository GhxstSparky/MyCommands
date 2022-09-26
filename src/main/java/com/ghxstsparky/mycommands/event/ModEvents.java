package com.ghxstsparky.mycommands.event;

import com.ghxstsparky.mycommands.MyCommands;
import com.ghxstsparky.mycommands.command.HomeCommand;
import com.ghxstsparky.mycommands.command.RemoveHomeCommand;
import com.ghxstsparky.mycommands.command.RtpCommand;
import com.ghxstsparky.mycommands.command.SetHomeCommand;
import com.ghxstsparky.mycommands.command.TopCommand;

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
		new RemoveHomeCommand(event.getDispatcher());
		new TopCommand(event.getDispatcher());
		new RtpCommand(event.getDispatcher());
		
		ConfigCommand.register(event.getDispatcher());
	}
}
