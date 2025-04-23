package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
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
@Table(name="voucher")
@NamedQuery(name="Voucher.findAll", query="SELECT v from Course v")
public class Voucher implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "voucher_name")
    private String voucherName;
	
	@Column(name = "percentage")
    private Double percentage;
	
	@Column(name = "dateSet")
    private Date dateSet;
	
	@Column(name = "dateEnd")
    private Date dateEnd;
	
	@ManyToMany
    @JoinTable(
        name = "voucher_course",
        joinColumns = @JoinColumn(name = "voucher_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();
}
