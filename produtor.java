import java.util.ArrayList;

public class Produtor implements Runnable {
	
	private List Lista;
	static final int MAX_LISTA = 20;
	
	private int n_Produtor;
	private int n_Producao;
	

	public Produtor (List Lista, int n_Produtor, int n_Producao)
	{
		this.Lista = Lista;
		this.n_Produtor = n_Produtor;
		this.n_Producao = n_Producao;
	}
	
	@Override
	public void run ()
	{
		for (int i = 1; i<= n_Producao; i++)
			try
			{
				Produzir (i);
			} catch(InterruptedException e)
			{
				;
			}
	}
	
	public void Produzir (int i) throws InterruptedException
	{
		synchronized (Lista)
		{
			while (Lista.size() == MAX_LISTA)
			{
				System.out.println("Lista cheia");
				Lista.wait();
			}
		}
		
		synchronized (Lista)
		{
			System.out.println("Produtor " + n_Produtor + ": Produzido: " + i);
			Lista.add(i);
			double a = 1000* Math.random();
			Thread.sleep((int)a + 1);  
			Lista.notifyAll();
		}
		
	}
}
	
	
	
