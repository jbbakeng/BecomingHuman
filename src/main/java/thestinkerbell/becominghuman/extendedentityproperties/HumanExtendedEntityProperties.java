package thestinkerbell.becominghuman.extendedentityproperties;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import thestinkerbell.becominghuman.BecomingHuman;
import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.influences.DrinkingWaterInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.influences.InfluenceQueue;
import thestinkerbell.becominghuman.human.properties.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.Properties;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.human.properties.germ.GermHumanProperty;
import thestinkerbell.becominghuman.human.symptoms.Symptom;
import thestinkerbell.becominghuman.human.symptoms.Symptoms;
import thestinkerbell.becominghuman.network.packets.PacketBasicHumanProperty;
import thestinkerbell.becominghuman.network.packets.PacketGermHumanProperty;


public class HumanExtendedEntityProperties implements IExtendedEntityProperties  {
	
	private static final String identifier = "humanproperties";
	private final EntityPlayer player;
	public final Human human;
	
	private final InfluenceQueue influence_queue;
	
    public HumanExtendedEntityProperties(EntityPlayer player) {
        this.player = player;
        this.human  = new Human();
        this.influence_queue = new InfluenceQueue();
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
			this.syncBasicHumanProperties();
			this.syncGermHumanProperties();
		} else {
			return; //do nothing for client side, Packets will arrive from server.
		}
	}

	private void syncBasicHumanProperties() {
		Properties list = human.getListOfBasicHumanProperties();
		for(Property property : list) {
		    this.sendToClient((BasicHumanProperty)property);
		}
	}
	
	private void syncGermHumanProperties() {
		Properties list = human.getListOfGermHumanProperties();
		for(Property property : list) {
		    this.sendToClient((GermHumanProperty)property);
		}
	}
    
    /**
     * Should only be called on SERVER side!
     * @param property The property to be sent to the client
     */
	//@SideOnly(Side.SERVER)
	public void sendToClient(BasicHumanProperty property) {
    	//we are on the server side, we want to send human property information to correct players client
    	PacketBasicHumanProperty msg = new PacketBasicHumanProperty(property);
    	EntityPlayerMP serverPlayer = (EntityPlayerMP) this.player;
    	BecomingHuman.network.sendTo(msg, serverPlayer); //here values are pushed to the client
	}
	
    /**
     * Should only be called on SERVER side!
     * @param property The property to be sent to the client
     */
	//@SideOnly(Side.SERVER)
	public void sendToClient(GermHumanProperty property) {
		System.out.println("Sending germs to client");
    	//we are on the server side, we want to send human property information to correct players client
    	PacketGermHumanProperty msg = new PacketGermHumanProperty(property);
    	EntityPlayerMP serverPlayer = (EntityPlayerMP) this.player;
    	BecomingHuman.network.sendTo(msg, serverPlayer); //here values are pushed to the client
	}
	
    /**
     * Should only be called on CLIENT side!
     * @param property The property that should be set on the client
     */
	//@SideOnly(Side.CLIENT)
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
    
    /**
     * Should only be called on CLIENT side!
     * @param property The property that should be set on the client
     */
	//@SideOnly(Side.CLIENT)
    public void set(GermHumanProperty property) {
    	if (this.isServerSide()) {
    		System.err.println("DO NOT CALL THIS FROM SERVER SIDE!");
    		return;
    	}
    	else {
        	try {
        		System.out.println("Setting germ properties");
				this.human.setValue(property.getName(), property.getValue());
				this.human.setAntibodies(property.getName(), property.getAntibodies());
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
    
    public void applyPotionEffectsFromSymptoms() {
		Symptoms symptoms = human.getListOfAllSymptoms();
		for(Symptom symptom : symptoms) {
			PotionEffect effect = symptom.getEffect();
			//TODO on client or server side???
			player.addPotionEffect(effect);
		}
    }
    
	public void addInfluenceToQueue(Influence influence) {
		this.influence_queue.add(influence);
	}
    
    public void saveReviveRelevantNBTData(NBTTagCompound nbt, boolean wasDeath) {
        if (!wasDeath)
            this.saveNBTData(nbt);
    }

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		saveBasicHumanProperties(compound);
		saveGermHumanProperties(compound);
	}

	private void saveBasicHumanProperties(NBTTagCompound compound) {
		Properties list = human.getListOfBasicHumanProperties();
		for(Property property : list) {
			ByteBuf buf = Unpooled.buffer();
			BasicHumanProperty.serialize((BasicHumanProperty)property, buf);			
			compound.setByteArray(property.getName(), buf.array());
        }
	}
	
	private void saveGermHumanProperties(NBTTagCompound compound) {
		Properties list = human.getListOfGermHumanProperties();
		for(Property property : list) {
			ByteBuf buf = Unpooled.buffer();
			GermHumanProperty.serialize((GermHumanProperty)property, buf);			
			compound.setByteArray(property.getName(), buf.array());
        }
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
    	//This only happens on the server!!!
		//This happens BEFORE the entity joins the world (on both server and client side)
		loadBasicHumanProperties(compound);
		loadGermHumanProperties(compound);
	}

	private void loadBasicHumanProperties(NBTTagCompound compound) {
		Properties list = human.getListOfBasicHumanProperties();
		for(Property property : list) {
			ByteBuf buf = Unpooled.buffer();	
			buf.writeBytes(compound.getByteArray(property.getName()));
			BasicHumanProperty.deserialize(buf, (BasicHumanProperty)property);
        }
	}
	
	private void loadGermHumanProperties(NBTTagCompound compound) {
		Properties list = human.getListOfGermHumanProperties();
		for(Property property : list) {
			ByteBuf buf = Unpooled.buffer();	
			buf.writeBytes(compound.getByteArray(property.getName()));
			GermHumanProperty.deserialize(buf, (GermHumanProperty)property);
        }
	}

	@Override
	public void init(Entity entity, World world) {	
	}

	public void applyInfluences() {
		this.influence_queue.process();
	}


}
