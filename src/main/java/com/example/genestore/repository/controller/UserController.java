package com.example.genestore.repository.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import com.example.genestore.security.jwt.JwtProvider;
import com.example.genestore.service.UserService;
import com.example.genestore.vo.request.LoginForm;
import com.example.genestore.vo.response.JwtResponse;
import com.example.genestore.exception.MyException;
import com.example.genestore.model.User;
import com.example.genestore.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "users")
public class UserController {

	@Autowired
	 private UserRepository userRepository;
	
	@Autowired
   UserService userService;
	
	@Autowired
   JwtProvider jwtProvider;

   @Autowired
   AuthenticationManager authenticationManager;
   
 //  @Autowired
	//PasswordEncoder encoder;
	
	@GetMapping("/get")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/add")
	public void createUser(@RequestBody User user) {
		userRepository.save(user);
	}
	
	@DeleteMapping(path = { "/{id}" })
	public User deleteUser(@PathVariable("id") long id) {
		User user = userRepository.getOne(id);
		userRepository.deleteById(id);
		return user;
	}
	
	@PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginForm loginForm) {
        // throws Exception if authentication failed

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generate(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findOne(userDetails.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt, user.getEmail(), user.getName(), user.getRole()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    } 
	
	 @PostMapping("/register")
	    public ResponseEntity<User> save(@RequestBody User user) {
	        try {
	            return ResponseEntity.ok(userService.save(user));
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().build();
	        }
	    }
	
}
