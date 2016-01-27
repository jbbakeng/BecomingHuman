package thestinkerbell.becominghuman.human;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class HumanExtendedEntityProperties implements IExtendedEntityProperties  {
	
	private static final String identifier = "humanproperties";
	private final EntityPlayer player;
	private HumanProperties humanproperties;
	
    public HumanExtendedEntityProperties(EntityPlayer player) {
        this.player = player;
        this.humanproperties  = new HumanProperties();
        
    }
	
    public static HumanExtendedEntityProperties get(EntityPlayer player) {
        return (HumanExtendedEntityProperties) player.getExtendedProperties(identifier);
    }

    public static void register(EntityPlayer player) {
        player.registerExtendedProperties(identifier, new HumanExtendedEntityProperties(player));
    }
    
    public void saveReviveRelevantNBTData(NBTTagCompound nbt, boolean wasDeath) {
        if (!wasDeath)
            this.saveNBTData(nbt);
    }

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub
		
	}

}
