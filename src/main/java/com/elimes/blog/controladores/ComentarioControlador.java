package com.elimes.blog.controladores;

import com.elimes.blog.dto.ComentarioDto;
import com.elimes.blog.servicios.ComentarioServicio;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicio comentarioServicio;
    
    @GetMapping("/comentarios")
    public ResponseEntity<?> mensajes() {
        return ResponseEntity.ok(Arrays.asList("primero", "segundo","tercero"));
    }

    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDto> guardarComentario(@RequestBody ComentarioDto comentarioDto, @PathVariable(name = "publicacionId") Long publicacionId) {
        return new ResponseEntity<>(comentarioServicio.crearComentario(publicacionId, comentarioDto), HttpStatus.CREATED);
    }

}
