package thestinkerbell.becominghuman.human.symptoms.effects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class HumanEffect extends Potion {

	private ResourceLocation human_effect_icon;

	public HumanEffect(int potionID, ResourceLocation location, boolean badEffect, int potionColor, String name) {
		super(potionID, location, badEffect, potionColor);
		this.human_effect_icon = location;
		this.setPotionName(name);
		this.setIconIndex(0,0);
	}

	@Override
	public final Potion setIconIndex(int x, int y) {
		super.setIconIndex(x, y);
		return (Potion) this;
	}

	@Override
	public final int getStatusIconIndex() {
		ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(human_effect_icon);
		Minecraft.getMinecraft().renderEngine.bindTexture(human_effect_icon);
		
		return super.getStatusIconIndex();
	}

	@Override
	public final boolean isBadEffect() {
		return true;
	}

}