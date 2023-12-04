package com.elimes.blog.servicios;

import com.elimes.blog.dto.PublicacionDto;
import com.elimes.blog.dto.PublicacionRespuesta;
import com.elimes.blog.entidades.Publicacion;
import com.elimes.blog.excepciones.ResourceNotFoundException;
import com.elimes.blog.repositorios.PublicacionRepositorio;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PubliciacionServicioImpl implements PublicacionServicio {

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    @Override
    public PublicacionDto crearPublicacuion(PublicacionDto publicacionDto) {
        //convertimos de DTO a entidad 
        Publicacion publicacion = mapeaPub(publicacionDto);

        //Creamos en Base de datos y devuelve con id
        Publicacion newPublicacion = publicacionRepositorio.save(publicacion);

        //Convertimos de entidad a DTO
        PublicacionDto publicacionRespuesta = mapeaDto(newPublicacion);

        //devolvemos la publicacion
        return publicacionRespuesta;
    }
    
        @Override
    public PublicacionRespuesta listarPublicaciones(int numPagina, int publicacionesPorPagina, String ordenaPor, String desAsc) {
        //Paginación
        Sort sort = desAsc.equalsIgnoreCase(Sort.Direction.DESC.name())?Sort.by(ordenaPor).descending():Sort.by(ordenaPor).ascending();
        Pageable pageable = PageRequest.of(numPagina, publicacionesPorPagina, sort);
        Page<Publicacion> publicaciones = publicacionRepositorio.findAll(pageable);
        List<Publicacion> listaPublicaciones = publicaciones.getContent();
         
        PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
        List<PublicacionDto> contenido = listaPublicaciones.stream().map(publicacion -> mapeaDto(publicacion)).collect(Collectors.toList());
        publicacionRespuesta.setContenido(contenido);
        publicacionRespuesta.setMedidaPagina(publicaciones.getSize());
        publicacionRespuesta.setNumeroPagina(publicaciones.getNumber());
        publicacionRespuesta.setTotalElementos(publicaciones.getTotalElements());
        publicacionRespuesta.setTotalPaginas(publicaciones.getTotalPages());
        publicacionRespuesta.setUltima(publicaciones.isLast());
         return publicacionRespuesta;
    }


    //convertir PublicionDTO a publicacion
    private PublicacionDto mapeaDto(Publicacion publicacion) {
        PublicacionDto publicacionDto = new PublicacionDto();

        publicacionDto.setTitulo(publicacion.getTitulo());
        publicacionDto.setDescripcion(publicacion.getDescripcion());
        publicacionDto.setContenido(publicacion.getContenido());
        publicacionDto.setId(publicacion.getId());
        return publicacionDto;
    }

    //convertir Publicion a publicacionDTO
    private Publicacion mapeaPub(PublicacionDto publicacionDto) {
        Publicacion publicacion = new Publicacion();

        publicacion.setTitulo(publicacionDto.getTitulo());
        publicacion.setDescripcion(publicacionDto.getDescripcion());
        publicacion.setContenido(publicacionDto.getContenido());
        return publicacion;
    }

    @Override
    public PublicacionDto obtenerPublicacionesId(Long id) {

        Publicacion publicacion = publicacionRepositorio
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        return mapeaDto(publicacion);
    }

    @Override
    public PublicacionDto actualizarPublicacion(PublicacionDto publicacionDto, Long id) {
        Publicacion publicacion = publicacionRepositorio
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        publicacion.setTitulo(publicacionDto.getTitulo());
        publicacion.setDescripcion(publicacionDto.getDescripcion());
        publicacion.setContenido(publicacionDto.getContenido());

        Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);
        return mapeaDto(publicacionActualizada);
    }

    @Override
    public void eliminarPublicacion(Long id) {
        Publicacion publicacion = publicacionRepositorio
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        publicacionRepositorio.delete(publicacion);
    }


}
