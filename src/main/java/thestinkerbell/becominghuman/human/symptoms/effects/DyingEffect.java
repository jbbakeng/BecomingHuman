package thestinkerbell.becominghuman.human.symptoms.effects;

import net.minecraft.util.ResourceLocation;

public final class DyingEffect extends HumanEffect {

	private static boolean is_bad = true;
	private static int color = 0;
	private static String name = "potion.dyingeffect";
	private static ResourceLocation icon = new ResourceLocation("becominghuman", "/textures/potioneffects/dying.png".substring(1));

	public DyingEffect(int id) {
		super(id, icon ,is_bad, color, name);
	}
	
}
