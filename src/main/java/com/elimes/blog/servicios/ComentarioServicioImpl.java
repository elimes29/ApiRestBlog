
package com.elimes.blog.servicios;

import com.elimes.blog.dto.ComentarioDto;
import com.elimes.blog.entidades.Comentario;
import com.elimes.blog.entidades.Publicacion;
import com.elimes.blog.excepciones.ResourceNotFoundException;
import com.elimes.blog.repositorios.ComentarioRepositorio;
import com.elimes.blog.repositorios.PublicacionRepositorio;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{
    
    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    @Override
    public ComentarioDto crearComentario(Long publicacionId, ComentarioDto comenterioDto) {
        
        Comentario comentario = mapearCom(comenterioDto);
        Publicacion publicacion = publicacionRepositorio
                .findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
        comentario.setPublicacion(publicacion);
        
        Comentario newComentario = comentarioRepositorio.save(comentario);
        return mapearDto(newComentario);
    }
    
    private ComentarioDto mapearDto(Comentario comentario){
        ComentarioDto comentarioDto = new ComentarioDto();
        
        comentarioDto.setCuerpo(comentario.getCuerpo());
        comentarioDto.setEmail(comentario.getEmail());
        comentarioDto.setNombre(comentario.getNombre());
        comentarioDto.setId(comentario.getId());
        
        return comentarioDto;      
    }
    
    private Comentario mapearCom(ComentarioDto comentarioDto){
        Comentario comentario = new Comentario();
        
        comentario.setCuerpo(comentarioDto.getCuerpo());
        comentario.setEmail(comentarioDto.getEmail());
        comentario.setNombre(comentarioDto.getNombre());
        comentario.setId(comentarioDto.getId());
        
        return comentario;      
    }

    @Override
    public List<ComentarioDto> obtenerComentariosDePublicacionId(Long publicacionId) {
        List<Comentario> comentarios = comentarioRepositorio.findByPublicacionId(publicacionId);
        return comentarios.stream().map(comentario -> mapearDto(comentario)).collect(Collectors.toList());
        
    }
}
