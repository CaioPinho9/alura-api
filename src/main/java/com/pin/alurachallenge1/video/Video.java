package com.pin.alurachallenge1.video;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String titulo;
    private String descricao;
    private String url;

    public Video(String titulo, String descricao, String url) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }

    public Video() {
    }

    public static Video convert(VideoRequest videoRequest) {
        return new Video(videoRequest.getTitulo(), videoRequest.getDescricao(), videoRequest.getUrl());
    }

    public static Video autoComplete(Video oldVideo, Video newVideo) {
        newVideo.setId(oldVideo.getId());
        if (newVideo.getTitulo() == null) {
            newVideo.setTitulo(oldVideo.getTitulo());
        }
        if (newVideo.getDescricao() == null) {
            newVideo.setDescricao(oldVideo.getDescricao());
        }
        if (newVideo.getUrl() == null) {
            newVideo.setUrl(oldVideo.getUrl());
        }
        return newVideo;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}