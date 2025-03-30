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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="course_detail")
@NamedQuery(name="CourseDetail.findAll", query="SELECT cd from CourseDetail cd")
public class CourseDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToMany(mappedBy = "courseDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Target> courseTarget = new HashSet<>();
	
	@Column(name = "description", nullable = false, length = 1000)
	private String description;

	@Column(name = "courseIntroduction", nullable = false, length = 1000)
	private String courseIntroduction;

	@Column(name = "courseImage", nullable = false, length = 255)
	private String courseImage;
	
	@OneToOne(mappedBy = "courseDetail")
    private Course course;
}
