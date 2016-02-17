package thestinkerbell.becominghuman.human.symptoms.effects;

import net.minecraft.util.ResourceLocation;

public final class TiredEffect extends HumanEffect {

	private static boolean is_bad = true;
	private static int color = 0;
	private static String name = "potion.tiredeffect";
	private static ResourceLocation icon = new ResourceLocation("becominghuman", "/textures/potioneffects/tired.png".substring(1));

	public TiredEffect(int id) {
		super(id, icon ,is_bad, color, name);
	}
}
