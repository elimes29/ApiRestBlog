
package com.elimes.blog.repositorios;

import com.elimes.blog.entidades.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ComentarioRepositorio extends JpaRepository<Comentario, Long>{
    
    public List<Comentario> findByPublicacionId(Long publicacioId);
    
}
