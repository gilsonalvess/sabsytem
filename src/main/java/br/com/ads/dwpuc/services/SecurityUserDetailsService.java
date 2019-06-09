package br.com.ads.dwpuc.services;

import br.com.ads.dwpuc.models.Usuario;
import br.com.ads.dwpuc.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@EnableConfigurationProperties(JpaProperties.class)
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if(usuario == null){
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		Set<GrantedAuthority> perfis = new HashSet<GrantedAuthority>();
		perfis.add(new SimpleGrantedAuthority(usuario.getRole()));
		
		return new User(usuario.getUsername(), usuario.getPassword(), perfis);
	}

}