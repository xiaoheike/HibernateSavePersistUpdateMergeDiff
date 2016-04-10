/* 
 * @(#)HibernateSaveOrUpdateExample.java    Created on 2016年4月10日
 * Copyright (c) 2016. All rights reserved.
 */
package nd.esp.com.hibernate.example;

import nd.esp.com.hibernate.model.Address;
import nd.esp.com.hibernate.model.Employee;
import nd.esp.com.hibernate.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateSaveOrUpdateExample {
	public static void main(String[] args) {
		// Prep Work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		System.out.println("***********************************************");
		// saveOrUpdate example - without transaction
		Session session5 = sessionFactory.openSession();
		Employee emp5 = getTestEmployee();
		session5.saveOrUpdate(emp5);
		System.out.println("***********************************************");

		// saveOrUpdate example - with transaction
		Session session3 = sessionFactory.openSession();
		Transaction tx3 = session3.beginTransaction();
		Employee emp3 = getTestEmployee();
		session3.saveOrUpdate(emp3);
		emp3.setName("Kumar"); // will be saved into DB
		System.out.println("9. Before committing saveOrUpdate transaction. Id=" + emp3.getId());
		tx3.commit();
		System.out.println("10. After committing saveOrUpdate transaction");
		System.out.println("***********************************************");

		Transaction tx4 = session3.beginTransaction();
		emp3.setName("Updated Test Name"); // Name changed
		emp3.getAddress().setCity("Updated City");
		session3.saveOrUpdate(emp3);
		emp3.setName("Kumar"); // again changed to previous value, so no Employee update
		System.out.println("11. Before committing saveOrUpdate transaction. Id=" + emp3.getId());
		tx4.commit();
		System.out.println("12. After committing saveOrUpdate transaction");
		System.out.println("***********************************************");

		// Close resources
		sessionFactory.close();
	}
	public static Employee getTestEmployee() {
		Employee emp = new Employee();
		Address add = new Address();
		emp.setName("Test Emp");
		add.setCity("Test City");
		emp.setAddress(add);
		add.setEmployee(emp);
		return emp;
	}
}
