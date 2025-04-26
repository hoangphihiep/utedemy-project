package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="orders")
@NamedQuery(name="Orders.findAll", query="SELECT o from Orders o")
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;
	
	@Column(name = "order_status")
	private String orderStatus;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	public int calculateTotal() {
		return 0;
	}

}
