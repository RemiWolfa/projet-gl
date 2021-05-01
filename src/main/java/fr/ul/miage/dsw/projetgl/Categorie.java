package fr.ul.miage.dsw.projetgl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Categorie implements List<item> {

	private String nom;

	// categorieMere:
	private Categorie categorieMere;

	public List<item> getContent() {
		return categorieMere;
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

	public Iterator<item> iterator() {
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

	public boolean add(item e) {
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

	public boolean addAll(Collection<? extends item> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(int index, Collection<? extends item> c) {
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

	public item get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public item set(int index, item element) {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(int index, item element) {
		// TODO Auto-generated method stub

	}

	public item remove(int index) {
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

	public ListIterator<item> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<item> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<item> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
