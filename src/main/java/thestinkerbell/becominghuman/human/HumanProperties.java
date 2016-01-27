package thestinkerbell.becominghuman.human;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class HumanProperties implements IExtendedEntityProperties  {
	
	private static final String identifier = "humanproperties";
	private final EntityPlayer player;
	private HumanProperty height = new HumanProperty("Height");
	
    public HumanProperties(EntityPlayer player) {
        this.player = player;
        
    }
	
    public static HumanProperties get(EntityPlayer player) {
        return (HumanProperties) player.getExtendedProperties(identifier);
    }

    public static void register(EntityPlayer player) {
        player.registerExtendedProperties(identifier, new HumanProperties(player));
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
