package com.revature.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal amount;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date submitted;
    @Temporal(TemporalType.TIMESTAMP)
    private Date resolved;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resolver_id", referencedColumnName = "id")
    private User resolver;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private ReimbursementStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private ReimbursementType type;

    public Reimbursement(BigDecimal amount, String description, Date submitted, Date resolved, User author, User resolver, ReimbursementStatus status, ReimbursementType type) {
        this.amount = amount;
        this.description = description;
        this.submitted = submitted;
        this.resolved = resolved;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.type = type;
    }

    public Reimbursement(BigDecimal amount, String description, Date submitted, User author, ReimbursementStatus status, ReimbursementType type) {
        this.amount = amount;
        this.description = description;
        this.submitted = submitted;
        this.author = author;
        this.status = status;
        this.type = type;
    }

    public Reimbursement(BigDecimal amount, String description, User author, User resolver, ReimbursementStatus status, ReimbursementType type) {
        this.amount = amount;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.type = type;
    }

    public Reimbursement(BigDecimal amount, String description, User author, ReimbursementStatus status, ReimbursementType type) {
        this.amount = amount;
        this.description = description;
        this.author = author;
        this.status = status;
        this.type = type;
    }

    public Reimbursement(BigDecimal amount, String description, User author, ReimbursementType type) {
        this.amount = amount;
        this.description = description;
        this.author = author;
        this.type = type;
    }

    public Reimbursement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public ReimbursementStatus getStatus() {
        return status;
    }

    public void setStatus(ReimbursementStatus status) {
        this.status = status;
    }

    public ReimbursementType getType() {
        return type;
    }

    public void setType(ReimbursementType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(submitted, that.submitted) && Objects.equals(resolved, that.resolved) && Objects.equals(author, that.author) && Objects.equals(resolver, that.resolver) && Objects.equals(status, that.status) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, description, submitted, resolved, author, resolver, status, type);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", author=" + author +
                ", resolver=" + resolver +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
