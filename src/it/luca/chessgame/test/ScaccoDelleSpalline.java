package it.luca.chessgame.test;

import static org.junit.Assert.*;

import java.awt.Color;

import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

/**
 * Classe di test per la combinazione di matto delle spalline:
 * se il re è autobloccato la donna può dare scacco da sola.
 * Devono essere occupate le due casa di fuga a salto del cavallo
 * dalla posizione della donna.
 * 
 * @author luca
 */
public class ScaccoDelleSpalline{
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	
	@Test
	public void testScaccoDelleSpalline(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(0, 0, new Torre(black));
		c.set(0, 1, new Pedone(black));
		c.set(1, 1, new Pedone(black));
		c.set(3, 0, new Regina(black));
		c.set(7, 1, new Re(black));
		c.set(7, 2, new Pedone(black));
		c.set(7, 0, new Torre(black));
			
		c.set(7, 2, new Torre(white));
		c.set(5, 1, new Regina(white));
		c.set(6, 6, new Pedone(white));
		c.set(6, 7, new Re(white));
		c.set(7, 5, new Pedone(white));
			
		mover = new Mover(new TilesModel(c));
		mover.setTurno(false);
		
		new Simulation(c, "Il re è autobloccato e la donna dà scacco");

		// scacco matto
		assertTrue(mover.scaccoMatto());
	}
}