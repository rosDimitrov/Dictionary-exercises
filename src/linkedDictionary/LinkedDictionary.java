package linkedDictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDictionary<K, V> implements DictionaryADT<K, V> {

	private Node head;
	private int numberOfEntries;

	public LinkedDictionary() {
		head = null;
		numberOfEntries = 0;
	}

	@Override
	public V add(K key, V value) {
		V result = null;
		Node temp = head;
		boolean found = false;

		while (temp != null && !found) {
			if (temp.getKey().equals(key)) {
				result = temp.getValue();
				temp.setValue(value);
				found = true;
				numberOfEntries++;
			}
			temp = temp.next;
		}

		if (found == false) {
			Node toAdd = new Node(key, value);
			toAdd.setNext(head);
			head = toAdd;
			result = toAdd.getValue();
			numberOfEntries++;
		}
		return result;
	}

	@Override
	public V remove(K key) {

		V result = null;
		Iterator<K> iterator = getKeyIterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(key)) {
				result = getValue(key);
			}
		}
		return result;

	}

	@Override
	public V getValue(K key) {

		Node temp = head;

		while (temp.next != null) {
			if (temp.key.equals(key)) {
				return temp.value;
			}
		}
		return null;

	}

	@Override
	public boolean contains(K key) {
		Iterator<K> iterator = getKeyIterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(key)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
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

		Node newNode = new Node(null, null);
		head = newNode;
		numberOfEntries = 0;

	}

	private class ValueIterator implements Iterator<V> {
		private Node nextNode;

		private ValueIterator() {
			nextNode = head;
		}

		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public V next() {
			V result;
			if (hasNext()) {
				result = nextNode.getValue();
				nextNode = nextNode.getNext();
			} else {
				throw new NoSuchElementException();
			}
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class KeyIterator implements Iterator<K> {
		private Node nextNode;

		private KeyIterator() {
			nextNode = head;
		}

		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public K next() {
			K result;
			if (hasNext()) {
				result = nextNode.getKey();
				nextNode = nextNode.getNext();
			} else {
				throw new NoSuchElementException();
			}
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class Node {
		private K key;
		private V value;
		private Node next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

}
