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

			// 비영속. 객체를 생성만 한 상태
			Member member = new Member();
			member.setId(100L);
			member.setName("helloJPA");

			// 영속. 객체를 저장한 상태
			System.out.println("========= BEFORE =========");
			em.persist(member); // 저장
			// // 준영속. 영속성 컨텍스트에서 분리
			// em.detach(member);
			// // 삭제. DB삭제를 요청
			// em.remove(member);
			System.out.println("========= AFTER =========");
			// 커밋한 후에 데이터베이스에 처리가 되는거기 떄문에 AFTER 콘솔에 찍힌 후 쿼리 발생

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
