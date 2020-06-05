package com.example.demo;
import javax.persistence.*;

@Entity
@Table (name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name = "username")
    private String username;

    @Column (name = "role")
    private String role;

    public Role() {
    }

    public Role(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
