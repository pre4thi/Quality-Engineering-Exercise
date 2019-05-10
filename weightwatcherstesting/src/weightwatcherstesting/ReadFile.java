package weightwatcherstesting;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ReadFile {

	public static void main(String[] args) {
		new ReadFile().doesFileExist("words.txt");
	}

	public void doesFileExist(String filename) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
			List<Word> words;
			words = parseFileForWords(reader);
			printWords(words);
		} catch (FileNotFoundException e) {
			System.err.println("File " + filename + " is not found in the project path");
		} catch (IOException e) {
			System.err.println("IO Exception occurred when reading the file " + e.getMessage());
		}
	}

	/**
	 * Parses the file for words and meanings
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	private List<Word> parseFileForWords(BufferedReader reader) throws IOException {
		List<Word> words;
		words = new ArrayList<>();
		String line;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split("–");

			String wordText = tokens[0];
			String meaning = tokens[1];
			List<String> meaningList = Arrays.asList(meaning.split(","));

			Word word = new Word();
			word.setName(wordText);
			word.setMeanings(meaningList);
			words.add(word);
		}
		return words;
	}

	/**
	 * Pretty prints the words and its meanings
	 * 
	 * @param words
	 */
	private void printWords(List<Word> words) {
		IntStream.range(0, words.size()).forEach(index -> {
			Word word = words.get(index);
			System.out.println("Word " + (index + 1) + ". " + word.getName());
			final List<String> meanings = word.getMeanings();
			IntStream.range(0, meanings.size()).forEach(index2 -> {
				System.out.println("    Meaning " + (index2 + 1) + " -> " + meanings.get(index2));
			});
		});
	}
}
