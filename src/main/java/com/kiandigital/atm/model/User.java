package com.kiandigital.atm.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "atm_users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;

    public User() {
        super();
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String username, String password) {
        this.firstName = firstName;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Collection<GrantedAuthority> authorities) {
        super();
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 200)
    private String firstName;

    @Column(name = "last_name", length = 200)
    private String lastName;

    @Column(name = "username", unique = true, nullable = false)
    @ColumnTransformer(read = "lower(username)", write = "lower(?)")
    private String username;

    @Column(name = "password", length = 300)
    private String password;

    @Column(name = "is_enabled", nullable = false)
    private boolean enabled;

    @Column(name="failed_login_attempt", nullable = false)
    private Integer failedLoginAttempt;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
