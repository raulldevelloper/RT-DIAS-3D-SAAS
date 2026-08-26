package com.saas.rtdias3d.entitys;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String email;
    private String senha;

    @Column(name = "margem_lucro_padrao", precision = 10, scale = 2, nullable = false)
    private BigDecimal margemLucroPadrao;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PecaEntity> pecas;

    // ---- Controle de assinatura ----

    @Column(name = "assinatura_ativa", nullable = false)
    private boolean assinaturaAtiva = true;

    @Column(name = "assinatura_expira_em", nullable = false)
    private LocalDateTime assinaturaExpiraEm;

    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin; // null até o primeiro login

    // ---- Métodos exigidos pela interface UserDetails ----

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_VENDEDOR"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // conta "não expirada" = assinatura ainda dentro do prazo
        return assinaturaExpiraEm != null && assinaturaExpiraEm.isAfter(LocalDateTime.now());
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
        // "desligado" manualmente por você, independente da data
        return assinaturaAtiva;
    }
}
