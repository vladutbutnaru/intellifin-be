package ro.happydevs.intellifin.models.business;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 @Author: Vlad Butnaru
 @Version: 1.0
 @Revision: 4
 @Title: Generic Bean
 @Description: Representation of a generic entity
 @Links: none

 */

//TODO: TO BE REMOVED
public class GenericModel {
    private int id;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private boolean deleted;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}
