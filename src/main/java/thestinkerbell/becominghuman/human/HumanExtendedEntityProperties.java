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
import thestinkerbell.becominghuman.network.packets.PacketHumanProperty;


public class HumanExtendedEntityProperties implements IExtendedEntityProperties  {
	
	private static final String identifier = "humanproperties";
	private final EntityPlayer player;
	private HumanProperties properties;
	
    public HumanExtendedEntityProperties(EntityPlayer player) {
        this.player = player;
        this.properties  = new HumanProperties();
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
		System.out.println("2.		SYNCING ALL!");
		if (this.isServerSide()) {
			List<HumanProperty> list = properties.getListOfHumanProperties();
			for(HumanProperty property : list) {
	            this.sendToClient(property);
	        }
		} else {
			return; //do nothing for client side, Packets will arrive from server.
		}
	}
    
    /**
     * Should only be called on SERVER side!
     * @param property The property to be sent to the client
     */
	public void sendToClient(HumanProperty property) {
    	//we are on the server side, we want to send human property information to correct players client
    	PacketHumanProperty msg = new PacketHumanProperty(property);
    	EntityPlayerMP serverPlayer = (EntityPlayerMP) this.player;
    	BecomingHuman.network.sendTo(msg, serverPlayer); //here values are pushed to the client
	}
	
    /**
     * Should only be called on CLIENT side!
     * @param property The property that should be set on the client
     */
    public void set(HumanProperty property) {
    	this.properties.setValue(property.name, property.getValue());
    	if (this.isServerSide()) {
    		System.err.println("DO NOT CALL THIS FROM SERVER SIDE!");
    		return;
    	}
    	else {
        	System.out.println("Setting property on CLIENT. Package arrived from SERVER I assume.");
        	this.properties.setValue(property.name, property.value);
        }
    }
    
    
    public void saveReviveRelevantNBTData(NBTTagCompound nbt, boolean wasDeath) {
        if (!wasDeath)
            this.saveNBTData(nbt);
    }

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		List<HumanProperty> list = properties.getListOfHumanProperties();
		for(HumanProperty property : list) {
			ByteBuf buf = Unpooled.buffer();
			HumanProperty.serialize(property, buf);			
			compound.setByteArray(property.name, buf.array());
        }
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
    	//This only happens on the server!!!
		//This happens BEFORE the entity joins the world (on both server and client side)
		System.out.println("0.		LOADING NTB DATA on Server side.");
		List<HumanProperty> list = properties.getListOfHumanProperties();
		for(HumanProperty property : list) {
			ByteBuf buf = Unpooled.buffer();	
			buf.writeBytes(compound.getByteArray(property.name));
			HumanProperty.deserialize(buf, property);
        }
	}

	@Override
	public void init(Entity entity, World world) {	
	}

}
