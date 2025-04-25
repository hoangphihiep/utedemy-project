package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

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
@ToString(exclude = {"createdBy"})
@Table(name="review")
@NamedQuery(name="Review.findAll", query="SELECT r from Review r")
public class Review implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "rate")
    private int rate;
	
	@Column(name = "content")
    private String content;
	
	@ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User createdBy;
	
	@Column(name = "created_at")
    private Date createdAt;

}
