package pl.marekhacieja.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Books.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author"),
    @NamedQuery(name = "Books.deleteAll", query = "DELETE FROM Book b"),
    @NamedQuery(name = "Books.deleteByTitle", query = "DELETE FROM Book b WHERE b.title = :title")
})
@Table(name = "book")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_book")
	private Long id;
	@Column(name = "book_title", nullable = false, length = 50)
	private String title;
	@Column(name = "author", nullable = false, length = 50)
	private String author;
	@Column(name = "book_type", length = 30)
	private String type;
	@Column(name = "publisher", length = 30)
	private String publisher;
	@ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
	private List<Order> orders;

	Book() {
	}

	public Book(String title, String author, String type, String publisher) {
		super();
		this.title = title;
		this.author = author;
		this.type = type;
		this.publisher = publisher;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", type=" + type + ", publisher="
				+ publisher + "]";
	}

}
