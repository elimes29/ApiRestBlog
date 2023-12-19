/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elimes.blog.servicios;

import com.elimes.blog.dto.ComentarioDto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author usuario
 */
public interface ComentarioServicio {
    
    public ComentarioDto crearComentario(Long publicacionId, ComentarioDto comenterioDto);
    
    @Query("SELECT c FROM Comentarios c WHERE c.publicacionId =:publicacionId")
    public List<ComentarioDto> obtenerComentariosDePublicacionId(Long publicacionId);
    
    
}
