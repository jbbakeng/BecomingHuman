package thestinkerbell.becominghuman.human.symptoms.effects;

import net.minecraft.util.ResourceLocation;

public final class CoughEffect extends HumanEffect {
	
	private static boolean is_bad = true;
	private static int color = 0;
	private static String name = "potion.cougheffect";
	private static ResourceLocation icon = new ResourceLocation("becominghuman", "/textures/potioneffects/cough.png".substring(1));

	public CoughEffect(int id) {
		super(id, icon ,is_bad, color, name);
	}
}
