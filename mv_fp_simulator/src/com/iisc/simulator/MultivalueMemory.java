package com.iisc.simulator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class MultivalueMemory<K,V> {
	
	private static final int MAIN_MEMORY_SIZE = 32768;
	private final int maxSize = MAIN_MEMORY_SIZE;
	
	private final Map<K,V> values;
	
	public MultivalueMemory(Map<K, V> values) {
        this.values = values;
    }

	public int getMaxSize() {
		return maxSize;
	}

	public Map<K,V> getValues() {
		return values;
	}
	
	public V getValue(K key) {
		if (values.containsKey(key))
			return values.get(key);
		else 
			System.out.println("Block is Missing in Main memory - "+key.toString());
			
		return null;
	}
	
}
