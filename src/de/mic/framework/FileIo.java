package de.mic.framework;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileIo {

	private static final String FOLDER = "Files";

	public static void main(String[] args) throws IOException {

		String testFile = "day1.txt";
		Stream<String[]> readAllLines = new FileIo().rowsEveryChar(testFile);
		readAllLines.forEach(s -> System.out.println(s[0]));

	}

	List<String> loadFile(String filename) {
		Charset enc = Charset.forName("UTF-8");
		List<String> readAllLines;
		try {
			readAllLines = Files.readAllLines(Path.of(FOLDER, filename), enc);
			return readAllLines;
		} catch (IOException e) {
			throw new RuntimeException(
					String.format("Error loading file {} from {} with Exception {}", filename, FOLDER, e));
		}
	}

	public List<String> rows(String filename) {
		return loadFile(filename);
	}

	public Stream<String[]> rowsCommaSplitted(String filename) {
		return splitWith(filename, ",");
	}

	public Stream<String[]> rowsBlankSplitted(String filename) {
		return splitWith(filename, " ");
	}

	public Stream<String[]> rowsEveryChar(String filename) {
		return splitWith(filename, "");

	}

	private Stream<String[]> splitWith(String filename, String split) {
		return loadFile(filename).stream().map(s -> s.split(split));
	}

	public Stream<Number> rowsAsNumber(String filename) {
		return loadFile(filename).stream().map(Integer::valueOf);
	}
}
