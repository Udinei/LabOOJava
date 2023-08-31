package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Cliente;
import one.digitalinovation.laboojava.entidade.Cupom;
import one.digitalinovation.laboojava.entidade.Pedido;
import one.digitalinovation.laboojava.entidade.Produto;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Cliente}.
 * @author thiago leite
 */
public class ClienteNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter acesso aos clientes cadastrados
     */
    public ClienteNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    /**
     * Consulta o cliente pelo seu CPF.
     * @param cpf CPF de um cliente
     * @return O cliente que possuir o CPF passado.
     */
    public Optional<Cliente> consultar(String cpf) {

        for(Cliente cliente: bancoDados.getClientes()) {
            if (cliente.getCpf().equals(cpf)) {
                return Optional.of(bancoDados.getCliente());
            }
        }
        return Optional.empty();

    }


    /**
     * Cadastra um novo cliente.
     * Salva um novo cliente.
     * @param novoCliente Cliente a ser cadastrado
     */
    public void salvar(Cliente novoCliente) {

        boolean clienteJaCadastrado = false;

       // le o banco de dados
        for (Cliente cliente: bancoDados.getClientes()) {
            // verifica pelo cpf, se no bd o novo cliente ja esta cadastrado
            if (cliente.getCpf() == novoCliente.getCpf()) {
                clienteJaCadastrado = true;
                System.out.println("Cliente já cadastrado.");
                break;
            }
        }

        // cliente cadastrado
        if (!clienteJaCadastrado) {
            this.bancoDados.adicionarCliente(novoCliente);
            System.out.println("Cliente  cadastrado com sucesso.");
        }

    }


    /**
     * Exclui um cliente a partir de seu cpf.
     * @param cpf Código do pedido
     */
    public void excluir(String cpf) {

        int clienteExclusao = -1;
        for (int i = 0; i < bancoDados.getClientes().length; i++) {

            Cliente cliente = bancoDados.getClientes()[i];
            if (cliente.getCpf().equals(cpf)) {
                clienteExclusao = i; // index do registro encontrado
                break;
            }
        }

        if (clienteExclusao != -1) {
            bancoDados.removerCliente(clienteExclusao);
            System.out.println("Cliente excluído com sucesso.");
        } else {
            System.out.println("Cliente inexistente.");
        }
    }

    public void listarTodos() {

        if (bancoDados.getClientes().length == 0) {
            System.out.println("Não existem clientes cadastrados");
        } else {

            for (Cliente produto: bancoDados.getClientes()) {
                System.out.println(produto.toString());
            }
        }
    }
}
