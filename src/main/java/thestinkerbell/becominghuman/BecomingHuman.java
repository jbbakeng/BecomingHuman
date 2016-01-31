package thestinkerbell.becominghuman;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import thestinkerbell.becominghuman.common.CommonProxy;

@Mod(modid = BecomingHuman.MODID, name = BecomingHuman.MODNAME, version = BecomingHuman.VERSION)
public class BecomingHuman
{
    public static final String MODID = "becominghuman";
    public static final String MODNAME = "Becoming Human";
    //versioning rules: https://mcforge.readthedocs.org/en/latest/conventions/versioning/
    public static final String VERSION = "1.8-0.0.0.0"; 

    @Instance(MODID)
    public static BecomingHuman instance = new BecomingHuman();
    
    @SidedProxy(clientSide="thestinkerbell.becominghuman.client.ClientProxy", serverSide="thestinkerbell.becominghuman.server.ServerProxy")
    public static CommonProxy proxy;
    
    public static SimpleNetworkWrapper network;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	//is called at the very beginning of the startup routine. 
    	//In this method we should read your config file, create Blocks, Items, etc. 
    	//and register them with the GameRegistry.
    	System.out.println("Called method: [preInit]");
    	
    	this.proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
    	//is called after the preInit Handler. In this method we can build up data structures, 
    	//add Crafting Recipes and register new handler.
    	System.out.println("Called method: [init]");
    	
    	this.proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	// is called at the very end. Its used to communicate with other mods 
    	//and adjust your setup based on this.
    	System.out.println("Called method: [postInit]");
    	
    	this.proxy.postInit(e);
    }
}
