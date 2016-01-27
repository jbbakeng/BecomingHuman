package thestinkerbell.becominghuman.deprecated;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

//PacketSyncMana should be sent from Server
@Deprecated
public class PacketSyncMana implements IMessage{
    private int mana;

    public PacketSyncMana() {}

    public PacketSyncMana(int mana) {
        this.mana = mana;
        System.out.println("Creating mana package with "+mana);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.mana = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.mana);
    }
    // ========================================================================

    // Handler should be run by client
	public static class HandlerOnClient implements IMessageHandler<PacketSyncMana, IMessage> {

        @Override
        public IMessage onMessage(final PacketSyncMana message, final MessageContext ctx) {
        	
            if (ctx.side != Side.CLIENT) {
                System.err.println("PacketSyncMana received on wrong side:" + ctx.side);
                return null;
              }
        	
        	//IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.worldObj; // on the server
        	IThreadListener mainThread = Minecraft.getMinecraft(); //on the client
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {	
                    System.out.println(String.format("Received %s from %s", message.mana, Minecraft.getMinecraft().thePlayer.getName()));
                    PlayerData.get(Minecraft.getMinecraft().thePlayer).setMana(message.mana);
                }
            });
            return null; // no response in this case
        }
	}
	// ========================================================================
}
