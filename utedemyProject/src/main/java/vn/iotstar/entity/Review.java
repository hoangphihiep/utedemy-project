package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name="review")
@NamedQuery(name="Review.findAll", query="SELECT r from Review r")
public class Review implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "rate")
    private String rate;
	
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
