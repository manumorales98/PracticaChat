package edu.uclm.esi.videochat.http;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import edu.uclm.esi.videochat.model.Email;
import edu.uclm.esi.videochat.model.Manager;
import edu.uclm.esi.videochat.model.Token;
import edu.uclm.esi.videochat.model.User;
import edu.uclm.esi.videochat.springdao.TokenRepository;
import edu.uclm.esi.videochat.springdao.UserRepository;


@RestController
@RequestMapping("users")
public class UsersController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TokenRepository tokenRepo;
	
	@PostMapping(value = "/login")
	public User login(HttpServletRequest request, @RequestBody Map<String, Object> credenciales) throws Exception {
		JSONObject jso = new JSONObject(credenciales);
		String name = jso.getString("name");
		String pwd = jso.getString("pwd");
		String ip = request.getRemoteAddr();
		User user = userRepo.findByNameAndPwd(name, pwd);
		if (user==null)
			throw new Exception("Incorrect login");
		Manager.get().add(user);
		request.getSession().setAttribute("user", user);
		Manager.get().add(request.getSession());
		return user;
	}
	
	@GetMapping("/login")
		public void recuperarMensajes(HttpServletRequest request, HttpServletResponse response, @RequestParam String Sender) {
			
			
		
	}
	
	@PutMapping("/register")
	public void register(@RequestBody Map<String, Object> credenciales) throws Exception {
		JSONObject jso = new JSONObject(credenciales);
		String name = jso.getString("name");
		String email = jso.getString("email");
		String pwd1 = jso.getString("pwd1");
		String pwd2 = jso.getString("pwd2");
		if (!pwd1.equals(pwd2))
			throw new Exception("Error: las contraseñas no coinciden");
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setPwd(pwd1);
		user.setConfirmationDate(0);
		
		Vector<String> mail = new Vector<String>();
		mail = userRepo.getEmail();
		for (int i = 0; i < mail.size(); i++) {
			if (user.getEmail().equals(mail.get(i)))
				throw new Exception("Error: Este correo ya se ha registrado");
		}
		
		
		userRepo.save(user);
		
		Token token = new Token(email);
		
		Email sender = new Email();
		sender.send(email, token.getId());
		tokenRepo.save(token);
		//sender.send(email, token.getId() );
		
	}
	
	@GetMapping("/confirmarCuenta")
	public void confirmarCuenta(HttpServletRequest request, HttpServletResponse response, @RequestParam String tokenId) {
		// IR A LA BASE DE DATO, buscar el token con ese token id en la tabla, ver que no ha caducado y actualizar la confirmation_Date del user
		long day=86400000;
		long actual=System.currentTimeMillis();
		Optional<Token> tk = tokenRepo.findById(tokenId);
		System.out.println(tokenId);
		User user = new User();
		
		System.out.println(tk.get().getId());
		if((actual-tk.get().getDate())<=day)
			user = userRepo.findByMail(tk.get().getEmail());
			user.setConfirmationDate(actual);
			userRepo.save(user);
		try {
			response.sendRedirect("https://localhost:7500/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GetMapping("/confirmarCuenta2/{tokenId}")
	public void confirmarCuenta2(HttpServletRequest request, HttpServletResponse response, @PathVariable String tokenId) {
		// IR A LA BASE DE DATO, buscar el token con ese token id en la tabla, ver que no ha caducado y actualizar la confirmation_Date del user
		
		System.out.println(tokenId);
		try {
			response.sendRedirect("https://localhost:7500/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@PatchMapping("/cambiarPwd")
	public void cambiarPwd(@RequestBody Map<String, String> credenciales) throws Exception {
		JSONObject jso = new JSONObject(credenciales);
		String name = jso.getString("name");
		String pwd = jso.getString("pwd");
		String pwd1 = jso.getString("pwd1");
		String pwd2 = jso.getString("pwd2");
		if (userRepo.checkPassword(name, pwd) > 0) { 
			if (pwd1.equals(pwd2)) {
				User user = userRepo.findByNameAndPwd(name, pwd);
				user.setPwd(pwd2);
				userRepo.save(user);

			} else throw new Exception("Las paasswords no coinciden");
		} else 
			throw new Exception("Credenciales inválidas");
	}
	
	@GetMapping(value = "/getUsuariosConectados", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getUsuariosConectados() {
		return Manager.get().getUsuariosConectados();
	}
	
	@GetMapping(value = "/getUsuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsuarios() {
		return Manager.get().getUsuarios();
	}
	
	@PostMapping(value="/getPicture", produces = MediaType.APPLICATION_JSON_VALUE)
	public String  getPicture(@RequestBody Map<String, Object> datos) {
		JSONObject jso = new JSONObject(datos);
		
		String name = jso.getString("name");
		
		
		JSONObject jsoResponse = new JSONObject();
		jsoResponse.put("picture", Manager.get().getPicture(name));
		return jsoResponse.toString();
		
	}
	
}
