package thestinkerbell.becominghuman.human;

import java.util.HashMap;
import java.util.Iterator;

import thestinkerbell.becominghuman.human.diseases.AllKnownDiseases;
import thestinkerbell.becominghuman.human.diseases.Disease;
import thestinkerbell.becominghuman.human.diseases.Diseases;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty;
import thestinkerbell.becominghuman.human.properties.Properties;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.Risks;
import thestinkerbell.becominghuman.human.symptoms.Symptoms;

public class Human {

	private final AllKnownDiseases akd = new AllKnownDiseases(); 
	private HumanBiology biology = new HumanBiology();
	//TODO active_diseases must be saved and loaded!
	protected Diseases active_diseases = new Diseases();

	public Human() {
	}
	
	public Properties getListOfBasicHumanProperties() {
		Properties list = new Properties();
		list.addAll(biology.basic_properties.values());
		return list;
	}
	
	public Properties getListOfGermHumanProperties() {
		Properties list = new Properties();
		list.addAll(biology.germ_properties.values());
		return list;
	}
	
	public Properties getListOfAllHumanProperties() {
		Properties list = new Properties();
		list.addAll(biology.basic_properties.values());
		list.addAll(biology.compound_properties.values());
		list.addAll(biology.germ_properties.values());
		return list;
	}
	
	public Property getHumanPropertyWithName(String name) {
		HashMap<String, Property> properties = new HashMap();
		properties.putAll(biology.basic_properties);
		properties.putAll(biology.compound_properties);
		properties.putAll(biology.germ_properties);
		Property test = properties.get(name);
		return properties.get(name);
	}
	
	public GermHumanProperty getGermHumanPropertyWithName(String name) {
		return biology.germ_properties.get(name);
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

	public Diseases getActiveDiseases() {
		return active_diseases;
	}
	
	protected Diseases getApplicableDiseases() {
		Risks risks = getCurrentRisks();
		Diseases diseases = akd.identifyDiseasesBasedOnRisks(risks);
		return diseases;
	}

	public Symptoms getListOfAllSymptoms() {
		Symptoms allSymptoms = new Symptoms();
		Diseases diseases = getActiveDiseases();		
		for(Disease disease : diseases) {
			allSymptoms.addAll(disease.getSymptoms());
		}
		return allSymptoms;
	}

	public void updateHealth() {
		updateActiveDiseases();
		removeCuredDiseases();
		addNewDiseases();
	}

	private void updateActiveDiseases() {
		for(Disease disease : active_diseases) {
			disease.updateProgress();
		}
	}

	private void addNewDiseases() {
		Diseases applicable_diseases = getApplicableDiseases();
		applicable_diseases.removeAll(active_diseases);
		for(Disease disease : applicable_diseases) {
			disease.activate();
		}
		active_diseases.addAll(applicable_diseases);
	}

	private void removeCuredDiseases() {
		for (Iterator<Disease> iterator = active_diseases.iterator(); iterator.hasNext();) {
			Disease disease = iterator.next();
		    if (disease.isCured()) {
		        iterator.remove();
		    }
		}
	}
}
