package vn.iotstar.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "section")
@Table(name="lesson")
@NamedQuery(name="Lesson.findAll", query="SELECT l from Lesson l")
public class Lesson implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title", length = 255)
	private String title;
	
	@Column(name = "description", length = 1000)
    private String description;
    
	@Column(name = "videoUrl", length = 1000)
    private String videoUrl;
	
	@Column(name = "number_item")
    private int numberItem;
	
	@ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
	
	@OneToOne(mappedBy = "lesson")
	private LessonProgress lessonProgress;
	
}
