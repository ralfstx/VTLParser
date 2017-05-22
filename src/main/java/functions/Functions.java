package functions;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import enums.typesOfNodes.OperatorV;
import staticObjects.StaticObjects;

/**
 * class containing useful functions.
 * 
 * @author LIN
 * @version 0.1
 */
public class Functions {

    public static void showDifferenceInTime(LocalDateTime start, LocalDateTime end) {
	LocalDateTime refTime = LocalDateTime.from(start);
	long years = refTime.until(end, ChronoUnit.YEARS);
	refTime = refTime.plusYears(years);
	long months = refTime.until(end, ChronoUnit.MONTHS);
	refTime = refTime.plusMonths(months);
	long days = refTime.until(end, ChronoUnit.DAYS);
	refTime = refTime.plusDays(days);
	long hours = refTime.until(end, ChronoUnit.HOURS);
	refTime = refTime.plusHours(hours);
	long minutes = refTime.until(end, ChronoUnit.MINUTES);
	refTime = refTime.plusMinutes(minutes);
	long seconds = refTime.until(end, ChronoUnit.SECONDS);
	System.out.println("dt = (" + hours + "." + minutes + "." + seconds + ")");
    }

    /**
     * transforms strings into list of strings. E.g. "S t r i ng" --> {"S", "t",
     * "r", "i", "ng"}.
     * 
     * @param inputString
     *            a non empty {@link String} object
     * @return a {@link List} object containing the ({@link String}) components
     *         of the input string {@code inputString}
     */
    public static List<String> stringToArrayList(String inputString) {
	List<String> rList = null;
	if (inputString != null && !inputString.isEmpty()) {
	    rList = new ArrayList<String>();
	    String[] split = inputString.split(" ");
	    for (String s : split) {
		for (String remain : StaticObjects.remains) {
		    s = s.replace(remain, "");
		}
		s = s.trim();
		rList.add(s);
	    }
	}
	return rList;
    }

