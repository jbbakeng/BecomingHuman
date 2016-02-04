package thestinkerbell.becominghuman.utilities;

public class Pair<TYPE> {
	
	private final TYPE first;
	private final TYPE second;
	
	public Pair(TYPE first, TYPE second) {
		this.first = first;
		this.second = second;
	}
	
	public TYPE first() {
		return this.first;
	}
	
	public TYPE second() {
		return this.second;
	}
	
    public String toString() {
        return  first() + "/" + second();
    }
	
}
