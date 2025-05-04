package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="notification")
@NamedQuery(name="Notification.findAll", query="SELECT n from Notification n")
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "content")
    private String content;
	
	@Column(name = "sent_date")
    private String sentDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sender_id")
	private User sender;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "receiver_id")
	private User receiver;
}