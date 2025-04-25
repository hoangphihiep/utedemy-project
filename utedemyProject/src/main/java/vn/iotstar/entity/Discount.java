package vn.iotstar.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.JoinColumn;
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
@Table(name="discount")
@NamedQuery(name="Discount.findAll", query="SELECT d from Discount d")
public class Discount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "dis_code")
	private String disCode;
	
	@Column(name = "decreased_fee")
	private String decreasedFee;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "code_amount")
	private String codeAmount;
	
	@Column(name = "type")
	private String type;
	
	@ManyToMany
    @JoinTable(
        name = "discount_course",
        joinColumns = @JoinColumn(name = "discount_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> appliedCourses = new HashSet<>();
	
	@ManyToMany
    @JoinTable(
        name = "user_discount",
        joinColumns = @JoinColumn(name = "discount_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> usedByUsers = new HashSet<>();
}
