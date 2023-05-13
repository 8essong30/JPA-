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
			// 영속
			Member member = em.find(Member.class, 150L);
			member.setName("AAAA");

/*
			em.detach(member); // 준영속상태로 만들어
			// 그러면 업데이트 쿼리 안날라감
*/
			em.clear(); // 영속성컨텍스트 초기화
			// 이래도 업데이트 쿼리 안날라가지
			Member member2 = em.find(Member.class, 150L);
			// 이러면 select쿼리 총 두 번 날라감. (clear()없으면 1차캐시로 한 번 쿼리로 조회하는데..)

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
