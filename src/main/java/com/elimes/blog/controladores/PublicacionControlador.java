/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elimes.blog.controladores;

import com.elimes.blog.dto.PublicacionDto;
import com.elimes.blog.dto.PublicacionRespuesta;
import com.elimes.blog.servicios.PublicacionServicio;
import com.elimes.blog.utilidades.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/publicaciones")
public class PublicacionControlador {

    @Autowired
    private PublicacionServicio publicacionServicio;

    /* PARA PRUEBAS
        @GetMapping()
    public ResponseEntity<?> mensajes() {
        return ResponseEntity.ok(Arrays.asList("primero", "segundo","tercero"));
    }
     */
    @GetMapping()
    public PublicacionRespuesta listarPublicaciones(
            @RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina, 
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int publicacionesPorPagina, 
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenaPor, 
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String desAsc ) {
        return publicacionServicio.listarPublicaciones(numeroDePagina, publicacionesPorPagina, ordenaPor, desAsc);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDto> obtenerPublicacionPorId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(publicacionServicio.obtenerPublicacionesId(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionDto> guardarPublicaion(@RequestBody PublicacionDto publicaionDto) {
        return new ResponseEntity<>(publicacionServicio.crearPublicacuion(publicaionDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDto> actualizarPublicacion(@PathVariable(name = "id") Long id, @RequestBody PublicacionDto publicaionDto) {
        PublicacionDto publicacionRespuesta = publicacionServicio.actualizarPublicacion(publicaionDto, id);
        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") Long id) {
        publicacionServicio.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicación eliminada exitosamente.", HttpStatus.OK);
    }
}
