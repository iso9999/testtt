package com.ensa.pfa.web;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.security.MD5Encoder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ensa.pfa.dao.ClientDao;
import com.ensa.pfa.dao.OffreurDao;
import com.ensa.pfa.dao.RoleDao;
import com.ensa.pfa.dao.UsersDao;
import com.ensa.pfa.entities.Client;
import com.ensa.pfa.entities.Image;
import com.ensa.pfa.entities.Info;
import com.ensa.pfa.entities.Offreur;
import com.ensa.pfa.entities.Roles;
import com.ensa.pfa.entities.Users;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;




@Controller
public class MainController {
	
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private OffreurDao offreurDao;
	@Autowired
    protected AuthenticationManager authenticationManager;
	
	
	@RequestMapping("/")
	public String home(Authentication authentication) {
		Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
		String role = roles.iterator().next().getAuthority();
		return "redirect:/"+role+"/home";
	}
	
	@RequestMapping(value="/login")
	public String login(Model model) throws JsonProcessingException {
		List<Users> users= usersDao.findAll();
		JsonArray usernames = new JsonArray();
		JsonArray emails = new JsonArray();
		for( Users u : users) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("username", u.getUsername());
			JsonObject jsonObject2 = new JsonObject();
			jsonObject2.addProperty("email", u.getEmail());

			usernames.add(jsonObject);
			emails.add(jsonObject2);
			
		}
		
		model.addAttribute("usernames", usernames);
		model.addAttribute("emails", emails);

		return "login";
	}
	
	@RequestMapping("/singUp")
	public  String singUp(String info, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Info infoUser = objectMapper.readValue(info, Info.class);
		Users users = new Users();
		Image image = new Image(9, "/img/myImages/default.png", null);
		PasswordEncoder encoder = new MessageDigestPasswordEncoder("MD5");
		if(infoUser.getRole().equals("CLIENT")) {
			
			Client client = new Client(infoUser.getLast(), infoUser.getFirst(), infoUser.getTitle(), infoUser.getAddress(), false, true, infoUser.getCity(), infoUser.getSummary());
			users = new Users(infoUser.getUsername(), infoUser.getEmail(), encoder.encode(infoUser.getPassword()), true);
			client.setImage(image);
			Roles roles = roleDao.findById(infoUser.getRole()).get();
			users.setRole(roles);
			users.setClient(client);
			clientDao.save(client);
			usersDao.save(users);
			
		}
		if(infoUser.getRole().equals("FREELANCER")) {
			Offreur offreur = new Offreur(infoUser.getLast(), infoUser.getFirst(), infoUser.getTitle(), infoUser.getAddress(), infoUser.getSummary(), infoUser.getCity());
			users = new Users(infoUser.getUsername(), infoUser.getEmail(), encoder.encode(infoUser.getPassword()), true);
			offreur.setImage(image);
			Roles roles = roleDao.findById(infoUser.getRole()).get();
			users.setRole(roles);
			users.setOffreur(offreur);
			offreurDao.save(offreur);
			usersDao.save(users);
			
		}
		users.setPassword(infoUser.getPassword());
		authenticateUserAndSetSession(users, request);
		Principal principal = request.getUserPrincipal();
		return "redirect:/ROLE_"+infoUser.getRole()+"/home";
	}
	
	private void authenticateUserAndSetSession(Users user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
	
	
}
