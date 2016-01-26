package thestinkerbell.becominghuman;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thestinkerbell.becominghuman.items.ModItems;

public class CommonProxy{

    public void preInit(FMLPreInitializationEvent e) {
    	ModItems.createItems();
    }

    public void init(FMLInitializationEvent e) {
    	EventHandlerCommon handler = new EventHandlerCommon();
    	MinecraftForge.EVENT_BUS.register(handler);
    	FMLCommonHandler.instance().bus().register(handler);
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
