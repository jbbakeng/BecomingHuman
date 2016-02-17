package thestinkerbell.becominghuman.human.symptoms.effects;

import net.minecraft.util.ResourceLocation;

public class HeadAcheEffect extends PainEffect {

	public HeadAcheEffect(int id) {
		super(	id,
				new ResourceLocation("becominghuman", "/textures/potioneffects/pain.png".substring(1)),
				true,
				0,
				"potion.headacheeffect"
				);
		this.setIconIndex(1,0);
	}

}
