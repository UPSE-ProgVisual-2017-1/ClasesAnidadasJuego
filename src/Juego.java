import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Juego {

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
	
	
}
