package thestinkerbell.becominghuman.human;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import thestinkerbell.becominghuman.BecomingHuman;
import thestinkerbell.becominghuman.human.properties.Properties;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.network.packets.PacketBasicHumanProperty;


public class HumanExtendedEntityProperties implements IExtendedEntityProperties  {
	
	private static final String identifier = "humanproperties";
	private final EntityPlayer player;
	final public Human human;
	
    public HumanExtendedEntityProperties(EntityPlayer player) {
        this.player = player;
        this.human  = new Human();
    }
	
    public static HumanExtendedEntityProperties get(EntityPlayer player) {
        return (HumanExtendedEntityProperties) player.getExtendedProperties(identifier);
    }

    public static void register(EntityPlayer player) {
        player.registerExtendedProperties(identifier, new HumanExtendedEntityProperties(player));
    }
    
    public boolean isServerSide() {
        return this.player instanceof EntityPlayerMP;
    }
    
    /**
     * Can be called from both Server and Client side.
     */
	public void syncAll() {
		if (this.isServerSide()) {
			Properties list = human.getListOfBasicHumanProperties();
			for(Property property : list) {
	            this.sendToClient((BasicHumanProperty)property);
	        }
		} else {
			return; //do nothing for client side, Packets will arrive from server.
		}
	}
    
    /**
     * Should only be called on SERVER side!
     * @param property The property to be sent to the client
     */
	public void sendToClient(BasicHumanProperty property) {
    	//we are on the server side, we want to send human property information to correct players client
    	PacketBasicHumanProperty msg = new PacketBasicHumanProperty(property);
    	EntityPlayerMP serverPlayer = (EntityPlayerMP) this.player;
    	BecomingHuman.network.sendTo(msg, serverPlayer); //here values are pushed to the client
	}
	
    /**
     * Should only be called on CLIENT side!
     * @param property The property that should be set on the client
     */
    public void set(BasicHumanProperty property) {
    	if (this.isServerSide()) {
    		System.err.println("DO NOT CALL THIS FROM SERVER SIDE!");
    		return;
    	}
    	else {
        	try {
				this.human.setValue(property.getName(), property.getValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
    
    
    public void saveReviveRelevantNBTData(NBTTagCompound nbt, boolean wasDeath) {
        if (!wasDeath)
            this.saveNBTData(nbt);
    }

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		Properties list = human.getListOfBasicHumanProperties();
		for(Property property : list) {
			ByteBuf buf = Unpooled.buffer();
			BasicHumanProperty.serialize((BasicHumanProperty)property, buf);			
			compound.setByteArray(property.getName(), buf.array());
        }
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
    	//This only happens on the server!!!
		//This happens BEFORE the entity joins the world (on both server and client side)
		Properties list = human.getListOfBasicHumanProperties();
		for(Property property : list) {
			ByteBuf buf = Unpooled.buffer();	
			buf.writeBytes(compound.getByteArray(property.getName()));
			BasicHumanProperty.deserialize(buf, (BasicHumanProperty)property);
        }
	}

	@Override
	public void init(Entity entity, World world) {	
	}

}
