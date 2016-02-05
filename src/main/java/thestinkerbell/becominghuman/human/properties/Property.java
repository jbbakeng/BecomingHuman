package thestinkerbell.becominghuman.human.properties;

import thestinkerbell.becominghuman.human.risks.Risk;

public interface Property<TYPE> {

	TYPE getValue();

	void setValue(TYPE value);

	Risk getRisk();

	int hashCode();

	boolean equals(Object obj);

}