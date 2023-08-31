package one.digitalinovation.laboojava.console;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.*;
import one.digitalinovation.laboojava.negocio.*;
import one.digitalinovation.laboojava.utilidade.LeitoraDados;

import java.util.Optional;

/**
 * Classe responsável por controlar a execução da aplicação.
 * @author thiago leite
 */
public class Start {

    private static Cliente clienteLogado = null;

    private static Banco banco = new Banco();

    private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
    private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
    private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

    private static LivroNegocio livroNegocio = new LivroNegocio(banco);

    private static CadernoNegocio cadernoNegocio = new CadernoNegocio(banco);

    /**
     * Método utilitário para inicializar a aplicação.
     * @param args Parâmetros que podem ser passados para auxiliar na execução.
     */
    public static void main(String[] args) {

        System.out.println("Bem vindo ao e-Compras");

        String opcao = "";

        while(true) {

            if (clienteLogado == null) {

                System.out.println("Digite o cpf:");

                String cpf = "";
                cpf = LeitoraDados.lerDado();

                identificarUsuario(cpf);
            }

            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Excluir Livro");
            System.out.println("3 - Cadastrar Caderno");
            System.out.println("4 - Excluir Caderno");
            System.out.println("5 - Fazer pedido");
            System.out.println("6 - Excluir pedido");
            System.out.println("7 - Listar produtos");
            System.out.println("8 - Listar pedidos");
            System.out.println("9 - Deslogar");
            System.out.println("10 - Sair");
            System.out.println("11 - Cadastrar Cliente");
            System.out.println("12 - Listar Cliente");
            System.out.println("13 - Excluir Cliente");
            System.out.println("14 - Consultar Livro");
            System.out.println("15 - Consultar Caderno");
            System.out.println("16 - Consultar Pedido");

            opcao = LeitoraDados.lerDado();

            switch (opcao) {
                case "1":
                    // cadastrar livro
                    Livro livro = LeitoraDados.lerLivro();
                    produtoNegocio.salvar(livro);
                    break;
                case "2":
                    // excluir livro
                    System.out.println("Digite o código do livro");
                    String codigoLivro = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoLivro);
                    break;
                case "3":
                    // cadastrar caderno
                    Caderno caderno = LeitoraDados.lerCaderno();
                    produtoNegocio.salvar(caderno);

                    break;
                case "4":
                    System.out.println("Digite o código do caderno");
                    String codigoCaderno = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoCaderno);
                    break;
                case "5":
                    Pedido pedido = LeitoraDados.lerPedido(banco);
                    Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

                    if (cupom.isPresent()) {
                        pedidoNegocio.salvar(pedido, cupom.get());
                    } else {
                        pedidoNegocio.salvar(pedido);
                    }
                    break;
                case "6":
                    System.out.println("Digite o código do pedido");
                    String codigoPedido = LeitoraDados.lerDado();
                    pedidoNegocio.excluir(codigoPedido);
                    break;
                case "7":
                    produtoNegocio.listarTodos();
                    break;
                case "8":
                    // Listar todos os Pedidos
                    pedidoNegocio.listarTodos();
                    break;
                case "9":
                    System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
                    clienteLogado = null;
                    break;
                case "10":
                    System.out.println("Aplicação encerrada.");
                    System.exit(0);
                    break;
                case "11":
                    Cliente cliente = LeitoraDados.lerCliente();
                    clienteNegocio.salvar(cliente);
                    break;
                case "12":
                    clienteNegocio.listarTodos();
                    break;
                case "13":
                    System.out.println("Digite o cpf do cliente para exclusão");
                    String cpf = LeitoraDados.lerDado();
                    clienteNegocio.excluir(cpf);
                    clienteNegocio.listarTodos();
                    break;
                case "14":
                    System.out.println("Digite o nome do livro: ");
                    String nome = LeitoraDados.lerDado();
                    Optional<Livro> livroConsulta = livroNegocio.consultar(nome);
                    System.out.printf("Codigo: "+ livroConsulta.get().getCodigo() +" Livro: " + livroConsulta.get().getNome() +" Genero: " + livroConsulta.get().getGenero());
                    break;
                case "15":
                    System.out.println("Digite o a materia do caderno");
                    String materia = LeitoraDados.lerDado();
                    Optional<Caderno> cadernoConsulta = cadernoNegocio.consultar(materia);
                    System.out.printf("Codigo: "+ cadernoConsulta.get().getCodigo() +" Tipo: " + cadernoConsulta.get().getTipo() +" Preço: " + cadernoConsulta.get().getPreco());
                    break;
                case "16":
                    System.out.println("Digite o codigo do pedido");
                    String codigo = LeitoraDados.lerDado();
                    Optional<Pedido> pedidoConsultar = pedidoNegocio.consultar(codigo);
                    System.out.printf("Codigo: "+ pedidoConsultar.get().getCodigo() +" Tipo: " + pedidoConsultar.get().getCliente() +" Preço: " + pedidoConsultar.get().getTotal());
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    /**
     * Procura o usuário na base de dados.
     * @param cpf CPF do usuário que deseja logar na aplicação
     */
    private static void identificarUsuario(String cpf) {

        Optional<Cliente> resultado = clienteNegocio.consultar(cpf);

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();
            System.out.println(String.format("Olá %s! Você está logado.", cliente.getNome()));
            clienteLogado = cliente;
        } else {
            System.out.println("Usuário não cadastrado.");
            System.exit(0);
        }
    }
}
