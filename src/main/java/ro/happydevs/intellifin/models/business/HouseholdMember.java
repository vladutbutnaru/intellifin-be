package ro.happydevs.intellifin.models.business;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Vlad Butnaru
 * @Version: 1.0
 * @Revision: 1
 * @Title: Household Member Entity Bean
 * @Description: Representation of a single member in a household
 * @Links: User, Household
 */
@Entity
@Table(name = "intelli_household_member")
public class HouseholdMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    private boolean deleted;

    private Long userId;
    private Long householdId;

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

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }
}
