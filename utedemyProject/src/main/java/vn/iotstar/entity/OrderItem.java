package vn.iotstar.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="order_item")
@NamedQuery(name="OrderItem.findAll", query="SELECT oi from OrderItem oi")
public class OrderItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	    name = "orderitem_course",
	    joinColumns = @JoinColumn(name = "orderitem_id"),
	    inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private Set<Course> courses = new HashSet<>();
	
	@Column(name = "finished_fee")
	private double finishedFee;
	
	@ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;
}
