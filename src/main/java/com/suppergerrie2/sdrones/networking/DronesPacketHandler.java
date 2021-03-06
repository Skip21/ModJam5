package com.suppergerrie2.sdrones.networking;

import com.suppergerrie2.sdrones.Reference;
import com.suppergerrie2.sdrones.networking.ItemsInDroneMessage.ItemsInDroneMessageHandler;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class DronesPacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
	
	public static void register() {
		INSTANCE.registerMessage(ItemsInDroneMessageHandler.class, ItemsInDroneMessage.class, 0, Side.CLIENT);
	}
}
