package model;

/*
 * Tablero de 7x6 (7 en el eje "x" y 6 en el "y").
	   Fichas Rojas y Amarillas. La primera partida la comienza siempre la Roja (la segunda la Amarilla, la tercera la Roja...).
	   No hay que implementar una funcionalidad que te permita jugar contra la App. Se asume que jugarán dos personas reales alternándose.
	   Al seleccionar la columna se coloca la ficha en la parte inferior.
	   Guardar el número partidas ganadas de cada equipo mientras la App no se finaliza.
	   Dos botones para reiniciar la partida en marcha y para resetear el contador de victorias y derrotas.
	   Puedes añadirle todas las funcionalidades extra que consideres.
 */

public class Juego {

	public final int VACIO = 0;
	public final int AMARILLO = 1;
	public final int ROJO = 2;
	private int[][] tablero;
	private int filas;
	private int columnas;
	private int turno;

	public Juego(int filas, int columnas) {
		turno = ROJO;
		this.filas = filas;
		this.columnas = columnas;
		this.tablero = new int[this.filas][this.columnas];
		iniciarJuego();
	}

	public Juego() {
		turno = ROJO;
		filas = 6;
		columnas = 7;
		this.tablero = new int[this.filas][this.columnas];
		iniciarJuego();
	}

	/*
	 * Método para iniciar el juego y reiniciar el tablero
	 */

	public void iniciarJuego() {
		for (int i = 0; i < filas; ++i) {
			for (int j = 0; j < columnas; ++j) {
				tablero[i][j] = VACIO;
			}
		}
	}

	/*
	 * CAMBIA EL VALOR DE LA ÚLTIMA FICHA DE LA COLUMNA PASADA POR EL VALOR DEL
	 * TURNO ACTUAL, CAMBIA EL TURNO Y DEVUELVE -1 SI NO SE PUEDEN AÑADIR MÁS FICHAS
	 * A LA COLUMNA INDICADA
	 */

	public int addFicha(int col) {
		int columnaLlena = -1;
		for (int i = 0; i < filas; ++i) {
			if (tablero[i][col] == VACIO) {
				tablero[i][col] = turno;
				cambiarTurno();
				return i;
			}
		}
		return columnaLlena;
	}

	/*
	 * FUNCION PARA CAMBIAR DE TURNO
	 */
	private void cambiarTurno() {
		if (turno == ROJO) {
			turno = AMARILLO;
		} else {
			turno = ROJO;
		}
	}

	/*
	 * MÉTODO PARA COMPROBAR SI HAY 4 FICHAS EN LA MISMA FILA
	 */
	public boolean hayGanador() {
		
		return (lineaHorizontal() || lineaVertical()|| diagonalEnDerecha() || diagonalEnIzquierda());
	}

	/*
	 * MÉTODO PARA COMPROBAR SI HUBO EMPATE, SI EL TABLERO ESTA LLENO Y NO HAY
	 * GANADOR, SERÁ EMPATE
	 */

	public boolean empate() {
		boolean resul = true;
		for (int i = 0; i < filas && resul; ++i) {
			for (int j = 0; j < i && resul; ++j) {
				if (j == VACIO) {
					resul = false;
				}
			}
		}
		return resul;
	}

	public int getTurno() {
		return turno;
	}

	/*
	 * FUNCION QUE COMPRUEBA SI HAY 4 FICHAS HORIZONTALES SEGUIDAS
	 */
	private boolean cuatroEnFila(int fila, int maximo) {
		boolean resul = false;
		int anterior = -1;
		int cont = 0;
		for (int i = 0; i < maximo && !resul; ++i) {
			if (tablero[fila][i] == anterior) {
				++cont;
			} else {
				cont = 0;
			}
			anterior = tablero[fila][i];
			if (cont == 3) {
				resul = true;
			}
		}
		return resul;
	}

	private boolean cuatroEnColumna(int columna, int maximo) {
		boolean resul = false;
		int anterior = -1;
		int cont = 0;
		for (int i = 0; i < maximo && !resul; ++i) {
			if (tablero[i][columna] == anterior) {
				++cont;
			} else {
				cont = 0;
			}
			anterior = tablero[i][columna];
			if (cont == 3) {
				resul = true;
			}
		}
		return resul;
	}

	/*
	 * FUNCION QUE COMPRUEBA SI HAY 4 EN FILA EN TODO EL TABLERO
	 */
	private boolean lineaHorizontal() {
		boolean resul = false;
		for (int i = 0; i < filas; ++i) {
			if (tablero[i][3] != VACIO && cuatroEnFila(i, columnas) && !resul) {
				resul = true;
			}
		}
		return resul;
	}

