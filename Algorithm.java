import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class Algorithm {

	public static int countOcc(String word, char letter) {
		int count = 0;
		for (int j = 0; j < word.length(); j++) {
			if (word.charAt(j) == letter)
				count++;
		}
		return count;
	}

	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> a : list) {
			temp.put(a.getKey(), a.getValue());
		}
		return temp;
	}

	public static void main(String[] args) {
		try {
			System.out.println("Unesite željeni niz znakova:");
			Scanner input = new Scanner(System.in);
			String text = input.nextLine();
			input.close();
			text = text.toLowerCase();
			int[] count = { 0, 0, 0, 0, 0 };
			char[] logic = { 'l', 'o', 'g', 'i', 'c' };
			String word;
			int totalLogicCount = 0;
			String line = "";
			HashMap<String, Integer> logicMap = new HashMap<>();
			int totalCount = 0;
			String[] words = text.split(" ");
			int[] sum = new int[words.length];
			int counter = 0;
			for (int i = 0; i < words.length; i++) {
				word = words[i];
				for (int j = 0; j < words[i].length(); j++) {
					char c = word.charAt(j);
					if (c >= 'a' && c <= 'z')
						totalCount++;
				}
			}
			for (int i = 0; i < words.length; i++) {
				word = words[i];
				for (int j = 0; j < logic.length; j++) {
					counter = countOcc(word, logic[j]);
					if (counter != 0) {
						count[j] = counter;
						if (!line.contains(Character.toString(logic[j])))
							line += logic[j];
					}
				}
				sum[i] = IntStream.of(count).sum();
				totalLogicCount += sum[i];
				if (line != "")
					logicMap.put(line + "," + word.length(), sum[i]);
				line = "";
				for (int k = 0; k < count.length; k++)
					count[k] = 0;
			}
			Map<String, Integer> logicMapSorted = sortByValue(logicMap);
			double res = 0.00;
			for (Map.Entry<String, Integer> map : logicMapSorted.entrySet()) {
				res = (double) map.getValue().intValue() / totalLogicCount;
				res = Math.round(res * 100.00) / 100.00;
				System.out.println("Key ={" + map.getKey() + "}, Value = " + map.getValue() + ", Result = " + res);
			}
			res = (double) totalLogicCount / totalCount;
			res = Math.round(res * 100.0) / 100.0;
			System.out.print("Total frequency = " + res);
		} catch (Exception e) {
			System.exit(-1);

		}
	}

}
