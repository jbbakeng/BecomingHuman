package thestinkerbell.becominghuman.human.symptoms.effects;

import net.minecraft.util.ResourceLocation;

public final class SneezeEffect extends HumanEffect {

	private static boolean is_bad = true;
	private static int color = 0;
	private static String name = "potion.sneezeeffect";
	private static ResourceLocation icon = new ResourceLocation("becominghuman", "/textures/potioneffects/sneeze.png".substring(1));

	public SneezeEffect(int id) {
		super(id, icon ,is_bad, color, name);
	}
}
