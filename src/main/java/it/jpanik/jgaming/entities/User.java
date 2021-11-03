package it.jpanik.jgaming.entities;

import it.jpanik.jgaming.enums.UserRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.StringJoiner;

/**
 * @author Jacopo Korompay
 */
@Entity
@SequenceGenerator(name = "USER_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "USER_SEQ")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQUENCE_GENERATOR")
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column
  private String presentation;

  @Column(name = "ACCOUNT_NOT_EXPIRED")
  private boolean accountNonExpired;

  @Column(name = "ACCOUNT_NOT_LOCKED")
  private boolean accountNonLocked;

  @Column(name = "CREDENTIAL_NOT_EXPIRED")
  private boolean credentialsNonExpired;

  @Column(name = "ENABLED")
  private boolean enabled;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private UserRole role;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPresentation() {
    return presentation;
  }

  public void setPresentation(String presentazione) {
    this.presentation = presentazione;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void enableUser() {
    this.setAccountNonExpired(true);
    this.setAccountNonLocked(true);
    this.setCredentialsNonExpired(true);
    this.setEnabled(true);
  }

  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("username='" + username + "'")
            .add("email='" + email + "'")
            .add("password='" + password + "'")
            .add("presentation='" + presentation + "'")
            .add("accountNonExpired=" + accountNonExpired)
            .add("accountNonLocked=" + accountNonLocked)
            .add("credentialsNonExpired=" + credentialsNonExpired)
            .add("enabled=" + enabled)
            .add("role=" + role)
            .toString();
  }
}
