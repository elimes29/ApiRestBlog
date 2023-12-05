/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elimes.blog.servicios;

import com.elimes.blog.dto.ComentarioDto;

/**
 *
 * @author usuario
 */
public interface ComentarioServicio {
    
    public ComentarioDto crearComentario(Long publicacionId, ComentarioDto comenterioDto);
    
    
}
