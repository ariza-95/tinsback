package com.tutorial.crud.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class FechasDto {
    @NotBlank
    @NotNull
    private Date from;
    @NotBlank
    @NotNull
    private Date until;
    private int id;
    public FechasDto(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }

    public FechasDto(@NotBlank @NotNull Date from, @NotBlank @NotNull Date until) {
        this.from = from;
        this.until = until;
    }
}
