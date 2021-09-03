package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Pessoa extends Thread{
	private int idPerson;
	private static int colocacao;
	private Semaphore semaforo;
	Random r = new Random();

	public Pessoa(int idPerson, Semaphore semaforo) {
		this.idPerson = idPerson;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		pessoaCaminhando();
		try {
			semaforo.acquire();
			PessoaNaPorta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		PessoaCruzando();
	}

	private void pessoaCaminhando() {
		int tempo = 200;
		int distanciaPercorrida = 0;

		while (distanciaPercorrida < 200) {
			distanciaPercorrida += (int) ((Math.random() * 3) + 4);
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A Pessoa #" + idPerson + " já andou " + distanciaPercorrida + " metros");
		}
	}

	private void PessoaNaPorta() {
		System.out.println("A Pessoa #" + idPerson + " chegou na porta");
		double tempoInicial = System.nanoTime();
		int tempoParado = (r.nextInt(3 - 1) + 1) * 1000;
		try {
			Thread.sleep(tempoParado);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double tempoFinal = System.nanoTime();
		double tempototal = (tempoFinal - tempoInicial)/Math.pow(10, 9); //Converte nanosegundos para segundos
		System.out.println("Pessoa #" + idPerson + " Tempo parado: " + tempototal);
	}

	private void PessoaCruzando() {
		colocacao++;
		System.out.println("A pessoa #" + idPerson + " foi a " + colocacao + "ª. a cruzar a porta");
	}
}

