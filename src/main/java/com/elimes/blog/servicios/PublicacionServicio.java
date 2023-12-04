/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elimes.blog.servicios;

import com.elimes.blog.dto.PublicacionDto;
import com.elimes.blog.dto.PublicacionRespuesta;


/**
 *
 * @author usuario
 */
public interface PublicacionServicio {
    
    public PublicacionDto crearPublicacuion( PublicacionDto publicacionDto);

    public PublicacionRespuesta listarPublicaciones(int numPagina, int publicacionesPorPagina, String oredenaPor, String desAsc);
    
    public PublicacionDto obtenerPublicacionesId(Long id);
    
    public PublicacionDto actualizarPublicacion(PublicacionDto publicacionDto, Long id);
    
    public void eliminarPublicacion(Long id);
}