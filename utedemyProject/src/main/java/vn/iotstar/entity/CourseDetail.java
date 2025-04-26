package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="course_detail")
@NamedQuery(name="CourseDetail.findAll", query="SELECT cd from CourseDetail cd")
public class CourseDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "description", length = 1000)
	private String description;

	@Column(name = "course_introduction",length = 1000)
	private String courseIntroduction;

	@Column(name = "course_image", length = 255)
	private String courseImage;
	
	@Column(name = "course_video", length = 255)
	private String courseVideo;
	
	@Column(name = "learner_achievements", length = 500)
    private String learnerAchievements;

    @Column(name = "course_learner", length = 500)
    private String courseLearner;
	
	@OneToOne(mappedBy = "courseDetail")
    private Course course;
}
