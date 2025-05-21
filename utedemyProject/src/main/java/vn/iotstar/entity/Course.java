package vn.iotstar.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
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
@Table(name="course")
@NamedQuery(name="Course.findAll", query="SELECT c from Course c")
public class Course implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "course_name")
    private String courseName;
	
	@ManyToOne
    @JoinColumn(name = "course_type_id", nullable = false)
    private CourseType courseType;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_detail_id", referencedColumnName = "id")
    private CourseDetail courseDetail;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Section> sections = new HashSet<>();
	
	@Column(name = "course_price")
    private double coursePrice;
	
	@ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Review> review = new HashSet<>();
	
	@ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private Set<FavoriteCourse> favoriteCourse = new HashSet<>();
	
	@ManyToMany(mappedBy = "courses")
    private Set<Voucher> vouchers = new HashSet<>();
	
	@ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private Set<Cart> cart = new HashSet<>();
	
	@ManyToMany(mappedBy = "appliedCourses",fetch = FetchType.EAGER)
    private Set<Discount> discounts = new HashSet<>();
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();
	
	@Column(name = "status")
    private int status;
	
	public void addSection(Section section) {
        if (section != null && section != null && !sections.contains(section)) {
        	sections.add(section);
        }
    }
	
	// Builder class
    public static class Builder {
        private int id;
        private String courseName;
        private CourseType courseType;
        private CourseDetail courseDetail;
        private Set<Section> sections = new HashSet<>();
        private double coursePrice;
        private Teacher teacher;
        private Set<Review> review = new HashSet<>();
        private Set<FavoriteCourse> favoriteCourse = new HashSet<>();
        private Set<Voucher> vouchers = new HashSet<>();
        private Set<Cart> cart = new HashSet<>();
        private Set<Discount> discounts = new HashSet<>();
        private Set<OrderItem> orderItems = new HashSet<>();
        private int status;

        public Builder() {}

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder courseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public Builder courseType(CourseType courseType) {
            this.courseType = courseType;
            return this;
        }

        public Builder courseDetail(CourseDetail courseDetail) {
            this.courseDetail = courseDetail;
            return this;
        }

        public Builder sections(Set<Section> sections) {
            this.sections = sections;
            return this;
        }

        public Builder addSection(Section section) {
            if (section != null) {
                this.sections.add(section);
            }
            return this;
        }

        public Builder coursePrice(double coursePrice) {
            this.coursePrice = coursePrice;
            return this;
        }

        public Builder teacher(Teacher teacher) {
            this.teacher = teacher;
            return this;
        }

        public Builder review(Set<Review> review) {
            this.review = review;
            return this;
        }

        public Builder favoriteCourse(Set<FavoriteCourse> favoriteCourse) {
            this.favoriteCourse = favoriteCourse;
            return this;
        }

        public Builder vouchers(Set<Voucher> vouchers) {
            this.vouchers = vouchers;
            return this;
        }

        public Builder cart(Set<Cart> cart) {
            this.cart = cart;
            return this;
        }

        public Builder discounts(Set<Discount> discounts) {
            this.discounts = discounts;
            return this;
        }

        public Builder orderItems(Set<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Course build() {
            Course course = new Course();
            course.setId(this.id);
            course.setCourseName(this.courseName);
            course.setCourseType(this.courseType);
            course.setCourseDetail(this.courseDetail);
            course.setSections(this.sections);
            course.setCoursePrice(this.coursePrice);
            course.setTeacher(this.teacher);
            course.setReview(this.review);
            course.setFavoriteCourse(this.favoriteCourse);
            course.setVouchers(this.vouchers);
            course.setCart(this.cart);
            course.setDiscounts(this.discounts);
            course.setOrderItems(this.orderItems);
            course.setStatus(this.status);
            return course;
        }
    }
}