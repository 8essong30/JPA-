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
		try {

/*
			// 추가
			Member member = new Member();
			member.setId(1L);
			member.setName("helloA");
			em.persist(member); // 저장
*/

			Member findMember = em.find(Member.class, 1L); // 조회
/*
			System.out.println("findMember.id = "+findMember.getId());
			System.out.println("findMember.name = "+findMember.getName());
*/
/*
			// 삭제
			em.remove(findMember);
*/
/*
			// 수정
			findMember.setName("HelloJPA"); // 따로 저장 안해도 됨
*/


			tx.commit(); // 트랜잭션 커밋
		} catch (Exception e) {
			tx.rollback();
		} finally {
			// 꼭 닫아줘야함
			em.close();
		}
		emf.close();
	}
}
