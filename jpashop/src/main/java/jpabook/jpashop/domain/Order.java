package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ORDERS")
public class Order {
	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
	// private Long memberId; // X 객체지향에 맞지 않음. 테이블 설계에 맞춘 방법.
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member; // O 외래키가 아닌 객체 자체를 갖고있어야 객체그래프 탐색 가능
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<>(); // 이건 비즈니스적으로 필요한 경우가 많연고나
	private LocalDateTime orderDate;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	// 연관관계 편의 메서드. 양방향 연관관계가 걸리도록
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
}
