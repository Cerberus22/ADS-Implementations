package weblab;

import java.util.*;

class Solution {

  /**
   * Sorts the list of student IDs as defined in the description.
   * @param studentIds - list of student IDs
   * @return sorted list of student IDs
   */
  static List<String> sortStudentId(List<String> SID) {
    if (SID == null) {return null;}
    if (SID.isEmpty()) {return new ArrayList<String>();}
    return radix(SID, 0);  
  }

  static List<String> radix(List<String> SID, int sort_idx) {
    if (SID.isEmpty()) {return new ArrayList<String>();}

    if (SID.size() == 1) {
      List<String> l = new ArrayList<>();
      l.add(SID.get(0));  
      return l;
    }

    List<List<String>> buckets = new ArrayList<>();
    for (int i = 0; i < 26; i++) {
      buckets.add(new ArrayList<>());
    }

    for (String s : SID) {
      buckets.get(25 - getVal(s.charAt(sort_idx))).add(s);
    }
    
    for (int bidx = 0; bidx < buckets.size(); bidx++) {
      buckets.set(bidx, radix(buckets.get(bidx), sort_idx + 1));
    }

    List<String> res = new ArrayList<>();
    for (List<String> bucket : buckets) {
      for (String s : bucket) {
        res.add(s);
      }
    }
    return res;
  }

  private static int getVal(char c) {
    if (c=='0'||c=='A') {return 0;}
    if (c=='1'||c=='B') {return 1;}
    if (c=='2'||c=='C') {return 2;}
    if (c=='3'||c=='D') {return 3;}
    if (c=='4'||c=='E') {return 4;}
    if (c=='5'||c=='F') {return 5;}
    if (c=='6'||c=='G') {return 6;}
    if (c=='7'||c=='H') {return 7;}
    if (c=='8'||c=='I') {return 8;}
    if (c=='9'||c=='J') {return 9;}
    if (c=='K') {return 10;}
    if (c=='L') {return 11;}
    if (c=='M') {return 12;}
    if (c=='N') {return 13;}
    if (c=='O') {return 14;}
    if (c=='P') {return 15;}
    if (c=='Q') {return 16;}
    if (c=='R') {return 17;}
    if (c=='S') {return 18;}
    if (c=='T') {return 19;}
    if (c=='U') {return 20;}
    if (c=='V') {return 21;}
    if (c=='W') {return 22;}
    if (c=='X') {return 23;}
    if (c=='Y') {return 24;}
    if (c=='Z') {return 25;}
    return -1;
  }
}
