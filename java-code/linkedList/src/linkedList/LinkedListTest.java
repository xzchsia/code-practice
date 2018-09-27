package linkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> a = new LinkedList<>();
		a.add("Amu");
		a.add("Carl");
		a.add("Erica");
		
		List<String> b = new LinkedList<>();
		b.add("Bob");
		b.add("Doung");
		b.add("Frances");
		b.add("Gloria");
		
		System.out.println(a);
		System.out.println(b);
		
		ListIterator<String> aIter = a.listIterator();
		Iterator<String> bIter = b.iterator();
		
		while(bIter.hasNext()) {
			if(aIter.hasNext())
				aIter.next();
			
			aIter.add(bIter.next());
		}
		
		System.out.println(a);
		System.out.println(b);
		
		bIter = b.iterator();
		while(bIter.hasNext()) {
			bIter.next();
			if(bIter.hasNext())
			{
				bIter.next();
				bIter.remove();
			}
		}
		System.out.println(a);
		System.out.println(b);
		
		a.removeAll(b);
		System.out.println(a);
		System.out.println(b);

	}

}
