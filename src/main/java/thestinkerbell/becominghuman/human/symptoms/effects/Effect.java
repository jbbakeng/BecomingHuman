package thestinkerbell.becominghuman.human.symptoms.effects;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.potion.Potion;
import scala.util.control.Exception.Catch;

public class Effect {
	
	private static int custom_potion_index = 50;

    public static Potion pain;
    public static Potion headache;
    public static Potion muscle_pain;
    public static Potion soar_throat;
    public static Potion fever;
    public static Potion cough;
    public static Potion sneez;
    public static Potion tired;
    public static Potion dying;	
    
    
    public Effect() {
	    this.makeRoomForMorePotions(); 
	    this.initEffects();
    }


	private void makeRoomForMorePotions() {
		//In minecraft there is only room for 32 potionTypes and
		// most of them are already used up, so we make some more room
		
		Potion[] potionTypes = null;
	
	    for (Field f : Potion.class.getDeclaredFields()) {
	        f.setAccessible(true);
	        try {
	            if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
	                Field modfield = Field.class.getDeclaredField("modifiers");
	                modfield.setAccessible(true);
	                modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);
	
	                potionTypes = (Potion[])f.get(null);
	                final Potion[] newPotionTypes = new Potion[256];
	                System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
	                f.set(null, newPotionTypes);
	            }
	        } catch (Exception e) {
	            System.err.println("Severe error, please report this to the mod author:");
	            System.err.println(e);
	        }
	    }
	}


	private void initEffects() {
		pain = new PainEffect(custom_potion_index++);
	    headache = new HeadAcheEffect(custom_potion_index++);
	    muscle_pain = new MusclePainEffect(custom_potion_index++);
	    soar_throat = new SoarThroatEffect(custom_potion_index++);
	    fever = new FeverEffect(custom_potion_index++);
	    cough = new CoughEffect(custom_potion_index++);
	    sneez = new SneezeEffect(custom_potion_index++);
	    tired = new TiredEffect(custom_potion_index++);
	    dying = new DyingEffect(custom_potion_index++);
	}
}
