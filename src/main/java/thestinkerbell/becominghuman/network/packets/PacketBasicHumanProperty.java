package thestinkerbell.becominghuman.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thestinkerbell.becominghuman.human.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;

public class PacketBasicHumanProperty implements IMessage{
	
	private BasicHumanProperty property;
	
	public PacketBasicHumanProperty(){
		this.property = new BasicHumanProperty();
	}
	
	public PacketBasicHumanProperty(BasicHumanProperty property) {
		this.property = property;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		BasicHumanProperty.deserialize(buf, this.property);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		BasicHumanProperty.serialize(this.property, buf);
	}
	
	public static class HandlerOnClient implements IMessageHandler<PacketBasicHumanProperty, IMessage> {

		@Override
		public IMessage onMessage(final PacketBasicHumanProperty message, MessageContext ctx) {
        	IThreadListener mainThread = Minecraft.getMinecraft(); //on the client
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {	
                    HumanExtendedEntityProperties.get(Minecraft.getMinecraft().thePlayer).set(message.property);
                }
            });
            return null; // no response in this case
		}
		
	}

}
