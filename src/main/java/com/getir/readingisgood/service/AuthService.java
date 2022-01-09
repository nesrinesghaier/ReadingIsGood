package com.getir.readingisgood.service;

import com.getir.readingisgood.config.UserDetailsImpl;
import com.getir.readingisgood.entity.Role;
import com.getir.readingisgood.entity.User;
import com.getir.readingisgood.model.ERole;
import com.getir.readingisgood.model.JwtResponse;
import com.getir.readingisgood.model.LoginRequest;
import com.getir.readingisgood.model.SignUpRequest;
import com.getir.readingisgood.repository.RoleRepository;
import com.getir.readingisgood.repository.UserRepository;
import com.getir.readingisgood.security.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtUtils jwtUtils;


    public JwtResponse authenticateUser(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return JwtResponse.builder().token(jwt).username(userDetails.getUsername()).roles(roles).build();
    }

    public String registerUser(SignUpRequest request) {
        boolean userAlreadyExists = userRepository.existsByUsername(request.getEmail());
        if (userAlreadyExists) {
            return "Error: Email is already in use!";
        }
        User user = User.builder().username(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName()).build();
        Set<String> roleSet = request.getRole();
        Set<Role> roles = new HashSet<>();
        if (roleSet == null) {
            Role r = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found." + roleSet));
            roles.add(r);
        } else {
            roleSet.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found." + roleSet));
                        roles.add(adminRole);
                        break;
                    default:
                        Role customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found." + roleSet));
                        roles.add(customerRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return "User registered successfully!";
    }


}
