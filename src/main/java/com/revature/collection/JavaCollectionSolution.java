package com.revature.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;


public class JavaCollectionSolution implements JavaCollection {

	private static final Logger LOGGER = Logger.getLogger(JavaCollectionSolution.class);
	
	@Override
	public List<Integer> digits(int n) throws IllegalArgumentException {
		
		if (n <= 0) {
			throw new IllegalArgumentException("Error: The input number cannot be 0 or less."
					+ " Please try again later.");
		}
		
		LinkedList<Integer> l = new LinkedList<>();
		while(n > 0) {
			l.push(n % 10);
			n = n / 10;
		}
		LOGGER.info(l.toString());
		return l;
	}

	@Override
	public Set<?> sort(Object[] array) throws IllegalArgumentException {
		
		if (array == null) {
			throw new IllegalArgumentException("Error: In order to sort properly,"
					+ " the input array must not be empty.");
		}
		
		/*
		 * This sorting will be utilizing the String class for array values.
		 */
		Set<Object> s = new TreeSet<>((o1, o2) -> o1.toString().compareTo(o2.toString()));
		s.addAll(Arrays.asList(array));
		LOGGER.info(s);
				
		return s;
	}

	@Override
	public boolean balancedBrackets(String brackets) throws IllegalArgumentException {
		
		if (brackets == null) {
			throw new IllegalArgumentException("Error: In order to check for balanced brackets,"
					+ " the input string must not be empty.");
		}
		
		LinkedList<Character> l = new LinkedList<Character>();
		for(int i = 0; i < brackets.length(); i++) {
			char c = brackets.charAt(i);
			if(c == '(' || c == '[' || c == '{') {
				l.push(c);
			} else if(c == ')') {
				if(l.isEmpty() || l.pop() != '(') {
					LOGGER.info("This string is not balanced.");
					return false;
				}
			} else if(c == ']') {
				if(l.isEmpty() || l.pop() != '[') {
					LOGGER.info("This string is not balanced.");
					return false;
				}
			} else if(c == '}') {
				if(l.isEmpty() || l.pop() != '{') {
					LOGGER.info("This string is not balanced.");
					return false;
				}	
			}
		}
		LOGGER.info("This string is balanced.");
		return l.isEmpty();
	}

	@Override
	public boolean isPalindrome(int n) throws IllegalArgumentException {
		
		if (n < 0) {
			throw new IllegalArgumentException("Error: The input number cannot be 0."
					+ " Please try again later.");
		}
		
		int i, j, temp = 0;
		int checker = n;
		
		LinkedList<Integer> l = new LinkedList<>();
		while(n > 0) {
			i = n % 10;
			j = i;
			
			/*
			 * This line will take the last digit of the input number after every "/10" has occurred,
			 * and after n = 0, temp will be either the same number as checker (a palindrome),
			 * or the reverse of checker (not a palindrome).
			 */
			temp = temp * 10 + i;
			n = n / 10;
		}
		if(temp != checker) {
			LOGGER.info("The number inputted: " + checker);
			LOGGER.info("The number returned: " + temp);
			LOGGER.info("The input number is not a palindrome");
			return false;
		}
		LOGGER.info("The number inputted: " + checker);
		LOGGER.info("The number returned: " + temp);
		LOGGER.info("The input number is a palindrome.");
		return true;
	}

	@Override
	public Map<Character, Integer> countCharacters(String string) throws IllegalArgumentException {
		
		if (string == null) {
			throw new IllegalArgumentException("Error: In order to check for character occurrences,"
					+ " the input string must not be empty.");
		}
		
		Map<Character, Integer> map = new HashMap<>();
		for(Character key: string.toCharArray()) {
			Integer count = map.get(key);
			
			/*
			 * The following line will be checking the count continuously for
			 * if the key was already found. If not, it will place a 1 for the number
			 * of occurrences (value); otherwise, the count for occurrences will continue
			 * for that character (key) until there are no more within the string.
			 */
			int value = (count == null ? 1 : count + 1);
			map.put(key, value);
		}
		for(Map.Entry<Character, Integer> entry: map.entrySet()) {
			LOGGER.info("Character: " + entry.getKey() + "; Number of occurrences: " + entry.getValue());
		}
		
		return map;
	}

	public static void main(String[] args) {
		
		Object[] array = new String[] {"'Ah.'", "'I see.'", "'Lexicography.'", "'I believe that despite the length,"
				+ " the first thing that's checked is the letter. Afterwards, it's the length.'", "'What's up?'"};
		
		new JavaCollectionSolution().digits(1234125);
		
		new JavaCollectionSolution().sort(array);
		
		new JavaCollectionSolution().balancedBrackets("({[]})");
		new JavaCollectionSolution().balancedBrackets("([)]");
		
		new JavaCollectionSolution().isPalindrome(1459);
		new JavaCollectionSolution().isPalindrome(6523256);
		
		new JavaCollectionSolution().countCharacters("Hello world, and all who inhabit it.");
	}

}
