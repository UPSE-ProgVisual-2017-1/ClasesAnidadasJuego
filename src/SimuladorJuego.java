import java.time.LocalDateTime;

import javax.swing.text.html.ParagraphView;

public class SimuladorJuego {

	public static void main(String[] args) {
		
		Juego.PersonajeJuego miniPekka = new Juego.PersonajeJuego("miniPekka", 70, 30);
		Juego.PersonajeJuego dragonInfernal = new Juego.PersonajeJuego("Dragon Infernal", 70, 40);
		Juego.PersonajeJuego bandida = new Juego.PersonajeJuego("Bandida", 90, 15);
		
		Juego.Arena legendario = new Juego.Arena("Arena Legendaria", Juego.Arena.TipoArena.OFENSIVA, 10);
		Juego.Arena montaPuertos = new Juego.Arena("Monta Puertos", Juego.Arena.TipoArena.DEFENSIVA, 20);
		
		Juego partidaMortal = new Juego(); 
		
		Juego.Jugador manuelito = partidaMortal.new Jugador("Manuelito", 100, bandida);
		Juego.Jugador abel = partidaMortal.new Jugador("abel", 100, dragonInfernal);
		
		//Agrego una Arena
		partidaMortal.agregarArena(montaPuertos);
		partidaMortal.agregarArena(legendario);
		
		//Agrego personajes, no es totalmente necesario
		partidaMortal.agregarPersonaje(bandida);
		partidaMortal.agregarPersonaje(dragonInfernal);
		partidaMortal.agregarPersonaje(miniPekka);
		
		partidaMortal.setArena(montaPuertos);
		partidaMortal.setJugadorA(manuelito);
		partidaMortal.setJugadorB(abel);
		
		partidaMortal.jugar();
	}

}
