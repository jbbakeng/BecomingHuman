package thestinkerbell.becominghuman;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thestinkerbell.becominghuman.items.ModItems;

public class CommonProxy{

    public void preInit(FMLPreInitializationEvent e) {
    	ModItems.createItems();
    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
