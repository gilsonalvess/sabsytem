package br.com.ads.dwpuc.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String role;

    @Column
    private Boolean desativado = false;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDesativado() {
        return desativado;
    }

    public void setDesativado(Boolean Desativado) {
        this.desativado = Desativado;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        if (role.equals("ROLE_ADMIN")){
            this.role = "Usuário admin";
        }else{
            this.role = "Usuário padrão";
        }
        return role;
    }

    public void setRole(String role) {
        if (role.equals("Usuário admin")){
            this.role = "ROLE_ADMIN";
        }else{
            this.role = "ROLE_USER";
        }
    }
}
