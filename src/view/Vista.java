package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import model.Juego;

public class Vista extends javax.swing.JFrame {
	Juego partida;
	private int filas, columnas;
	private JLabel lTurno;
    private javax.swing.JLabel lPuntosAmarillo;
    private javax.swing.JLabel lPuntosRojo;
    private javax.swing.JPanel pLateralDerecha;
    private javax.swing.JPanel pLateralIzquierda;
    private javax.swing.JPanel pTablero;
    private javax.swing.JPanel pTurno;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration//GEN-END:variables
    private JButton[][] botones;
    JLabel lQuienToca;
    int puntosRojo, puntosAmarillo;
	/**
	 * Creamos el form
	 */

	public Vista() {
		partida = new Juego();
		puntosRojo = 0;
		puntosAmarillo = 0;
		filas = 6;
		columnas = 7;
		lTurno = new JLabel("Le toca al jugador: ROJO", SwingConstants.CENTER);
		lTurno.setFont(new java.awt.Font("Roboto", 0, 36));
		lTurno.setForeground(Color.white);
		lTurno.setText(lPuntosRojo.getText() + puntosRojo);
		lTurno.setText(lPuntosAmarillo.getText() + puntosAmarillo);
		pTurno.add(lTurno);
		pTablero.setLayout(new java.awt.GridLayout(filas, columnas));
		botones = new JButton[filas][columnas];
		llenarBotones(filas, columnas);
		this.setVisible(true);
		
	}

	/*
	 * FUNCIÓN PARA AÑADIR BOTONES AL TABLERO QUE TIENEN UN ACTIONPERFORMED
	 * PERSONALIZADO
	 */

	private void llenarBotones(int filas, int columnas) {
		for (int i = 0; i < filas; ++i) {
			for (int j = 0; j < columnas; ++j) {
				botones[i][j] = new JButton();
				botones[i][j].setName(i + " " + j);

				pTablero.add(botones[i][j]);

				final int iFinal = i, jFinal = j;
				botones[i][j].addActionListener(new java.awt.event.ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						FActionPerformed(e);

					}

				});
			}
		}

	}

	private void FActionPerformed(ActionEvent evt) {
		String[] casillas = ((JButton) evt.getSource()).getName().split("");

		int fila = partida.addToColumna(Integer.parseInt(casillas[1]));
		if (fila != -1) {
			if (!partida.hayGanador() && !partida.empate()) {
				escribirTurno();
				pintarCasilla(fila, Integer.parseInt(casillas[1]));
			} else if (partida.hayGanador()) {
				if (partida.getTurno() == partida.ROJO) {
					pintarCasilla(fila, Integer.parseInt(casillas[1]));
					puntosAmarillo++;
					lTurno.setText("Ha ganado el jugador Amarillo");
					lPuntosAmarillo.setText(" Amarillo: " + puntosAmarillo);
				} else {
					pintarCasilla(fila, Integer.parseInt(casillas[1]));
					puntosRojo++;
					lTurno.setText("Ha ganado el jugador Rojo");
					lPuntosRojo.setText(" Rojo: " + puntosRojo);
				}
				JOptionPane.showMessageDialog(null, "¡Felicidades, has ganado!");
				reiniciarPartida();
			}else {
				lTurno.setText("Empate");
				pintarCasilla(fila, Integer.parseInt(casillas[1]));
				JOptionPane.showMessageDialog(null, "Empate");
				reiniciarPartida();
			}
		}
	}
	
	private void reiniciarPartida() {
		partida.iniciarJuego();
		quitarFichas(6,7);
		escribirTurno();
	}
	
	private void escribirTurno() {
		if(partida.getTurno()== partida.ROJO) {
			lTurno.setText("Le toca al jugador: Rojo");
		}else {
			lTurno.setText("Le toca al jugador: Amarillo");
		}
	}
	
	private void pintarCasilla(int fila, int columna) {
		if(partida.getTurno()== partida.ROJO) {
			botones[fila][columna].setBackground(Color.yellow);
		}else {
			botones[fila][columna].setBackground(Color.red);
		}
	}
	
	private void quitarFichas(int fila, int columna) {
		for(int i = 0; i < filas; ++i) {
			for(int j = 0; j < columnas; ++j) {
				botones[i][j].setBackground(Color.white);
			}
		}
	}
	
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        pLateralIzquierda = new javax.swing.JPanel();
        lPuntosRojo = new javax.swing.JLabel();
        pLateralDerecha = new javax.swing.JPanel();
        lPuntosAmarillo = new javax.swing.JLabel();
        pTablero = new javax.swing.JPanel();
        pTurno = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pLateralIzquierda.setBackground(new java.awt.Color(255, 255, 255));

        lPuntosRojo.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        lPuntosRojo.setText("  Rojo: ");

        javax.swing.GroupLayout pLateralIzquierdaLayout = new javax.swing.GroupLayout(pLateralIzquierda);
        pLateralIzquierda.setLayout(pLateralIzquierdaLayout);
        pLateralIzquierdaLayout.setHorizontalGroup(
            pLateralIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLateralIzquierdaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lPuntosRojo, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );
        pLateralIzquierdaLayout.setVerticalGroup(
            pLateralIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLateralIzquierdaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lPuntosRojo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(478, Short.MAX_VALUE))
        );

        panelFondo.add(pLateralIzquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 540));

        pLateralDerecha.setBackground(new java.awt.Color(255, 255, 255));

        lPuntosAmarillo.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        lPuntosAmarillo.setText("  Amarillo: ");

        javax.swing.GroupLayout pLateralDerechaLayout = new javax.swing.GroupLayout(pLateralDerecha);
        pLateralDerecha.setLayout(pLateralDerechaLayout);
        pLateralDerechaLayout.setHorizontalGroup(
            pLateralDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lPuntosAmarillo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        pLateralDerechaLayout.setVerticalGroup(
            pLateralDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLateralDerechaLayout.createSequentialGroup()
                .addComponent(lPuntosAmarillo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 488, Short.MAX_VALUE))
        );

        panelFondo.add(pLateralDerecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 170, 540));

        pTablero.setBackground(new java.awt.Color(0, 102, 255));
        pTablero.setLayout(new java.awt.GridLayout(1, 0));
        panelFondo.add(pTablero, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 620, 410));

        pTurno.setBackground(new java.awt.Color(0, 51, 255));
        pTurno.setLayout(new java.awt.GridLayout(1, 0));
        panelFondo.add(pTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 620, 130));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }
    
    
}
