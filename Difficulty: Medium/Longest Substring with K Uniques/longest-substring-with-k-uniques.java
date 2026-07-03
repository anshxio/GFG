class Solution {
	public int longestKSubstr(String s, int k) {
		// code here
		int n = s.length();
		int maxLength = -1, l=0, r =0;
		HashMap<Character, Integer> map = new HashMap<>();
		while(r < n){
		    char ch = s.charAt(r);
		    map.put(ch, map.getOrDefault(ch,0)+1);
		    if(map.size() > k){
		        map.put(s.charAt(l), map.get(s.charAt(l))-1);
		        if(map.get(s.charAt(l)) ==0){
		            map.remove(s.charAt(l));
		        }
		        l++;
		    }
		    if(map.size() == k){
		        maxLength = Math.max(maxLength, r-l+1);
		    }
		    r++;
		}
		return maxLength;
	}
}

