package thestinkerbell.becominghuman.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thestinkerbell.becominghuman.extendedentityproperties.HumanExtendedEntityProperties;
import thestinkerbell.becominghuman.human.properties.germ.GermHumanProperty;

public class PacketGermHumanProperty implements IMessage{
	
	private GermHumanProperty property;
	
	public PacketGermHumanProperty(){
		this.property = new GermHumanProperty();
	}
	
	public PacketGermHumanProperty(GermHumanProperty property) {
		this.property = property;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		GermHumanProperty.deserialize(buf, this.property);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		GermHumanProperty.serialize(this.property, buf);
	}
	
	public static class HandlerOnClient implements IMessageHandler<PacketGermHumanProperty, IMessage> {

		@Override
		public IMessage onMessage(final PacketGermHumanProperty message, MessageContext ctx) {
        	IThreadListener mainThread = Minecraft.getMinecraft(); //on the client
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {	
                    //HumanExtendedEntityProperties.get(Minecraft.getMinecraft().thePlayer).set(message.property);
                }
            });
            return null; // no response in this case
		}
		
	}

}
