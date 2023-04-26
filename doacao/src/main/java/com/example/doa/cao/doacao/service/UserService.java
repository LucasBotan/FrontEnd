package com.example.doa.cao.doacao.service;

import com.example.doa.cao.doacao.exception.AuthenticationException;
import com.example.doa.cao.doacao.models.ERole;
import com.example.doa.cao.doacao.models.Role;
import com.example.doa.cao.doacao.models.User;
import com.example.doa.cao.doacao.payload.request.LoginRequest;
import com.example.doa.cao.doacao.payload.request.RegisterRequest;
import com.example.doa.cao.doacao.payload.response.AuthenticateResponse;
import com.example.doa.cao.doacao.payload.response.RegisterResponse;
import com.example.doa.cao.doacao.repository.RoleRepository;
import com.example.doa.cao.doacao.repository.UserRepository;
import com.example.doa.cao.doacao.security.jwt.JwtUtils;
import com.example.doa.cao.doacao.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public RegisterResponse register(RegisterRequest signUpRequest) {

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setPhone(signUpRequest.getPhone());
        user.setBirth(signUpRequest.getBirth());
        user.setGender(signUpRequest.getGender());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);

        userRepository.save(user);
       var refreshToken =  refreshTokenService.createRefreshToken(user.getId());

//        securityService.autologin(user.getEmail(), user.getPassword());
        return RegisterResponse
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .birth(user.getBirth())
                .gender(user.getGender())
                .role(user.getRoles().stream().map(Role::getName).map(Enum::name).collect(Collectors.toSet()))
                .refreshToken(refreshToken.getToken())
                .build();

    }

    public AuthenticateResponse authenticate(LoginRequest loginRequest) {
        authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        var user = userRepository.findById(userDetails.getId()).orElseThrow();

        return AuthenticateResponse
                .builder()
                .id(userDetails.getId())
                .email(userDetails.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .birth(user.getBirth())
                .gender(user.getGender())
                .roles(roles)
                .token(jwt)
                .build();
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("Parece que o usuário está desativado", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Usuário ou senha parecem inválidos", e);
        }
    }
}
