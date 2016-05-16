import java.util.ArrayList;

public class Consumidor implements Runnable {
	
	private List Lista;
	static final int MAX_LISTA = 20;
	private int n_Consumidor;
	private int n_Consumo;

	public Consumidor (List Lista, int n_Consumidor, int n_Consumo)
	{
		this.Lista = Lista;
		this.n_Consumidor = n_Consumidor;
		this.n_Consumo = n_Consumo;
	}
	
	@Override
	public void run ()
	{
		for (int i = 1; i<= n_Consumo; i++)
			try
			{
				Consumir (i);
			} catch(InterruptedException e)
			{
				;
			}
	}
	
	public void Consumir (int i) throws InterruptedException
	{
		synchronized (Lista)
		{
			while (Lista.size() == 0)
			{
				System.out.println("Lista vazia!");
				Lista.wait();
			}
		}
		
		synchronized (Lista)
		{
			System.out.println("Consumidor " + n_Consumidor + ": Consumido: " + i);
			Lista.remove(0);		//Remove o primeiro da lista: First In, First Out
			double a = 1000* Math.random();
			Thread.sleep((int)a + 1); 
			Lista.notifyAll();
		}
	}
}
	
	
	
