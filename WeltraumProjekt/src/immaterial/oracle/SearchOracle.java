package immaterial.oracle;

import java.util.LinkedList;

public class SearchOracle extends Oracle{

	private static final long serialVersionUID = 1L;
	
	
	public SearchOracle() {
		super.setType("Orakel der Suche");
	}
	
	
	public <T> int findIndex(LinkedList<T> linkedList, T target) {
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).equals(target)) {
                return i; 
            }
        }
        return -1; 
    }
	
	
	public boolean checkIfIndexIsPositive(int index) {
		if (index != -1) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
}
