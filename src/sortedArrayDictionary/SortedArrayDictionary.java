package sortedArrayDictionary;

import java.util.Arrays;
import java.util.Iterator;

public class SortedArrayDictionary<K extends Comparable<? super K>, V>
		implements DictionaryADT<K, V> {

	private Entry<K, V>[] dictionary;
	private int numberOfEntries;
	private static final int DEFAULT_CAPACITY = 25;

	public SortedArrayDictionary() {
		Entry<K, V>[] tempDictionary = new Entry[DEFAULT_CAPACITY];
		dictionary = tempDictionary;
		numberOfEntries = 0;
	}

	@Override
	public V add(K key, V value) {

		V result = null;
		int keyIndex = locateIndex(key);

		// Check if the key is already in the dictionary. If there, return the
		// old value for the key,
		// and replace the value for the key with the new value
		if ((keyIndex < numberOfEntries)
				&& key.equals(dictionary[keyIndex].getKey())) {
			result = dictionary[keyIndex].getValue();
			dictionary[keyIndex].setValue(value);
		}
		// if it's not in the dictionary, do the following:
		else {
			ensureCapacity();
			makeRoom(keyIndex);
			dictionary[keyIndex] = new Entry<K, V>(key, value);
			numberOfEntries++;
		}
		return result;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getValue(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(K key) {
		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<V> getValueIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	@Override
	public int getSize() {
		return numberOfEntries;
	}

	@Override
	public void clear() {

		Entry<K, V>[] tempDictionary = new Entry[DEFAULT_CAPACITY];
		dictionary = tempDictionary;
		numberOfEntries = 0;
	}

	private int locateIndex(K key) {
		// search until you either find entry containing key
		// or pass the point where it should be

		int index = 0;
		while ((index < numberOfEntries)
				&& key.compareTo(dictionary[index].getKey()) > 0) {
			index++;
		}
		return index;
	}

	// check if you have to expand the array
	private void ensureCapacity() {
		if (numberOfEntries == dictionary.length) {
			dictionary = Arrays.copyOf(dictionary, 2 * dictionary.length);
		}
	}

	// make an empty place for the new position by shifting the array forward.
	private void makeRoom(int newPosition) {
		for (int position = numberOfEntries; position >= newPosition; position--) {
			dictionary[position] = dictionary[position - 1];
		}
	}

	// locateIndex() and BinarySearch() if I have to optimize the locateIndex
	// private int locateIndex(K key) {
	// return binarySearch(0, numberOfEntries - 1, key);
	// }
	//
	// private int binarySearch(int first, int last, K target) {
	// int location = -1;
	// int mid = first + (last - first) / 2;
	// if (first > last) {
	// location = first;
	// } else if (target.equals(dictionary[mid].getKey())) {
	// location = mid;
	// } else if (target.compareTo(dictionary[mid].getKey()) < 0) {
	// location = binarySearch(first, mid - 1, target);
	// } else {
	// location = binarySearch(mid + 1, last, target);
	// }
	// return location;
	// }

	// Entry<S,T> inner class
	private class Entry<S, T> {
		private S key;
		private T value;

		private Entry(S searchKey, T dataValue) {
			key = searchKey;
			value = dataValue;
		}

		private S getKey() {
			return key;
		}

		private T getValue() {
			return value;
		}

		private void setValue(T newValue) {
			value = newValue;
		}
	}

}
