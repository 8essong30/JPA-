package hello.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		// 어플리케이션 로딩 시점에 딱 하나만 만들어놔야함
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		// 실제 트랜잭션 단위마다 꼭 만들어줘야함
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// 실행 code 작성
		try {

			Member findMember1 = em.find(Member.class, 101L);
			Member findMember2 = em.find(Member.class, 101L);

			System.out.println("result = " + (findMember1 == findMember2)); // true

			tx.commit(); // 트랜잭션 커밋
		} catch (Exception e) { // 예외처리
			tx.rollback();
		} finally {
			// 꼭 닫아줘야함
			em.close();
		}
		emf.close();
	}
}
