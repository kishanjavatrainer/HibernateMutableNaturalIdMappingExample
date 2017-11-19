package com.infotech.client;

import org.hibernate.Session;

import com.infotech.entities.Book;
import com.infotech.util.HibernateUtil;

public class ReadAndUpdateByNaturalIdTest {

	public static void main(String[] args) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			long bookId = 2L;
			Book book = session.get(Book.class, bookId);
			if(book != null){
				session.beginTransaction();;
				book.setIsbn("933-9730228236");
				book.setTitle("Hibernate Persistence Contexts2");
				session.getTransaction().commit();
			}
			
			System.out.println("----------------------------------");
			System.out.println("Read entity by naturalId...");
			Book book2 = session.byNaturalId(Book.class).using("isbn", "978-9730228236").load();
			System.out.println(book2.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
