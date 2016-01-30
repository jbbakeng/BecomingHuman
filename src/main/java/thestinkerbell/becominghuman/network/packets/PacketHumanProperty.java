package thestinkerbell.becominghuman.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thestinkerbell.becominghuman.deprecated.PlayerData;
import thestinkerbell.becominghuman.human.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.HumanProperty;

public class PacketHumanProperty implements IMessage{
	
	private HumanProperty property;
	
	public PacketHumanProperty(){
		this.property = new HumanProperty();
	}
	
	public PacketHumanProperty(HumanProperty property) {
		this.property = property;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		HumanProperty.deserialize(buf, this.property);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		HumanProperty.serialize(this.property, buf);
	}
	
	public static class HandlerOnClient implements IMessageHandler<PacketHumanProperty, IMessage> {

		@Override
		public IMessage onMessage(final PacketHumanProperty message, MessageContext ctx) {
        	IThreadListener mainThread = Minecraft.getMinecraft(); //on the client
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {	
                    System.out.println("Package arrived on the CLIENT from the SERVER.");
                    HumanExtendedEntityProperties.get(Minecraft.getMinecraft().thePlayer).set(message.property);
                }
            });
            return null; // no response in this case
		}
		
	}

}
