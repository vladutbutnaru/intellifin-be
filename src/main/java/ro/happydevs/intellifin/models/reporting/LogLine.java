package ro.happydevs.intellifin.models.reporting;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Vlad Butnaru
 * @Version: 1.0
 * @Revision: 3
 * @Title: Log Line Entity Bean
 * @Description: A class that registers all actions made inside IntelliFin or Third Parties
 * @Links: User
 */

@Entity
@Table(name = "intelli_logs")
public class LogLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt = new Date();

    private boolean deleted;

    private Long userId;

    @Lob
    @Column(length = 10000000)
    private String action;


    public LogLine(Long userId, String action) {
        this.userId = userId;
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
