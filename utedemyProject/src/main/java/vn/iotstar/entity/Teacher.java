package vn.iotstar.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
@NamedQuery(name="Teacher.findAll", query="SELECT t from Teacher t")
public class Teacher extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "taxCode")
	private String taxCode;
	
	@Column(name = "identityCard")
	private String identityCard;
	
	@Column(name = "frontIdentityUrl")
	private String frontIdentityUrl;
	
	@Column(name = "backIdentityUrl")
	private String backIdentityUrl;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "socialUrl")
	private String socialUrl; 
	
	@Column(name = "bankAccountNumber")
	private String bankAccountNumber; 
	
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Course> course = new HashSet<>();

}
