package net.MyCloud.cloud.model;

import javax.persistence.*;

@Entity
@Table(name = "data")
public class Data {

    public Data(){}

    public Data(String name, Long user_id, String type, long size){
        this.name = name;
        this.user_id = user_id;
        this.type = type;
        this.size = size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "user_id")
    Long user_id;

    @Column(name = "type")
    String type;

    @Column(name = "size")
    Long size;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

}
