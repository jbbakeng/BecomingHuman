package thestinkerbell.becominghuman.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import thestinkerbell.becominghuman.BecomingHuman;
import thestinkerbell.becominghuman.client.render.items.ItemRenderRegister;
import thestinkerbell.becominghuman.common.CommonProxy;
import thestinkerbell.becominghuman.network.packets.PacketBasicHumanProperty;

public class ClientProxy extends CommonProxy{

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		
		//BecomingHuman.network.registerMessage(PacketHumanProperty.HandlerOnClient.class, PacketHumanProperty.class, 0, Side.CLIENT);
		ItemRenderRegister.registerItemRenderer();
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

}
