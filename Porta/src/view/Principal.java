package view;
import java.util.concurrent.Semaphore;

import controller.Pessoa;


public class Principal {

	public static void main(String[] args) {
			int permissoes = 1;
			Semaphore semaforo = new Semaphore(permissoes);

			for (int idPessoa = 1; idPessoa < 5; idPessoa++) { //Considerando Pessoa #1 e Pessoa #4, para não marcá-las com 0
				Pessoa pessoa = new Pessoa(idPessoa, semaforo);
				pessoa.start();
			}

	}

}
