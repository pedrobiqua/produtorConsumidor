package produtorConsumidor;
import java.util.concurrent.Semaphore;

public class App {
    
    public static void main(String args[]){
        FilaVenda vendas = new FilaVenda();
        FilaEntrega entregas = new FilaEntrega();

        Semaphore mutexVendas = new Semaphore( 1 );
        Semaphore mutexEntregas = new Semaphore( 1 );
		Semaphore itens = new Semaphore( 0 );
		Semaphore itens2 = new Semaphore( 0 );
		Semaphore espacos = new Semaphore( 100 );
        

        Loja LojaA = new Loja(vendas, mutexVendas, itens);
        LojaA.nomeLoja = 'A';

        Loja LojaB = new Loja(vendas, mutexVendas, itens);
        LojaB.nomeLoja = 'B';

        Loja LojaC = new Loja(vendas, mutexVendas, itens);
        LojaC.nomeLoja = 'C';

        Loja LojaD = new Loja(vendas, mutexVendas, itens);
        LojaD.nomeLoja = 'D';

        Loja LojaE = new Loja(vendas, mutexVendas, itens);
        LojaE.nomeLoja = 'E';

        Loja LojaF = new Loja(vendas, mutexVendas, itens);
        LojaF.nomeLoja = 'F';

        Loja LojaG = new Loja(vendas, mutexVendas, itens);
        LojaG.nomeLoja = 'G';

        Loja LojaH = new Loja(vendas, mutexVendas, itens);
        LojaH.nomeLoja = 'H';

        Fabricante FabrA = new Fabricante(vendas, entregas, mutexVendas, itens, itens2, mutexEntregas);
        FabrA.nomeFabricante = 'A';
        Fabricante FabrB = new Fabricante(vendas, entregas, mutexVendas, itens, itens2, mutexEntregas);
        FabrB.nomeFabricante = 'B';
        Fabricante FabrC = new Fabricante(vendas, entregas, mutexVendas, itens, itens2, mutexEntregas);
        FabrC.nomeFabricante = 'C';
        Fabricante FabrD = new Fabricante(vendas, entregas, mutexVendas, itens, itens2, mutexEntregas);
        FabrD.nomeFabricante = 'D';

        Transportadora TransA = new Transportadora('A', entregas, espacos, itens2, mutexEntregas);
        Transportadora TransB = new Transportadora('B', entregas, espacos, itens2, mutexEntregas);

        LojaA.start();
        LojaB.start();
        LojaC.start();
        LojaD.start();
        LojaE.start();
        LojaF.start();
        LojaG.start();
        LojaH.start();
        
        FabrA.start();
        FabrB.start();
        FabrC.start();
        FabrD.start();

        TransA.start();
        TransB.start();

        
    }

}
