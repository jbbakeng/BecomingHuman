package thestinkerbell.becominghuman.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import thestinkerbell.becominghuman.common.EventHandlerCommon;
import thestinkerbell.becominghuman.items.ModItems;
import thestinkerbell.becominghuman.network.packets.PacketSyncMana;

public class CommonProxy{

    public void preInit(FMLPreInitializationEvent e) {
    	ModItems.createItems();
    	
    	//BecomingHuman.network.registerMessage(PacketSyncMana.Handler.class, PacketSyncMana.class, 0, Side.CLIENT);

    }

    public void init(FMLInitializationEvent e) {
    	
    	//Registering to event buses
    	EventHandlerCommon handler = new EventHandlerCommon();
    	MinecraftForge.EVENT_BUS.register(handler);
    	FMLCommonHandler.instance().bus().register(handler);
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
