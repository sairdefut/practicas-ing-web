package com.brilloestelar.auth.service;

import com.brilloestelar.auth.model.Usuario;
import com.brilloestelar.auth.repository.UsuarioRepository;
import com.brilloestelar.auth.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public String registerUser(Usuario usuario) throws Exception {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new Exception("El nombre de usuario ya está en uso!");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return "Usuario registrado exitosamente";
    }

    public String authenticateUser(String username, String password) throws Exception {
        Optional<Usuario> userOptional = usuarioRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            Usuario user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return tokenProvider.generateToken(user.getUsername());
            }
        }
        throw new Exception("Usuario o contraseña incorrectos");
    }
}
