package weightwatcherstesting;

import java.util.List;

/**
 * This class represents a word.
 * 
 * @author Preethi
 *
 */
public class Word {

	private String name;
	private List<String> meanings;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMeanings() {
		return meanings;
	}

	public void setMeanings(List<String> meanings) {
		this.meanings = meanings;
	}
}
