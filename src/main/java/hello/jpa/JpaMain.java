package hello.jpa;

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
		// 추가
		Member member = new Member();
		member.setId(1L);
		member.setName("helloA");
		em.persist(member); // 저장

		tx.commit(); // 트랜잭션 커밋

		// 꼭 닫아줘야함
		em.close();
		emf.close();
	}
}
