package thestinkerbell.becominghuman.human.symptoms.effects;

import net.minecraft.util.ResourceLocation;

public class PainEffect extends HumanEffect {

	public PainEffect(int id) {
		this(	id, 
				new ResourceLocation("becominghuman", "/textures/potioneffects/pain.png".substring(1)), 
				true, 
				0, 
				"potion.paineffect");
	}
	
	protected PainEffect(int id, ResourceLocation icon, boolean is_bad, int color, String name) {
		super(id, icon ,is_bad, color, name);
	}
	
}
