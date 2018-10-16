package com.example.demo.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.user.entity.Role;
import com.example.demo.user.entity.UserLogin;
import com.example.demo.user.repository.RoleRepository;
import com.example.demo.user.repository.UserLoginRepository;

@Service
public class UserLoginServiceImpl implements UserDetailsService, UserLoginService {

	@Autowired
	private UserLoginRepository userLoginRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.demo.user.entity.UserLogin user = userLoginRepository.findByUsername(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

	@Override
	@Transactional
	public void registryUser(UserLogin user, @Autowired PasswordEncoder passwordEncoder) {
		UserLogin newUser = new UserLogin(user.getUsername(), passwordEncoder.encode(user.getPassword()));
		Role role = roleRepository.findByName("ROLE_LEARNER");
		if(role == null) {
			role = new Role("ROLE_LEARNER");
			roleRepository.save(role);
		}
    	List<Role> roles = new ArrayList<>();
    	roles.add(role);
    	newUser.setRoles(roles);
    	userLoginRepository.save(newUser);
	}
}
