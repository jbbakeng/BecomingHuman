package thestinkerbell.becominghuman.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thestinkerbell.becominghuman.deprecated.PlayerData;
import thestinkerbell.becominghuman.human.HumanExtendedEntityProperties;

public final class ModItems {
	
	public static Item tutorialItem;
	
    public static void createItems() {
    	GameRegistry.registerItem(tutorialItem = new BasicItem("tutorial_item"), "tutorial_item");
    	
    	GameRegistry.registerItem(new Item() {
    	    @Override
    	    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
    	        if (!world.isRemote) {
    	        	System.out.println("TODO: IMPLEMENT");
    	            //PlayerData.get(player).setMana(PlayerData.get(player).getMana() + 1);
    	        }
    	        return stack;
    	    }
    	}.setUnlocalizedName("increase_item").setCreativeTab(CreativeTabs.tabMisc), "increase_item");

    	GameRegistry.registerItem(new Item() {
    	    @Override
    	    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
    	        if (!world.isRemote) {
    	        	System.out.println("TODO: IMPLEMENT");
    	        	//HumanExtendedEntityProperties.get(player).update(property);
    	            //PlayerData.get(player).setMana(PlayerData.get(player).getMana() - 1);
    	        }
    	        return stack;
    	    }
    	}.setUnlocalizedName("decrease_item").setCreativeTab(CreativeTabs.tabMisc), "decrease_item");

    	GameRegistry.registerItem(new Item() {
    	    @Override
    	    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
    	        if (world.isRemote) {
    	        	System.out.println("TODO: IMPLEMENT");
    	            //player.addChatMessage(new ChatComponentText("Mana: " + PlayerData.get(player).getMana()));
    	        }
    	        return stack;
    	    }
    	}.setUnlocalizedName("output_item").setCreativeTab(CreativeTabs.tabMisc), "output_item");
    }

}
