package hw3;

import java.util.Map;

public class SearchTime {
	public static int searchtimes(String s, Map<String, Integer> map){
		if(map.containsKey(s)){
			map.put(s, map.get(s)+1);
		}else{
			map.put(s, 1);
		}
		
		return map.get(s);
	}
}
