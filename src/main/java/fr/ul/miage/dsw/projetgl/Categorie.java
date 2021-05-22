package fr.ul.miage.dsw.projetgl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Categorie extends Item {

	private List<Item> cat;
	private String nom;
	// categorieMere:
	private Categorie categorieMere;

	
	
	public Categorie(String nom) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
	}

	public List<Categorie> getContent() {
		return null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Categorie getCategorieMere() {
		return categorieMere;
	}

	public void setCategorieMere(Categorie categorieMere) {
		this.categorieMere = categorieMere;
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

	public boolean add(Item e) {
		// TODO Auto-generated method stub
		return false;
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

}
