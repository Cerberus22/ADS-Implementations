package weblab;

import java.util.*;

public class Solution {

    /**
     * Counts the number of occurrences of substring of length k in string s.
     *
     * @param s
     *     The String in which to look for substrings.
     * @param k
     *     The length of the substrings to look for.
     * @return Map with entries in the form of (sub, numOcc), where sub denotes a substring with numOcc occurrences in s.
     * Note: There should be no entries in the map for substrings that do not occur in string s.
     */
    public static Map<String, Integer> countSubstringOccurrences(String s, int k) {
        Map<String, Integer> res = new HashMap<String, Integer>();
        if (k > s.length()) {return res;}
		
        for (int i = 0; i <= s.length() - k; i++) {
            String substring = s.substring(i, i+k);
            if (res.get(substring) == null) {
                res.put(substring, 1);
            } else {
                res.put(substring, res.get(substring) + 1);
            }
        }
        return res;
    }

    /**
     * Counts the occurrences of substrings (of **all lengths**) in a given string s.
     * Should make use of the method countSubstringOccurrences(String s, int k).
     *
     * @param s
     *     The String in which to look for substrings.
     * @return Map with entries in the form of (sub, numOcc), where sub denotes a substring with numOcc occurrences in s.
     * Note 1: There should be no entries in the map for substrings that do not occur in string s.
     * Note 2: When iterating through the returned map, the substrings should be found in increasing order of their length.
     */
    public static Map<String, Integer> countSubstringOccurrences(String s) {
        Map<String, Integer> res = new LinkedHashMap<String, Integer>();
        for (int i = 1; i <= s.length(); i++) {
            res.putAll(countSubstringOccurrences(s,i));
        }
        return res;
    }

    /**
     * Simplifies a given map of substrings and their occurrences by removing substrings that do not repeat.
     * Must be implemented in-place.
     *
     * @param substringsMap
     *     The map from which substrings that do not repeat (occur only once) are to be removed.
     */
    public static void repeatedSubstrings(Map<String, Integer> substringsMap) {
        Object[] keys = substringsMap.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            if (substringsMap.get((String)keys[i]) == 1) {
                substringsMap.remove((String)keys[i]);
            }
        }
    }
}
