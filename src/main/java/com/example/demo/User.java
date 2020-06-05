package com.example.demo;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name="users_db")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column (name = "username")
    @NotEmpty
    public String username;

    @Column (name = "password")
    public String password;

    @Column (name = "email")
    @NotEmpty
    private String email;

    @Column (name = "first_name")
    @NotEmpty
    private String firstName;

    @Column (name = "last_name")
    @NotEmpty
    private String lastName;

    @Column (name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public Set<Message> messages;

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public User() {
    }

    public User(@NotEmpty String username, String password, @NotEmpty String email, @NotEmpty String firstName, @NotEmpty String lastName, boolean enabled) {
        this.username = username;
        setPassword(password);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
