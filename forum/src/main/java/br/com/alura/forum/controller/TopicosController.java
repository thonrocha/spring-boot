package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

@RestController
public class TopicosController {

	@RequestMapping("/topicos")
	public List<TopicoDTO> lista() {
		Topico t1 = new Topico("topico 2*", "mensagem  1", new Curso("Spring", "Java"));
		
		return TopicoDTO.converter(Arrays.asList(t1,t1,t1));
	}
}
