package br.com.listamercado.app;

import com.orm.SugarRecord;

/**
 * Created by 16254862 on 25/10/2017.
 */

public class Produto extends SugarRecord {

    private  String nome;
    private  Boolean ativo;

    // Construtores obrigatórios por conta do ORM
    public  Produto(){}

    public Produto(String nome, boolean ativo) {
        this.nome = nome;
        this.ativo = ativo;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }




}
