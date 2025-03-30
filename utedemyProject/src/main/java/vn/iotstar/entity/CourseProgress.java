package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
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
@Table(name="course_progress")
@NamedQuery(name="CourseProgress.findAll", query="SELECT cp from CourseProgress cp")
public class CourseProgress implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	@ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
	
	@Column(name = "progress_percentage")
	private double progressPercentage;
	
	@Column(name = "start_date")
    private Date startDate;

	@Column(name = "complete_date")
    private Date completeDate;
    
	@Column(name = "is_completed")
    private boolean isCompleted;
	
	@OneToMany(mappedBy = "courseProgress", cascade = CascadeType.ALL)
    private Set<LessonProgress> lessonProgresses = new HashSet<>();
}
