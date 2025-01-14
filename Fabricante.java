package produtorConsumidor;
import java.util.concurrent.Semaphore;
import java.util.Random;

public class Fabricante extends Thread{
    char nomeFabricante;
    private Semaphore mutex, itens, itens2, mutexEntregas;
    FilaVenda vendas;
    FilaEntrega entregas;


    public Fabricante(FilaVenda vendas, FilaEntrega entregas, Semaphore mutex, Semaphore itens, Semaphore itens2, Semaphore mutexEntregas){
        this.vendas = vendas;
        this.entregas = entregas;
        this.mutex = mutex;
        this.itens = itens;
        this.itens2 = itens2;
        this.mutexEntregas = mutexEntregas;

    }

    public void run(){
        Random random = new Random();
        while(true){
            try {
                Thread.sleep(random.nextInt(3000)); //arrumar intervalo conforme a tabela no .pdf
                
                itens.acquire();
                    
                    mutex.acquire();
                        Venda newVenda = vendas.vendas.get(0);
                        vendas.vendas.remove(vendas.vendas.get(0));
                    mutex.release();

                    Thread.sleep(random.nextInt(1000)); //arrumar intervalo conforme a tabela no .pdf
                    // Cria entrega

                    Entrega entrega = new Entrega();
                    entrega.numeroEntrega++;
                    entrega.venda = newVenda;
                    
                    //criar segundo mutex
                    mutexEntregas.acquire();
                        entregas.entregas.add(entrega);
                        System.out.println("Fila de entregas: " + entregas.entregas.size());
                    mutexEntregas.release();

                    Thread.sleep(random.nextInt(1000)); //arrumar intervalo conforme a tabela no .pdf

                    System.out.println("Fabricante retira produto");
                
                itens2.release();
                //Criar outro itens.release();
            }
            catch (InterruptedException e) {
				e.printStackTrace();
			}           
        }
    }
}
