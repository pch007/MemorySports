package com.jaimerivera.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jaimerivera.memory.Phoneme;

public class PrefixTree<E, D> {

	protected Map<E, PrefixTree<E, D>> children;
	protected List<D> containedData;
	protected int size;
	
	public PrefixTree() {
		this.containedData = null;
		this.children = null;
		this.size = 0;
	}
	
	public boolean containsValue(E[] value) {
		List<D> values = this.get(value);
		return values != null && !values.isEmpty();
	}
	
	/**
	 * 
	 * Includes the sequence of items within <code>value</code> into the tree.
	 * If any indices are null, then the inclusion terminates.
	 * 
	 * @param value array of items that represent the value
	 * @return true if the item existed or was inserted properly.
	 */
	public boolean add(E[] value, D data) {
		if (value == null) {
			return false;
		}
		
		return this.add(value, data, 0);
	}
	
	private boolean add(E[] value, D data, int index) {
		if (index == value.length) {
			this.store(value, data, index);
			return true;
		} else if (value[index] == null) {
			return false;
		}
		
		this.children = (children == null) ? new HashMap<E, PrefixTree<E, D>>() : this.children;

		if (!this.children.containsKey(value[index])) {
			this.children.put(value[index], new PrefixTree<E, D>());
		}
		
		PrefixTree<E, D> child = this.children.get(value[index]);
		boolean success = child.add(value, data, index + 1);
		
		if (success) {
			this.size += child.size;
		}
		
		return success;
	}
	
	protected void store(E[] value, D data, int index) {
		if (this.containedData == null) {
			this.containedData = new ArrayList<D>();
		}
		
		if (!this.containedData.contains(value)) {
			this.containedData.add(data);
			this.size++;
		}		
	}
	
	/**
	 * 
	 * @return a <code>List</code> of <code>D</code> which represents all data
	 * represented within this tree.
	 */
	public List<D> getAllValues() {
		List<D> allDataStored = new ArrayList<D>();
		this.storeAllValues(allDataStored);
		return allDataStored;
	}
	
	private void storeAllValues(List<D> storedData) {
		if (this.containedData != null) {
			storedData.addAll(this.containedData);
		}
		
		if (this.children == null) {
			return;
		}
		
		for (PrefixTree<E, D> tree : this.children.values()) {
			tree.storeAllValues(storedData);
		}
	}
	
	/**
	 * 
	 * @param value array of items that represent the value.
	 * @return <code>List<E></code> of values that correspond to <code>value</code>.
	 */
	public List<D> get(E[] value) {
		PrefixTree<E, D> foundTree = this.getTree(value);
		return (foundTree == null || foundTree.containedData == null) ? new ArrayList<D>() :
				new ArrayList<D>(foundTree.containedData);
	}
	
	public PrefixTree<E, D> getTree(E[] value) {
		return this.getTree(value, 0);
	}
	
	private PrefixTree<E, D> getTree(E[] value, int index) {
		if (index >= value.length) {
			return this;
		} else if (this.children == null || !this.children.containsKey(value[index])) {
			System.out.println("failsauce");
			return null;
		}
		
		return this.children.get(value[index]).getTree(value, index + 1);
	}
	
	/**
	 * 
	 * @param permutations 2-dimensional array of items representing potential values.
	 * @return all of the values that could be represented by permuting the permutations.
	 * Returns null if any 1-dimensional array is null.
	 */
	public List<D> getNested(E[][] permutations) {
		List<PrefixTree<E, D>> trees = this.getNestedTrees(permutations);
		List<D> accumulatedData = new ArrayList<D>();
		
		for (PrefixTree<E, D> tree : trees) {
			accumulatedData.addAll(tree.containedData);
		}
		
		return accumulatedData;
	}
	
	protected List<PrefixTree<E, D>> getNestedTrees(E[][] permutations) {
		List<PrefixTree<E, D>> trees = new ArrayList<PrefixTree<E, D>>();
		this.storeNestedTrees(trees, permutations, 0);
		return trees;
	}
	
	private void storeNestedTrees(List<PrefixTree<E, D>> trees, E[][] permutations, int index) {
		if (index >= permutations.length) {
			trees.add(this);
		} else if (permutations[index] == null) {
			return;
		}
		
		E[] level = permutations[index];
		
		for (int i = 0; i < level.length; i++) {
			if (this.children.containsKey(level[i])) {
				PrefixTree<E, D> child = this.children.get(level[i]);
				child.storeNestedTrees(trees, permutations, index + 1);
			}
		}
	}
	
	public List<D> getContains(Phoneme[] phonemes) {
		List<D> contained = new ArrayList<D>();
		storeContains(contained, phonemes, 0);
		return contained;
	}
	
	private void storeContains(List<D> contained, Phoneme[] phonemes, int index) {
		if (index == phonemes.length) {
			contained.addAll(this.getAllValues());
			return;
		} else if (this.children == null) {
			return;
		}
		
		for (Map.Entry<E, PrefixTree<E, D>> entry : this.children.entrySet()) {
			PrefixTree<E, D> child = entry.getValue();
			E e = entry.getKey();
			
			if (e.equals(phonemes[index])) {
				child.storeContains(contained, phonemes, index + 1);
			} else {
				child.storeContains(contained, phonemes, 0);
			}
		}
	}
	
	/**
	 * 
	 * @return the number of nodes represented within this tree.
	 */
	public int size() {
		return this.size;
	}
}
