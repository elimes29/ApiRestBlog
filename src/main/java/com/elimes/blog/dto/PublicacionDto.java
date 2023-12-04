package com.elimes.blog.dto;

/*
 Objeto de trasferencia de datos (DTO)
no hace contacto alguno con la base de datos
 */
public class PublicacionDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private String contenido;

    public PublicacionDto() {
    }

    public PublicacionDto(Long id, String titulo, String descripcion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

}
