package com.github.gerivansantos;

import com.github.gerivansantos.models.Categoria;
import com.github.gerivansantos.models.Cidade;
import com.github.gerivansantos.models.Cliente;
import com.github.gerivansantos.models.Endereco;
import com.github.gerivansantos.models.Estado;
import com.github.gerivansantos.models.ItemPedido;
import com.github.gerivansantos.models.Pagamento;
import com.github.gerivansantos.models.PagamentoComBoleto;
import com.github.gerivansantos.models.PagamentoComCartao;
import com.github.gerivansantos.models.Pedido;
import com.github.gerivansantos.models.Product;
import com.github.gerivansantos.models.Stock;
import com.github.gerivansantos.models.enums.EstadoPagamento;
import com.github.gerivansantos.models.enums.TipoCliente;
import com.github.gerivansantos.repositories.CategoriaRepository;
import com.github.gerivansantos.repositories.CidadeRepository;
import com.github.gerivansantos.repositories.ClienteRepository;
import com.github.gerivansantos.repositories.EnderecoRepository;
import com.github.gerivansantos.repositories.EstadoRepository;
import com.github.gerivansantos.repositories.ItemPedidoRepository;
import com.github.gerivansantos.repositories.PagamentoRepository;
import com.github.gerivansantos.repositories.PedidoRepository;
import com.github.gerivansantos.repositories.ProductRepository;
import com.github.gerivansantos.repositories.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class PdvEmporioApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private StockRepository stockRepository;

    public static void main(String[] args) {
        SpringApplication.run(PdvEmporioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        Categoria cat1 = new Categoria(null, "Informatica");
        Categoria cat2 = new Categoria(null, "Escritorio");
        Categoria cat3 = new Categoria(null, "Cama mesa e banho");
        Categoria cat4 = new Categoria(null, "Eletrônicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Product p1 = new Product(null, "Computador", "", 2000.00, new Date(), new Date());
        Product p2 = new Product(null, "Impressora", "", 300.00, new Date(), new Date());
        Product p3 = new Product(null, "Mouse", "", 80.00, new Date(), new Date());
        Product p4 = new Product(null, "Mesa de Escritório", "", 300.00, new Date(), new Date());
        Product p5 = new Product(null, "Toalha", "", 50.00, new Date(), new Date());
        Product p6 = new Product(null, "Colcha", "", 200.00, new Date(), new Date());
        Product p7 = new Product(null, "TV true color", "", 1200.00, new Date(), new Date());
        Product p8 = new Product(null, "Roçadeira", "", 800.00, new Date(), new Date());
        Product p9 = new Product(null, "Abajour", "", 100.00, new Date(), new Date());
        Product p10 = new Product(null, "Pendente", "", 180.00, new Date(), new Date());
        Product p11 = new Product(null, "Shampoo", "", 90.00, new Date(), new Date());

        Stock estq = new Stock(null, p1, 5, new Date());

        p1.setStock(estq);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Arrays.asList(p11));

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));

        p4.getCategories().addAll(Arrays.asList(cat2));
        p5.getCategories().addAll(Arrays.asList(cat3));
        p6.getCategories().addAll(Arrays.asList(cat3));
        p7.getCategories().addAll(Arrays.asList(cat4));
        p8.getCategories().addAll(Arrays.asList(cat5));
        p9.getCategories().addAll(Arrays.asList(cat6));
        p10.getCategories().addAll(Arrays.asList(cat6));
        p11.getCategories().addAll(Arrays.asList(cat7));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p9, p9, p10, p11));
        stockRepository.saveAll(Arrays.asList(estq));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);

        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));


    }

}

