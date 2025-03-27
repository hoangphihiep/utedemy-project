package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="target")
@NamedQuery(name="Target.findAll", query="SELECT t from Target t")
public class Target implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;

    @Column(name = "learner_achievements", nullable = false, length = 500)
    private String learnerAchievements;

    @Column(name = "course_learner", nullable = false, length = 500)
    private String courseLearner;
    
    @ManyToOne
    @JoinColumn(name = "course_detail_id", nullable = false)
    private CourseDetail courseDetail;

}
