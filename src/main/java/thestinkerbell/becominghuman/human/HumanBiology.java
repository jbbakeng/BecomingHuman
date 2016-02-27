package thestinkerbell.becominghuman.human;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import thestinkerbell.becominghuman.human.properties.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.CompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty.Transmission;
import thestinkerbell.becominghuman.human.properties.basic.AgeBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.BodyTemperatureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.DiastolicBloodPressureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.FitnessBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeartRateBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeartRateMaxBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeartRateRestingBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.BodyTemperatureSetPointBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.SystolicBloodPressureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WaterBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WhiteBloodCellsBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BMICompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BloodPressureCompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.HydrationLevelCompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.germ.InfluenzaAVirusHumanProperty;

public class HumanBiology {
	
	public static final String hr = "Heart Rate";
	public static final String hr_resting = "Heart Rate Resting";
	public static final String hr_max = "Heart Rate Max";
	public static final String fitness = "Fitness";
	public static final String water = "Water";
	public static final String weight = "Weight";
	public static final String age = "Age";
	public static final String bt = "Body Temperature";
	public static final String bt_sp = "Body Temperature Set Point";
	public static final String bp = "Blood Pressure";
	public static final String bp_d = "Diastolic Blood Pressure";
	public static final String bp_s = "Systolic Blood Pressure";
	public static final String height  ="Height";
	public static final String wbc = "White Blood Cells";
	public static final String bmi = "BMI";
	public static final String hydration = "Hydration Level";
	public static final String influenza_a = "Influenza A";
	
	public static void applyChange(Human human, String property_name, Double change) {
		Property property = human.getHumanPropertyWithName(property_name);
		Double old_value = (Double) property.getValue();
		Double new_value = old_value + change;
		
		try {
			human.setValue(property_name, new_value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public HashMap<String, BasicHumanProperty> basic_properties;
	public HashMap<String, CompoundHumanProperty> compound_properties;
	public HashMap<String, GermHumanProperty> germ_properties;

	public HumanBiology() {
		basic_properties = new HashMap<String, BasicHumanProperty>();
		compound_properties = new HashMap<String, CompoundHumanProperty>();
		germ_properties = new HashMap<String, GermHumanProperty>();
		this.addHumanProperties();
	}
	
	private void addHumanProperties() {
		WeightBasicHumanProperty weight = new WeightBasicHumanProperty();
		HeightBasicHumanProperty height = new HeightBasicHumanProperty();
		SystolicBloodPressureBasicHumanProperty systolic = new SystolicBloodPressureBasicHumanProperty();
		DiastolicBloodPressureBasicHumanProperty diastolic = new DiastolicBloodPressureBasicHumanProperty();
		WaterBasicHumanProperty water = new WaterBasicHumanProperty();

		this.addHumanProperty(basic_properties, new AgeBasicHumanProperty());
		this.addHumanProperty(basic_properties, weight);
		this.addHumanProperty(basic_properties, height);
		this.addHumanProperty(basic_properties, water);
		this.addHumanProperty(basic_properties, new BodyTemperatureBasicHumanProperty());
		this.addHumanProperty(basic_properties, new BodyTemperatureSetPointBasicHumanProperty());
		this.addHumanProperty(basic_properties, new HeartRateBasicHumanProperty());
		this.addHumanProperty(basic_properties, new HeartRateRestingBasicHumanProperty());
		this.addHumanProperty(basic_properties, new HeartRateMaxBasicHumanProperty());
		this.addHumanProperty(basic_properties, systolic);
		this.addHumanProperty(basic_properties, diastolic);
		this.addHumanProperty(basic_properties, new WhiteBloodCellsBasicHumanProperty());
		this.addHumanProperty(basic_properties, new FitnessBasicHumanProperty());
		
		this.addHumanProperty(compound_properties, new BMICompoundHumanProperty(weight, height));
		this.addHumanProperty(compound_properties, new BloodPressureCompoundHumanProperty(systolic, diastolic));
		this.addHumanProperty(compound_properties, new HydrationLevelCompoundHumanProperty(water, weight));
		
		this.addHumanProperty(germ_properties, new InfluenzaAVirusHumanProperty());
	}
	
	private void addHumanProperty(HashMap<String, GermHumanProperty> properties, GermHumanProperty humanProperty) {
		properties.put(humanProperty.getName(), humanProperty);
	}

	static private void addHumanProperty(HashMap<String, BasicHumanProperty> properties, BasicHumanProperty humanProperty) {
		properties.put(humanProperty.getName(), humanProperty);
	}
	
	static private void addHumanProperty(HashMap<String, CompoundHumanProperty> properties, CompoundHumanProperty humanProperty) {
		properties.put(humanProperty.getName(), humanProperty);
	}

	public GermHumanProperty getRandomGerm() {
		ArrayList<GermHumanProperty> germs = getArrayListOfGerms();
		int min = 0;
		int max = germs.size()-1;
		Random random = new Random();
		int randomNum = random.nextInt((max - min) + 1) + min;
		GermHumanProperty random_germ = germs.get(randomNum);
		return random_germ;
	}

	private ArrayList<GermHumanProperty> getArrayListOfGerms() {
		ArrayList<GermHumanProperty> germs = new ArrayList<GermHumanProperty>(); 
		germs.addAll(germ_properties.values());
		return germs;
	}
}