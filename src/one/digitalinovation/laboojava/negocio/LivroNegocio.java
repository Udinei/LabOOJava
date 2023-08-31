package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Cliente;
import one.digitalinovation.laboojava.entidade.Livro;
import one.digitalinovation.laboojava.entidade.Produto;

import java.util.Optional;

public class LivroNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    public LivroNegocio(Banco bancoDados) {
        this.bancoDados = bancoDados;
    }

    /**
     * Consulta o LIVRO pelo o seu CPF.
     *
     * @param nome CPF de um cliente
     * @return O cliente que possuir o CPF passado.
     */
    public Optional<Livro> consultar(String nome) {

        for (Produto produto: bancoDados.getProdutos()) {
            if (produto instanceof Livro) {
                Livro livro = (Livro) produto;
                if(livro.getNome().equals(nome)) {
                    return Optional.of(livro);
                }

            }

        }
        return Optional.empty();

    }
}