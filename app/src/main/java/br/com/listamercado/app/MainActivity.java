package br.com.listamercado.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list_view;
    EditText txt_produto;

    ProdutoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view = (ListView) findViewById(R.id.list_view);
        txt_produto = (EditText) findViewById(R.id.txt_produto);

        List<Produto> lstProdutos = Produto.listAll(Produto.class);

        // Dados Fake
        /*
        lstProdutos.add(new Produto("Arroz", false));
        lstProdutos.add(new Produto("Feijão", false));
        lstProdutos.add(new Produto("Macarrão", true));
        */

        // Criar o Adapter
        adapter = new ProdutoAdapter(this, lstProdutos);

        // Ligar o adapter a list_view
        list_view.setAdapter(adapter);
    }

    public void inserirItem(View view) {

        // Pega conteudo da caixibha
        String produto = txt_produto.getText().toString();

        if(produto.isEmpty()) return;

        // Criadno o obj produto
        Produto p = new Produto(produto, false);

        // Adcionando na lista
        adapter.add(p);

        // Adicionando no Banco
        p.save();

        //Limpando a caixa
        txt_produto.setText(null);
    }

    //Classe do Adapter
    private  class ProdutoAdapter extends ArrayAdapter<Produto> {

        public ProdutoAdapter(Context ctx, List<Produto> items) {
            super(ctx ,0, items);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View v = convertView;

            if(v == null) {
                v = LayoutInflater.from(getContext()).inflate(R.layout.item_lista, null);
            }

            Produto item = getItem(position);

            TextView txt_item_produto = v.findViewById(R.id.txt_item_produto);
            CheckBox ck_item_produto = v.findViewById(R.id.ck_item_produto);

            txt_item_produto.setText(item.getNome());
            ck_item_produto.setChecked(item.getAtivo());

            return v;
        }
    }
}
