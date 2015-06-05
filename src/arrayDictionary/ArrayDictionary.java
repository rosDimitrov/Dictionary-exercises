package arrayDictionary;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDictionary<K, V> implements DictionaryADT<K, V> {

	private Entry<K, V>[] dictionary;
	private int numberOfEntries;
	private static final int DEFAULT_CAPACITY = 25;

	public ArrayDictionary() {
		@SuppressWarnings("unchecked")
		Entry<K, V>[] temp = new Entry[DEFAULT_CAPACITY];
		dictionary = temp;
		numberOfEntries = 0;
	}

	@Override
	public V add(K key, V value) {
		V result = null;

		// this is the index in the array where the Entry will be saved
		int keyIndex = locateIndex(key);

		if (keyIndex < numberOfEntries) {
			// we have this key already and will replace the old value

			result = dictionary[keyIndex].getValue();
			dictionary[keyIndex].setValue(value);
		} else {
			// else is if we are adding a new element
			ensureCapacity();
			dictionary[numberOfEntries] = new Entry<K, V>(key, value);
			numberOfEntries++;
		}
		return result;

	}

	// find the index of a key
	// if there is no key found, it returns the index of the next element to add
	private int locateIndex(K key) {
		int index = 0;
		while ((index < numberOfEntries)
				&& !key.equals(dictionary[index].getKey())) {
			index++;
		}
		return index;
	}

	private void ensureCapacity() {
		if (numberOfEntries == dictionary.length) {
			dictionary = Arrays.copyOf(dictionary, 2 * dictionary.length);
		}
	}

	@Override
	public V remove(K key) {
		V result = null;
		int keyIndex = locateIndex(key);

		if (keyIndex < numberOfEntries) {
			result = dictionary[keyIndex].getValue();
			dictionary[keyIndex] = dictionary[numberOfEntries - 1];
			numberOfEntries--;
		}
		return result;

	}

	@Override
	public V getValue(K key) {

		for (int i = 0; i < numberOfEntries; i++) {
			if (dictionary[i].getKey().equals(key)) {
				return dictionary[i].getValue();
			}
		}
		return null;

	}

	@Override
	public boolean contains(K key) {
		for (int i = 0; i < numberOfEntries; i++) {
			if (dictionary[i].getKey().equals(key)) {
				return true;
			}
		}
		return false;

	}

	@Override
	public Iterator<K> getKeyIterator() {
		return new KeyIterator(dictionary);
	}

	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator(dictionary);
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

	private class KeyIterator implements Iterator<K> {

		private int position;
		private Entry<K, V>[] array;

		public KeyIterator(Entry<K, V>[] array) {
			this.array = array;
			position = 0;

		}

		@Override
		public boolean hasNext() {

			if (position >= array.length || array[position] == null) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public K next() {
			if (hasNext()) {
				K item = array[position].getKey();
				position += 1;
				return item;
			} else {
				throw new NoSuchElementException(
						"Illegal call to next(); iterator is after end of list");
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private class ValueIterator implements Iterator<V> {

		private int position;
		private Entry<K, V>[] array;

		public ValueIterator(Entry<K, V>[] array) {
			this.array = array;
			position = 0;

		}

		@Override
		public boolean hasNext() {

			if (position >= array.length || array[position] == null) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public V next() {
			if (hasNext()) {
				V item = array[position].getValue();
				position += 1;
				return item;
			} else {
				throw new NoSuchElementException(
						"Illegal call to next(); iterator is after end of list");
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
