package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Caderno;
import one.digitalinovation.laboojava.entidade.Livro;
import one.digitalinovation.laboojava.entidade.Produto;
import one.digitalinovation.laboojava.entidade.constantes.Materias;

import java.util.Optional;

public class CadernoNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    public CadernoNegocio(Banco bancoDados) {
        this.bancoDados = bancoDados;
    }

    /**
     * Consulta o caderno pelo o seu nome.
     *
     * @param nome CPF de um cliente
     * @return O cliente que possuir o CPF passado.
     */
    public Optional<Caderno> consultar(String nome) {

        for (Produto produto: bancoDados.getProdutos()) {
            if (produto instanceof Caderno) {
                Caderno caderno = (Caderno) produto;
                if(caderno.getTipo().equals(Materias.valueOf(nome.toUpperCase()))){
                    return Optional.of(caderno);
                }

            }

        }
        return Optional.empty();

    }
}
