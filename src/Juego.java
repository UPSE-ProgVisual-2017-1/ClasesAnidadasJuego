import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Juego {

	interface Enemigo{
		public void atacarJugador(Jugador j);
		public int getPoderEnemigo();
	}
	
	static class PersonajeJuego{
		private String nombrePersonaje;
		private int defensa;
		private int poder;
		
		public PersonajeJuego(String nombrePersonaje, int poder, int defensa) {
			this.nombrePersonaje = nombrePersonaje;
			this.poder = poder;
			this.defensa = defensa;
		}

		public String getNombrePersonaje() {
			return nombrePersonaje;
		}

		public void setNombrePersonaje(String nombrePersonaje) {
			this.nombrePersonaje = nombrePersonaje;
		}

		public int getDefensa() {
			return defensa;
		}

		public void setDefensa(int defensa) {
			this.defensa = defensa;
		}

		public int getPoder() {
			return poder;
		}

		public void setPoder(int poder) {
			this.poder = poder;
		}
		
		@Override
		public String toString() {
			return "PersonajeJuego [nombrePersonaje=" + nombrePersonaje + ", defensa=" + defensa + ", poder=" + poder
					+ "]";
		}
	}
	
	class Jugador {
		private String nombreJugador;
		private int salud = 100;
		private Juego.PersonajeJuego personajeElegido;
		
		public Jugador(String nombreJugador, int salud, Juego.PersonajeJuego personajeElegido)
		{
			this.nombreJugador = nombreJugador;
			this.salud = salud;
			this.personajeElegido = personajeElegido;
		}

		public String getNombreJugador() {
			return nombreJugador;
		}

		public void setNombreJugador(String nombreJugador) {
			this.nombreJugador = nombreJugador;
		}

		public int getSalud() {
			return salud;
		}

		public void setSalud(int salud) {
			this.salud = salud;
		}

		public Juego.PersonajeJuego getPersonajeElegido() {
			return personajeElegido;
		}

		public void setPersonajeElegido(Juego.PersonajeJuego personajeElegido) {
			this.personajeElegido = personajeElegido;
		}

		@Override
		public String toString() {
			return "Jugador [nombreJugador=" + nombreJugador + ", salud=" + salud + ", personajeElegido="
					+ personajeElegido + "]";
		}
	}

	static class Arena{
		
		enum TipoArena{
			OFENSIVA, DEFENSIVA, NEUTRA;
		}
		
		private String nombre;
		private TipoArena tipo;
		private int tiempoLimite;
		
		public Arena(String nombre, TipoArena tipo, int tiempoLimite) {
			super();
			this.nombre = nombre;
			this.tipo = tipo;
			this.tiempoLimite = tiempoLimite;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public TipoArena getTipo() {
			return tipo;
		}

		public void setTipo(TipoArena tipo) {
			this.tipo = tipo;
		}

		public int getTiempoLimite() {
			return tiempoLimite;
		}

		public void setTiempoLimite(int tiempoLimite) {
			this.tiempoLimite = tiempoLimite;
		}

		@Override
		public String toString() {
			return "Arena [nombre=" + nombre + ", tipo=" + tipo + ", tiempoLimite=" + tiempoLimite + "]";
		}

	}
	
	private List<Juego.PersonajeJuego> personajesDisponibles = new ArrayList<Juego.PersonajeJuego>();
	private List<Arena> arenasDisponibles = new ArrayList<Arena>();
	private Jugador jugadorA;
	private Jugador jugadorB;
	private LocalDateTime fechaPartida;
	private Arena arena;
	
	public Juego()
	{
		this.fechaPartida = LocalDateTime.now();
	}
	
	public Juego(Jugador jugadorA, Jugador jugadorB, LocalDateTime fechaPartida, Arena arena) {
		super();
		this.jugadorA = jugadorA;
		this.jugadorB = jugadorB;
		this.fechaPartida = fechaPartida;
		this.arena = arena;
	}
	
	public void agregarPersonaje(PersonajeJuego personaje)
	{
		personajesDisponibles.add(personaje);
	}
	
	public void agregarArena(Arena arena)
	{
		arenasDisponibles.add(arena);
	}
	
	public void jugar()
	{
		Random r = new Random();
		Enemigo e = generarEnemigo();
		
		do{
			int turno = r.nextInt(2);
			
			if(turno==0)
			{
				atacarDefender(jugadorA, jugadorB, arena);
			}else{
				atacarDefender(jugadorB, jugadorA, arena);
			}
			
			int malaSuerte = r.nextInt(3);
			switch (malaSuerte) {
				case 0: break;
				case 1: e.atacarJugador(jugadorA);
					break;
				case 2: e.atacarJugador(jugadorB);
					break;
			}
			
		}while(jugadorA.getSalud()>0 && jugadorB.getSalud()>0);
		
		Jugador ganador = ( jugadorA.getSalud()>0 ? jugadorA : jugadorB);
		System.out.println("El ganador es ... " + ganador.getNombreJugador());
	}

	public void atacarDefender(Jugador atacante, Jugador defensor, Arena arena) {
		Random r = new Random();
		
		System.out.println("*********Batalla Salvaje***********");
		System.out.println(atacante.getNombreJugador() + " vs " + defensor.getNombreJugador());
		System.out.println("Estadisticas:");
		System.out.println("Atacante : " + atacante);
		System.out.println("Defensor : " + defensor + "\n");
		System.out.println("Comienza batalla.....");
		int poderTotalAtaqueP1 = atacante.getPersonajeElegido().getPoder();
		int poderRealAtaqueP1 = poderTotalAtaqueP1 * r.nextInt(100)/100;
		
	
		int defensaTotalP2 = defensor.getPersonajeElegido().getDefensa();
		int defensaRealP2 = defensaTotalP2 * r.nextInt(100)/100;
		
		switch (arena.tipo){
		case OFENSIVA:
			poderRealAtaqueP1 = poderRealAtaqueP1*2;
			defensaRealP2 = Math.round( (float) (defensaRealP2 * 0.5));
			break;
		
		case DEFENSIVA:
			defensaRealP2 = defensaRealP2*2;
			poderRealAtaqueP1 = Math.round( (float) (poderRealAtaqueP1 * 0.5));
			break;
		
		case NEUTRA: break;
		}
		
		System.out.println(atacante.getNombreJugador() + " dice: toma esto @#$@#!. Ataca con" + poderRealAtaqueP1);
		//System.out.println("Jugador " + atacante.getNombreJugador() + " ataca con " + poderRealAtaqueP1 );
		System.out.println("Jugador " + defensor.getNombreJugador() + " se defiende con " + defensaRealP2);
		int danoRealizado = (poderRealAtaqueP1-defensaRealP2>0 ? poderRealAtaqueP1-defensaRealP2 : 0);
		int saludActualP2 = defensor.getSalud() - danoRealizado;
		defensor.setSalud(saludActualP2);
		
		if(danoRealizado>0)
		{
			System.out.println("Oh no, me has danado! ... nueva salud de " + defensor.getNombreJugador() + " es " + defensor.getSalud());
		}else {
			System.out.println("Con quien crees que te estas metiendo! ... " + defensor.getNombreJugador() + " se ha defendido bien y su salud esta intacta!" + defensor.getSalud() );
		}
		
		System.out.println("*******************************\n\n");
	}

	public Jugador getJugadorA() {
		return jugadorA;
	}

	public void setJugadorA(Jugador jugadorA) {
		this.jugadorA = jugadorA;
	}

	public Jugador getJugadorB() {
		return jugadorB;
	}

	public void setJugadorB(Jugador jugadorB) {
		this.jugadorB = jugadorB;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public LocalDateTime getFechaPartida() {
		return fechaPartida;
	}
	
	public Enemigo generarEnemigo(){
		class EnemigoSimplon implements Enemigo{

			int poder = 10; 
			
			@Override
			public void atacarJugador(Jugador j) {
				j.setSalud(j.getSalud()-poder);
				System.out.println(j.getNombreJugador() + " es super salado. El enemigo simplon a atacado a " + j.getNombreJugador() 
				+ ". Nueva salud de " + j.getNombreJugador() + " es " + j.getSalud());
			}

			@Override
			public int getPoderEnemigo() {
				
				return poder;
			}
		}
		
		Enemigo e = new EnemigoSimplon();
		return e;
	}
	
	
}
