package com.loja.danilo.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author danilo
 */
@SuppressWarnings("all")
@Table(name = "tb_usuario")
@Entity
public class Usuario implements Serializable, UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @OneToOne
    @JoinColumn(name = "id_cliente")
    private ClientePF cliente;

    @ManyToMany
    @JoinTable(name = "tb_usuarios_roles",
    joinColumns = @JoinColumn(name = "id_usuario"),
    inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

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
    public boolean isEnabled() {
        return true;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }
}