    /**
     * creates the intersection of two {@link List} of {@link T} objects. Please
     * note that if an object of type {@link T} is contained twice in one of the
     * two input lists and only once in the other input list this object will be
     * present twice in the resulting list. For a 'clean' intersection see
     * {@link #cleanIntersection(List, List)}.
     * 
     * @param list1
     *            a {@link List} of {@code T} objects
     * @param list2
     *            a {@link List} of {@code T} objects
     * @return the {@code intersection} of the two input lists
     */
    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
	List<T> rList = new ArrayList<T>();
	for (T t : list1) {
	    if (list2.contains(t)) {
		rList.add(t);
	    }
	}
	return rList;
    }

    public static <T> Set<T> intersection (Set<T> set1, Set<T> set2) {
	return Sets.intersection(set1, set2);
    }
    
    
    /**
     * creates a {@link List} of {@link T} objects representing a 'clean'
     * intersection of the two lists in the sense that if an object is
     * represented twice in one of the (input) lists but only once in the other
     * (input) list the resulting list will contain this object only once.
     * 
     * @param list1
     *            a {@link List} of {@code T} objects
     * @param list2
     *            a {@link List} of {@code T} objects
     * @return the {@code intersection} of the two input lists
     */
    public static <T> List<T> cleanIntersection(List<T> list1, List<T> list2) {
	List<T> rList = new ArrayList<T>();

	List<T> listA = new ArrayList<T>(list1);
	List<T> listB = new ArrayList<T>(list2);

	Iterator<T> it = listA.iterator();
	while (it.hasNext()) {
	    T t = it.next();
	    it.remove();
	    if (listB.contains(t)) {
		rList.add(t);
		listB.remove(t);
	    }
	}
	return rList;
    }

    /**
     * creates the union of two {@link List} of {@link T} objects
     * 
     * @param list1
     *            a {@link List} of {@code T} objects
     * @param list2
     *            a {@link List} of {@code T} objects
     * @return the {@code union} of the two input lists
     */
    public static <T> List<T> union(List<T> list1, List<T> list2) {
	List<T> rList = new ArrayList<T>(list1);
	rList.addAll(list2);
	Set<T> set = new HashSet<T>();
	for (T t : rList) {
	    set.add(t);
	}
	rList = new ArrayList<T>();
	for (T t : set) {
	    rList.add(t);
	}
	return rList;
    }

    public static List<String> removeRemains(List<String> list) {
	Iterator<String> it = list.iterator();
	while (it.hasNext()) {
	    String element = it.next();
	    if (StaticObjects.remains.contains(element.trim())) {
		it.remove();
	    } else if (element.trim().isEmpty()) {
		it.remove();
	    }
	}
	return list;
    }

    public static <T> boolean listEquals(List<T> list1, List<T> list2) {
	boolean rValue = false;
	List<T> listA = new ArrayList<T>(list1);
	List<T> listB = new ArrayList<T>(list2);

	boolean found = false;
	Iterator<T> it = listA.iterator();
	while (it.hasNext()) {
	    T t = it.next();
	    it.remove();
	    if (listB.contains(t)) {
		listB.remove(t);
	    } else {
		found = true;
	    }
	}
	rValue = listA.isEmpty() && listB.isEmpty() && !found;

	return rValue;
    }

    public static <T> List<T> getUniqueElements(List<T> list) {
	List<T> rList = null;
	if (list != null && !list.isEmpty()) {
	    rList = new ArrayList<T>();
	    Set<T> set = new HashSet<T>();
	    for (T t : list) {
		set.add(t);
	    }
	    for (T t : set) {
		rList.add(t);
	    }
	}
	return rList;
    }

    public static <T> List<T> reverseOrder(List<T> list) {
	List<T> rList = new ArrayList<>();
	if (list != null && !list.isEmpty()) {
	    for (int i = list.size() - 1; i >= 0; i--) {
		rList.add(list.get(i));
	    }
	}
	return rList;
    }

    public static Set<String> extractColumns(String s) {
	Set<String> rSet = null;
	if (s != null && !s.isEmpty()) {
	    rSet = new HashSet<>();
	    String[] split = s.split(",");
	    for (String part : split) {
		String content = "";
		if (part.contains(" as ")) {
		    content = part.substring(part.indexOf(" as "), part.length()).replace("as ", "").replace("\"", "")
			    .trim();
		} else {
		    if (part.contains("\\.")) {
			content = part.substring(part.indexOf("\\."), part.length()).trim();
		    } else {
			content = part.trim();
		    }
		}
		rSet.add(content);
	    }
	}
	return rSet;
    }

    public static <T> boolean contains(String term, T[] list) {
	List<T> tList = new ArrayList<>();
	for (T t : list) {
	    tList.add(t);
	}
	return contains(term, tList);
    }

    public static <T> boolean contains(String term, List<T> list) {
	boolean found = false;
	int i = 0;
	do {
	    if (term.contains(list.get(i).toString())) {
		found = true;
	    } else {
		i++;
	    }
	} while (i < list.size() && !found);
	return found;
    }

    public static <T> int getPosition(String term, T[] list) {
	List<T> tList = new ArrayList<>();
	for (T t : list) {
	    tList.add(t);
	}
	return getPosition(term, tList);
    }

    public static <T> int getPosition(String term, List<T> list) {
	int rValue = (-1);
	if (contains(term, list)) {
	    rValue = term.length();
	    int i = 0;
	    do {
		int pos = term.indexOf(list.get(i).toString());
		if (pos > 0 && pos < rValue) {
		    rValue = pos;
		}
		i++;
	    } while (i < list.size());
	}
	return rValue;
    }

    public static void main(String[] args) {
	System.out.println("******************************************");
	System.out.println(LocalDateTime.now());

	String s = "[inner setA as \"A\", setB on A.x = setB.x] {y = k * x + d, filter (x > 0)}";
	System.out.println(Functions.contains(s, OperatorV.values()));
	int pos = Functions.getPosition(s, OperatorV.values());
	System.out.println(pos);

	System.out.println(LocalDateTime.now());
	System.out.println("******************************************");
    }

}