	private boolean lineaVertical() {
		boolean resul = false;
		for (int i = 0; i < columnas; ++i) {
			if (tablero[2][i] == tablero[3][i] && tablero[2][i] != VACIO && tablero[3][i] != VACIO) {
				if (cuatroEnColumna(i, filas)) {
					resul = true;
				}
			}
		}
		return resul;
	}

	/*
	 * FUNCIÓN QUE DEVUELVE SI HAY 4 FICHAS EN DIAGONAL HACIA LA DERECHA SEGUIDAS
	 */
	private boolean cuatroEnDiagonalDerecha(int fila, int col, int max) {
		boolean resul = false;
		int anterior = -1;
		int cont = 0;
		for (int i = 0; i < max; ++i) {
			if (tablero[fila + i][col + i] == anterior) {
				++cont;
			} else {
				cont = 0;
			}
			anterior = tablero[fila + i][col + i];
			if (cont == 3) {
				resul = true;
			}
		}
		return resul;
	}

	/*
	 * FUNCIÓN QUE COMPRUEBA SI HAY 4 FICHAS EN DIAGONAL HACIA LA DERECHA SEGUIDAS
	 * EN TODO EL TABLERO
	 */
	private boolean diagonalEnDerecha() {
		boolean resul = false;
		if (tablero[2][0] != VACIO && tablero[2][0] == tablero[3][1] && tablero[2][0] == tablero[4][2]
				&& tablero[2][0] == tablero[5][3]) {
			resul = true;
		}

		if (tablero[0][3] != VACIO && tablero[0][3] == tablero[1][4] && tablero[0][3] == tablero[2][5]
				&& tablero[0][3] == tablero[3][6]) {
			resul = true;
		}

		if (tablero[2][1] != VACIO && tablero[3][2] != VACIO && tablero[4][3] != VACIO) {
			if (cuatroEnDiagonalDerecha(1, 0, 5)) {
				resul = true;
			}
		}

		if (tablero[1][3] != VACIO && tablero[2][4] != VACIO && tablero[3][5] != VACIO) {
			if (cuatroEnDiagonalDerecha(0, 2, 5)) {
				resul = true;
			}
		}

		if (tablero[2][2] != VACIO && tablero[3][3] != VACIO) {
			if (cuatroEnDiagonalDerecha(0, 0, 6)) {
				resul = true;
			}
		}

		if (tablero[2][3] != VACIO && tablero[3][4] != VACIO) {
			if (cuatroEnDiagonalDerecha(0, 1, 6)) {
				resul = true;
			}
		}

		return resul;
	}

	private boolean cuatroEnDiagonalIzquierda(int fila, int col, int max) {
		boolean resul = false;
		int anterior = -1;
		int cont = 0;
		for (int i = 0; i < max; ++i) {
			if (tablero[fila - i][col + i] == anterior) {
				++cont;
			} else {
				cont = 0;
			}
			anterior = tablero[fila - i][col + i];
			if (cont == 3) {
				resul = true;
			}
		}
		return resul;
	}

	/*
	 * FUNCIÓN QUE COMPRUEBA SI HAY CUATRO FICHAS EN DIAGONAL HACIA LA IZQUIERDA
	 * PARA TODO EL TABLERO
	 */

	private boolean diagonalEnIzquierda() {
		boolean resul = false;
		if (tablero[3][0] != VACIO && tablero[3][0] == tablero[2][1] && tablero[2][1] == tablero[1][2]
				&& tablero[1][2] == tablero[0][3]) {
			resul = true;
		}

		if (tablero[5][3] != VACIO && tablero[5][3] == tablero[4][4] && tablero[4][4] == tablero[3][5]
				&& tablero[3][5] == tablero[2][6]) {
			resul = true;
		}

		if (tablero[3][1] != VACIO && tablero[2][2] != VACIO && tablero[1][3] != VACIO) {
			if (cuatroEnDiagonalIzquierda(4, 0, 5)) {
				resul = true;
			}
		}

		if (tablero[4][3] != VACIO && tablero[3][4] != VACIO && tablero[2][5] != VACIO) {
			if (cuatroEnDiagonalIzquierda(5, 2, 5)) {
				resul = true;
			}
		}

		if (tablero[2][4] != VACIO && tablero[3][3] != VACIO) {
			if (cuatroEnDiagonalIzquierda(5, 1, 6)) {
				resul = true;
			}
		}
		if (tablero[2][3] != VACIO && tablero[3][2] != VACIO) {
			if (cuatroEnDiagonalIzquierda(5, 0, 6)) {
				resul = true;
			}
		}
		return resul;
	}
}
