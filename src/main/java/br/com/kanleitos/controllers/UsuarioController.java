package br.com.kanleitos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kanleitos.models.Usuario;
import br.com.kanleitos.repository.UsuarioRepository;
import br.com.kanleitos.util.Response;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("/login")
	public @ResponseBody ResponseEntity<Response<Long>> login(@RequestBody Usuario user) {

		Response<Long> response = new Response<Long>();

		// Encode senha
		user.encodeSenha();

		Usuario userDataBase = usuarioRepository.findByLogin(user.getLogin());
		boolean usuarioValidado = userDataBase != null ? userDataBase.getSenha().equals(user.getSenha()) : false;

		// Montando resposta

		response.setData(user.getIdUsario());
		return ResponseEntity.ok(response);
	}

	@PostMapping("/usuario")
	public @ResponseBody ResponseEntity<Response<Long>> criarUsuario(@RequestBody Usuario user) {

		Response<Long> response = new Response<Long>();

		// Encode senha
		user.encodeSenha();

		usuarioRepository.save(user);
		// Montando resposta

		response.setData(user.getIdUsario());
		return ResponseEntity.ok(response);

	}

}
