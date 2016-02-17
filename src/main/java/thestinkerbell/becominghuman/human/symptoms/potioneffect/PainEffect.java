package thestinkerbell.becominghuman.human.symptoms.potioneffect;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PainEffect extends Potion {

	public static final ResourceLocation icon = new ResourceLocation("becominghuman", "/textures/potioneffects/pain.png".substring(1));

	public PainEffect(int potionID, boolean badEffect, int potionColor) {
		super(potionID, icon, badEffect, potionColor);
	}

	public Potion setIconIndex(int x, int y) {
		super.setIconIndex(x, y);
		return (Potion) this;
	}
	
	public int getStatusIconIndex() {
		ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(icon);
		Minecraft.getMinecraft().renderEngine.bindTexture(icon);
		
		return super.getStatusIconIndex();
	}
	
	public boolean isBadEffect() {
		return true;
	}
	
}
