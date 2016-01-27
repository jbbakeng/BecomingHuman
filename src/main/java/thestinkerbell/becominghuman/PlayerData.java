package thestinkerbell.becominghuman;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import thestinkerbell.becominghuman.network.packets.PacketSyncMana;

public class PlayerData implements IExtendedEntityProperties {
	
	private static final String identifier = "tutorialPlayerData";

    // PROPERTIES =============================================================

    private final EntityPlayer player;
    
    private int mana;

    // CONSTRUCTOR, GETTER, REGISTER ==========================================

    public PlayerData(EntityPlayer player) {
        this.player = player;
        this.mana = 100;
    }
    
    public static PlayerData get(EntityPlayer player) {
        return (PlayerData) player.getExtendedProperties(identifier);
    }

    public static void register(EntityPlayer player) {
        player.registerExtendedProperties(identifier, new PlayerData(player));
    }

    public boolean isServerSide() {
        return this.player instanceof EntityPlayerMP;
    }
    
    public void setMana(int mana) {
    	System.out.println("setMana "+mana);
        this.mana = mana;
        this.syncMana();
    }

    public int getMana() {
        return this.mana;
    }
    
    public void syncMana() {
    	boolean test = this.isServerSide();
        if (this.isServerSide()){
        	//we are on the server side, we want to send mana infomation to correct players client
        	System.out.println("Server side, sending package to player: "+this.player.getName());
        	PacketSyncMana msg = new PacketSyncMana(this.getMana());
        	EntityPlayerMP serverPlayer = (EntityPlayerMP) this.player;
        	// ==== BUG ====
        	BecomingHuman.network.sendTo(msg, serverPlayer);
        	// ==== BUG ====
        	
        	//BecomingHuman.network.sendToServer(msg);
        }
        else{
        	System.out.println("Client side, NOT sending package.");
        }
    }
    
    // LOAD, SAVE =============================================================
    
    public void saveReviveRelevantNBTData(NBTTagCompound nbt, boolean wasDeath) {
        if (!wasDeath)
            this.saveNBTData(nbt);
    }
    
    @Override
    public void saveNBTData(NBTTagCompound nbt) {
    	nbt.setInteger("mana", this.getMana());
    }

    @Override
    public void loadNBTData(NBTTagCompound nbt) {
        if (nbt.hasKey("mana", 3)){
        	System.out.println("NTB mana "+nbt.getInteger("mana"));
        	this.setMana(nbt.getInteger("mana"));
        }
    }

    @Override
    public void init(Entity entity, World world) {
    }
}