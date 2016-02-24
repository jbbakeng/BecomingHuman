package thestinkerbell.becominghuman.human;

import java.util.HashMap;

import thestinkerbell.becominghuman.human.diseases.AllKnownDiseases;
import thestinkerbell.becominghuman.human.diseases.Disease;
import thestinkerbell.becominghuman.human.diseases.Diseases;
import thestinkerbell.becominghuman.human.properties.Properties;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.human.properties.basic.AgeBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.BodyTemperatureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.DiastolicBloodPressureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeartRateMaxBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeartRateRestingBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeartRateBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.SetPointBodyTemperatureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.SystolicBloodPressureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WaterBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WhiteBloodCellsBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BMICompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BloodPressureCompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.CompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.HydrationLevelCompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.germ.GermHumanProperty;
import thestinkerbell.becominghuman.human.properties.germ.InfluenzaAVirusHumanProperty;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.Risks;
import thestinkerbell.becominghuman.human.symptoms.Symptoms;

final public class Human {

	private final AllKnownDiseases akd = new AllKnownDiseases(); 
	
	private HashMap<String, BasicHumanProperty> basic_properties; //sync using packages
	private HashMap<String, CompoundHumanProperty> compound_properties; //updates when its basic components updates
	private HashMap<String, GermHumanProperty>  germ_properties; //sync using packages
	
	public Human() {
		basic_properties = new HashMap<String, BasicHumanProperty>();
		compound_properties = new HashMap<String, CompoundHumanProperty>();
		germ_properties = new HashMap<String, GermHumanProperty>();
		this.addHumanProperties();
	}
	
	public Properties getListOfBasicHumanProperties() {
		Properties list = new Properties();
		list.addAll(basic_properties.values());
		return list;
	}
	
	public Properties getListOfGermHumanProperties() {
		Properties list = new Properties();
		list.addAll(germ_properties.values());
		return list;
	}
	
	public Properties getListOfAllHumanProperties() {
		Properties list = new Properties();
		list.addAll(basic_properties.values());
		list.addAll(compound_properties.values());
		list.addAll(germ_properties.values());
		return list;
	}
	
	public Property getHumanPropertyWithName(String name) {
		HashMap<String, Property> properties = new HashMap();
		properties.putAll(basic_properties);
		properties.putAll(compound_properties);
		properties.putAll(germ_properties);
		Property test = properties.get(name);
		return properties.get(name);
	}
	
	public GermHumanProperty getGermHumanPropertyWithName(String name) {
		return germ_properties.get(name);
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
		this.addHumanProperty(basic_properties, new SetPointBodyTemperatureBasicHumanProperty());
		this.addHumanProperty(basic_properties, new HeartRateBasicHumanProperty());
		this.addHumanProperty(basic_properties, new HeartRateRestingBasicHumanProperty());
		this.addHumanProperty(basic_properties, new HeartRateMaxBasicHumanProperty());
		this.addHumanProperty(basic_properties, systolic);
		this.addHumanProperty(basic_properties, diastolic);
		this.addHumanProperty(basic_properties, new WhiteBloodCellsBasicHumanProperty());
		
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

	public void setValue(String name, Double value) throws Exception {
		Property property = getHumanPropertyWithName(name);
		if(property != null)
			property.setValue(value);
		else
			throw new Exception("No human property named "+name);
	}
	
	public void setAntibodies(String name, Double antibodies) throws Exception {
		GermHumanProperty property = getGermHumanPropertyWithName(name);
		if(property != null)
			property.setAntibodies(antibodies);
		else
			throw new Exception("No human property named "+name);
	}

	public Risks getCurrentRisks() {
		Risks risks = new Risks();
		Properties all_properties = this.getListOfAllHumanProperties();
		for(Property property : all_properties) {
			Risk risk = property.getRisk();
			risks.add(risk);
		}
		return risks;
	}
	
	public Diseases getCurrentDiseases() {
		Risks risks = getCurrentRisks();
		Diseases diseases = akd.identifyDiseasesBasedOnRisks(risks);
		return diseases;
	}

	public Symptoms getListOfAllSymptoms() {
		Symptoms allSymptoms = new Symptoms();
		Diseases diseases = getCurrentDiseases();		
		for(Disease disease : diseases) {
			allSymptoms.addAll(disease.getSymptoms());
		}
		return allSymptoms;
	}
}
