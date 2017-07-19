package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import com.esri.map.GroupLayer;
import com.esri.map.JMap;

import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanasDinamicas extends JFrame{
	Botones botones = new Botones();
	EventoMapa eventoMapa = new EventoMapa();
	Principal principal = new Principal();
	
	String auxNameBaseLayer="";
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanasDinamicas frame = new VentanasDinamicas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanasDinamicas() {
		setTitle("Agregar Mapa Base");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		this.setUndecorated(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{25, 0, 25, 25, 0, 25, 25, 0, 0};
		gbl_panel.rowHeights = new int[]{25, 0, 0, 25, 25, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblAux = new JLabel("");
		lblAux.setVisible(false);
		GridBagConstraints gbc_lblAux = new GridBagConstraints();
		gbc_lblAux.insets = new Insets(0, 0, 0, 5);
		gbc_lblAux.gridx = 1;
		gbc_lblAux.gridy = 7;
		panel.add(lblAux, gbc_lblAux);
		
		JLabel lblImagery = new JLabel("");
		lblImagery.setIcon(new ImageIcon(VentanasDinamicas.class.getResource("/imagenes/img/ImageryR.jpg")));
		GridBagConstraints gbc_lblImagery = new GridBagConstraints();
		gbc_lblImagery.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagery.gridx = 1;
		gbc_lblImagery.gridy = 1;
		botones.choiceBaseLayer(lblImagery, lblAux, 0);
		panel.add(lblImagery, gbc_lblImagery);
		
		JLabel lblPhysical = new JLabel("");
		lblPhysical.setIcon(new ImageIcon(VentanasDinamicas.class.getResource("/imagenes/img/PhysicalR.jpg")));
		GridBagConstraints gbc_lblPhysical = new GridBagConstraints();
		gbc_lblPhysical.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhysical.gridx = 4;
		gbc_lblPhysical.gridy = 1;
		botones.choiceBaseLayer(lblPhysical, lblAux, 1);
		panel.add(lblPhysical, gbc_lblPhysical);
		
		JLabel lblShadedRelief = new JLabel("");
		lblShadedRelief.setIcon(new ImageIcon(VentanasDinamicas.class.getResource("/imagenes/img/ShadedReliefR.jpg")));
		GridBagConstraints gbc_lblShadedRelief = new GridBagConstraints();
		gbc_lblShadedRelief.insets = new Insets(0, 0, 5, 0);
		gbc_lblShadedRelief.gridx = 7;
		gbc_lblShadedRelief.gridy = 1;
		botones.choiceBaseLayer(lblShadedRelief, lblAux, 2);
		panel.add(lblShadedRelief, gbc_lblShadedRelief);
		
		JLabel lblImagen = new JLabel("Imagen");
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagen.gridx = 1;
		gbc_lblImagen.gridy = 2;
		botones.choiceBaseLayer(lblImagery, lblAux, 0);
		panel.add(lblImagen, gbc_lblImagen);
		
		JLabel lblFsico = new JLabel("Físico");
		GridBagConstraints gbc_lblFsico = new GridBagConstraints();
		gbc_lblFsico.insets = new Insets(0, 0, 5, 5);
		gbc_lblFsico.gridx = 4;
		gbc_lblFsico.gridy = 2;
		botones.choiceBaseLayer(lblFsico, lblAux, 1);
		panel.add(lblFsico, gbc_lblFsico);
		
		JLabel lblRelieveSombreado = new JLabel("Relieve Sombreado");
		GridBagConstraints gbc_lblRelieveSombreado = new GridBagConstraints();
		gbc_lblRelieveSombreado.insets = new Insets(0, 0, 5, 0);
		gbc_lblRelieveSombreado.gridx = 7;
		gbc_lblRelieveSombreado.gridy = 2;
		botones.choiceBaseLayer(lblRelieveSombreado, lblAux, 2);
		panel.add(lblRelieveSombreado, gbc_lblRelieveSombreado);
		
		JLabel lblStreet = new JLabel("");
		lblStreet.setIcon(new ImageIcon(VentanasDinamicas.class.getResource("/imagenes/img/StreetR.jpg")));
		GridBagConstraints gbc_lblStreet = new GridBagConstraints();
		gbc_lblStreet.insets = new Insets(0, 0, 5, 5);
		gbc_lblStreet.gridx = 1;
		gbc_lblStreet.gridy = 5;
		botones.choiceBaseLayer(lblStreet, lblAux, 3);
		panel.add(lblStreet, gbc_lblStreet);
		
		JLabel lblTerrain = new JLabel("");
		lblTerrain.setIcon(new ImageIcon(VentanasDinamicas.class.getResource("/imagenes/img/TerrainBaseR.jpg")));
		GridBagConstraints gbc_lblTerrain = new GridBagConstraints();
		gbc_lblTerrain.insets = new Insets(0, 0, 5, 5);
		gbc_lblTerrain.gridx = 4;
		gbc_lblTerrain.gridy = 5;
		botones.choiceBaseLayer(lblTerrain, lblAux, 4);
		panel.add(lblTerrain, gbc_lblTerrain);
		
		JLabel lblTopographic = new JLabel("");
		lblTopographic.setIcon(new ImageIcon(VentanasDinamicas.class.getResource("/imagenes/img/TopographicR.jpg")));
		GridBagConstraints gbc_lblTopographic = new GridBagConstraints();
		gbc_lblTopographic.insets = new Insets(0, 0, 5, 0);
		gbc_lblTopographic.gridx = 7;
		gbc_lblTopographic.gridy = 5;
		botones.choiceBaseLayer(lblTopographic, lblAux, 5);
		panel.add(lblTopographic, gbc_lblTopographic);
		
		JLabel lblCalles = new JLabel("Calles");
		GridBagConstraints gbc_lblCalles = new GridBagConstraints();
		gbc_lblCalles.insets = new Insets(0, 0, 5, 5);
		gbc_lblCalles.gridx = 1;
		gbc_lblCalles.gridy = 6;
		botones.choiceBaseLayer(lblCalles, lblAux, 3);
		panel.add(lblCalles, gbc_lblCalles);
		
		JLabel lblTerreno = new JLabel("Terreno");
		GridBagConstraints gbc_lblTerreno = new GridBagConstraints();
		gbc_lblTerreno.insets = new Insets(0, 0, 5, 5);
		gbc_lblTerreno.gridx = 4;
		gbc_lblTerreno.gridy = 6;
		botones.choiceBaseLayer(lblTerreno, lblAux, 4);
		panel.add(lblTerreno, gbc_lblTerreno);
		
		JLabel lblTopogrfico = new JLabel("Topográfico");
		GridBagConstraints gbc_lblTopogrfico = new GridBagConstraints();
		gbc_lblTopogrfico.insets = new Insets(0, 0, 5, 0);
		gbc_lblTopogrfico.gridx = 7;
		gbc_lblTopogrfico.gridy = 6;
		botones.choiceBaseLayer(lblTopogrfico, lblAux, 5);
		panel.add(lblTopogrfico, gbc_lblTopogrfico);
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Agregar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eventoMapa.changeBaseLayer(principal.getGroupLayer(), principal.getJmap(), lblAux.getText());
				dispose();
			}
		});
		btnAdd.setHorizontalAlignment(SwingConstants.RIGHT);
		panelBotones.add(btnAdd);
		btnAdd.setIcon(new ImageIcon(VentanasDinamicas.class.getResource("/com/esri/client/toolkit/images/LayerPolygon16.png")));
		btnAdd.setAlignmentY(RIGHT_ALIGNMENT);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setHorizontalAlignment(SwingConstants.RIGHT);
		panelBotones.add(btnCancel);
		btnCancel.setIcon(new ImageIcon(VentanasDinamicas.class.getResource("/imagenes/img/delete.png")));
		
		JLabel lblAgregarMapaBase = new JLabel("Agregar Mapa Base");
		lblAgregarMapaBase.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarMapaBase.setIcon(new ImageIcon(VentanasDinamicas.class.getResource("/com/esri/client/toolkit/images/LayerPolygon16.png")));
		lblAgregarMapaBase.setBackground(Color.WHITE);
		contentPane.add(lblAgregarMapaBase, BorderLayout.NORTH);
	}
}