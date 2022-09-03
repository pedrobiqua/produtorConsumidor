package produtorConsumidor;
import java.util.concurrent.Semaphore;
import java.util.Random;

public class Loja extends Thread {
    public char nomeLoja;
    public int contadorVendas;
    private Semaphore mutex, itens;
    public FilaVenda vendas;
    public Random random = new Random();


    public Loja(FilaVenda vendas, Semaphore mutex, Semaphore itens){
        this.vendas = vendas;
        this.mutex = mutex;
        this.itens = itens;
    }

    public char sorteiaProduto() {
        String possibilidades = "ABCDEFGH";
        char nomeProduto = possibilidades.charAt(random.nextInt(8));
        return nomeProduto;
    }

    public void run(){

        while(true){
            try {

                Venda venda = new Venda(sorteiaProduto(), this);
                contadorVendas++;

                mutex.acquire();
                    System.out.println("Produto: " + venda.nomeProduto + " Pedido: " + contadorVendas + nomeLoja);
                    vendas.vendas.add(venda);
                mutex.release();
                itens.release();

                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }

}
