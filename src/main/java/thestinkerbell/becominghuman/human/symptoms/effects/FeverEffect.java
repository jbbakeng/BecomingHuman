package thestinkerbell.becominghuman.human.symptoms.effects;

import net.minecraft.util.ResourceLocation;

public final class FeverEffect extends HumanEffect {
	
	private static boolean is_bad = true;
	private static int color = 0;
	private static String name = "potion.fevereffect";
	private static ResourceLocation icon = new ResourceLocation("becominghuman", "/textures/potioneffects/fever.png".substring(1));

	public FeverEffect(int id) {
		super(id, icon ,is_bad, color, name);
	}
}
