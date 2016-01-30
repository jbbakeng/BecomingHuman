package thestinkerbell.becominghuman.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import thestinkerbell.becominghuman.BecomingHuman;
import thestinkerbell.becominghuman.items.ModItems;
import thestinkerbell.becominghuman.network.packets.PacketHumanProperty;

public class CommonProxy{

    public void preInit(FMLPreInitializationEvent e) {
    	ModItems.createItems();
    	BecomingHuman.network = NetworkRegistry.INSTANCE.newSimpleChannel(BecomingHuman.MODID);
    }

    public void init(FMLInitializationEvent e) {
    	
    	BecomingHuman.network.registerMessage(PacketHumanProperty.HandlerOnClient.class, PacketHumanProperty.class, 0, Side.CLIENT);
    	
    	//Registering to event buses
    	EventHandlerCommon handler = new EventHandlerCommon();
    	MinecraftForge.EVENT_BUS.register(handler);
    	FMLCommonHandler.instance().bus().register(handler);
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
