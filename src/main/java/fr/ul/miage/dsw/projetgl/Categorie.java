package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import fr.ul.miage.dsw.projetgl.database.CategorieCollection;

public class Categorie extends Item {

	public List<Item> categories; //fils
	public String nom;
	// categorieMere:
	public Categorie categorieMere;

	
	
	public Categorie(String nom) {
		this.nom = nom;
		this.categories = new ArrayList<Item>();
	}
	
	public boolean add(Item item) {
		this.categories.add(item);
		return true;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(Collection<? extends Item> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(int index, Collection<? extends Item> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub

	}

	public Item get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public Item set(int index, Item element) {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(int index, Item element) {
		// TODO Auto-generated method stub

	}

	public Item remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ListIterator<Item> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<Item> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Item> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean save() {
		return CategorieCollection.save(this);
	}

}
