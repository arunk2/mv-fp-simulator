package com.iisc.simulator;

import java.util.LinkedHashMap;
import java.util.Map;

//Full Associative Cache implementation
//Tag is the entire Physical address for Simplicity - later last digit (block info) is obtained to retrieve data
//Block size - 8

public class Cache<K, V> {

    private final Map<K,V> cacheMap;
    private Integer hits = 0;
    private Integer misses = 0;
    private Integer replacements = 0;
    private MultivalueMemory multivalueMemory;

    public Cache(final int cacheSize) {

        // true = use access order instead of insertion order.
        this.cacheMap = new LinkedHashMap<K, V>(cacheSize, 0.75f, true) {
        	
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                // When to remove the eldest entry.
                return size() > cacheSize; // Size exceeded the max allowed.
            }
        };
    }

    public synchronized void put(K key, V elem) {
        cacheMap.put(key, elem);
        System.out.println("Adding to cache - "+key+" - "+elem);
    }

    public synchronized V get(K address) {
//    	System.out.println("get From cache - "+address);
    	
    	if (cacheMap.containsKey(address)) {
    		hits++;
//    		System.out.println("Hit - "+hits);
    	}
    	else {
    		misses++;
    		loadBlock(address);
    		System.out.println("Miss - "+misses);
    	}
        return cacheMap.get(address);
        
    }

    private void loadBlock(K key) {
    	V value = (V) multivalueMemory.getValue(key);
    	this.put(key, value);
		replacements++;
	}

	public synchronized V atomicGetAndSet(K key, V elem) {
        V result = get(key);
        put(key, elem);
        return result;
    }

	public MultivalueMemory getMultivalueMemory() {
		return multivalueMemory;
	}

	public void setMultivalueMemory(MultivalueMemory multivalueMemory) {
		this.multivalueMemory = multivalueMemory;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public Integer getMisses() {
		return misses;
	}

	public void setMisses(int misses) {
		this.misses = misses;
	}

	public Integer getReplacements() {
		return replacements;
	}

	public void setReplacements(int replacements) {
		this.replacements = replacements;
	}

	public Map<K, V> getCacheMap() {
		return cacheMap;
	}
}