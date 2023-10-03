package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controladores.HospedeControlador;
import Controladores.ReservasControlador;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.List;

import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	private ReservasControlador reservasControl;
	private HospedeControlador hospedeControl;
	private ReservasView reservaVista;
	String reservas;
	String hospedes;
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public Buscar() {
		
		
		this.reservaVista = new ReservasView();
		this.hospedeControl = new HospedeControlador();
		this.reservasControl = new ReservasControlador();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		mostrarTabelaReservas();	
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		mostrarTabelasHospedes();
		
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Huéspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				limparTabela();
				
				if(txtBuscar.getText().equals("")) {
				
					mostrarTabelaReservas();
			
					mostrarTabelasHospedes();
				
				}else {
					
					mostrarTabelaReservasID();
				
					mostrarTabelasHospedesID();

				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				int filaReservas = tbReservas.getSelectedRow();
				int filaHospedes = tbHospedes.getSelectedRow();
				
				
				if(filaReservas >= 0 ) {
					
					atualizarReservas();
					
					limparTabela();
					
					mostrarTabelaReservas();
					
					mostrarHospedes();
					
					
				}
				
				else if(filaHospedes >= 0) {
					
					
					atualizarReservas();
										
					mostrarTabelaReservas();
					
					mostrarHospedes();
				}
					

			}
		});
		
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnDeletar = new JPanel();
		
		btnDeletar.addMouseListener (new MouseAdapter() {
			
			public void mouseClicked (MouseEvent e) {
				
				int filaReservas = tbReservas.getSelectedRow();
				
				int filaHospedes = tbHospedes.getSelectedRow();
				
				
				
				if(filaReservas >= 0) {
					
					reservas = tbReservas.getValueAt(filaReservas, 0).toString();
					
					int confirmar = JOptionPane.showConfirmDialog(null, "Apagar na Reserva ?");
					
					if(confirmar == JOptionPane.YES_OPTION) {
						
						String valor = tbReservas.getValueAt(filaReservas, 0).toString();
						
						reservasControl.deletar(Integer.valueOf(valor));
						
						JOptionPane.showM	essageDialog(contentPane, "Registro apagado com êxito! ");
						
						limparTabela();
						mostrarHospedes();
						mostrarTabelaReservas();
						mostrarTabelaHospedes();
						
					}
						
					}else if (filaHospedes >= 0) {
						
						hospedes = tbhospedes.getValueAt(filaHospedes, 0).toString();
						
						int confirmaH = JOptionPane.showConfirmDialog(null, "Deletar hospedes");
						
						if(confirmaH == JOptionPane.YES_OPTION) {
							
							String valor = tbHospedes.getValueAt(filaHospedes, 0).toString();
							hospedControl.eliminar(Integer.valueOf(valor));
							JOptionPane.showMessageDialog(contentPane, "Apagar hospedes");
							
							limparTabela();
							mostrarHospedes();
							mostrarTabelaReservas();
							mostrarTabelaHospedes();
							
							
						}
						
					}else {
						JOptionPane.showMessageDialog(null,"Eliminando erro");
					}
				
		}
				
			
		});
		
		
		
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}
	
	
	//CRUD - Reservas
	
	
	private List<reservas> MostrarReservas(){
		return this.reservasControl.Mostrar();
	}
	
	
	private List<reservas> buscarIDreservaa(){
		return this.reservasControl.buscar(txtBuscar.getText());
	}
	
	
	
	
	
	
 	private void mostrarTabelaReservas() {
		List<reservas> reserva = MostrarReservas();
		modelo.setRowCount(0);
		try {
			for(reservas reservas : reserva) {
				modelo.addRow(new Object[] {
				reservas.getId(), reservas.getDataEntrada(), reservas.getDataSaida(),reservas.getValor(),reservas.getFormaPagamento()
				});
				
			}
		}catch (Exception e ) {
			throw e;
		}
	}
	
	private void mostrarTabelaReservasID() {
		List<reservas> reserva = MostrarReservas();

		try {
			for(reservas reservas : reserva) {
				modelo.addRow(new Object[] {
				reservas.getId(), reservas.getDataEntrada(), reservas.getDataSaida(),reservas.getValor(),reservas.getFormaPagamento()
				});
				
			}
		}catch (Exception e ) {
			throw e;
		}
	}
	
	private void atualizarReservas() {
		
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumns()))
		.ifPresent(fila ->{
			
			LocalDate dataEntrada;
			LocalDate dataSaida;
			
			try {
				DateTimeFormatar dateFormat = Date]timeformatter.ofPattern("yyyy-MM-dd");
				dataEntrada = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedColumn(), 1).toString(), dateFormat);
				dataSaida = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedColumn(), 2).toString(1, dateFormat);
			}catch(DateTimeException e) {
				throw new RuntimeException(e);
				{
					this.reservaVista.limparValor();
					
					String valor = calcularValorReserva(dataEntrada, dataSaida);
					String formaPagamento = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
					Integer id = Integer.valueOf(modelo.getValveAt(tbReservas.getSelectdRow(), 0).toString());
					
					if(tbReservas.getSelectedColumn() ==0) {
						JOptionPane.showMessageDialog(this, "Não pode editar o ID");
					}else {
						this.reservasControl.atualizarReservas(dataEntrada, dataSaida, valor, formapagamento, id);
						JOptionPane.showMessageDialog(this, String.format("Registo modificado com êxito"));
						
					}
				});
			}
			
	
	
	public String calcularValorReserva(LocalDate dataEntrada, LocalDate dataSaida) {
		
		if(dataEntrada != null &&) dataSaida != null){
			
			int dias = (int) ChronoUnit.DAYS.between(dataEntrada, dataSaida);
			int noite = 50;
			int valor = dias * noite;
			
			return Integer.toString(valor);
			
		}else {
			return "";
		}
		
	}

	
	//crud hospedes
	
	private List<hospedes> mostrarHosped(){
		
		return this.hospedeControl.mostrarHosped();
	}
	
	
	private List<hospedes> buscarHospedId(){
		
		return this.hospedeControl.buscarHosped(txtBuscar.getText());
	}
	
	
	
	private void mostrarTabelasHospedes() {
		
	
		List<hospedes> hospedes = mostrarHospedes();
		modelo.setRowCount(0);
		try {
			for(Modelo.hospedes hospedes1 : hospedes) {
				modelo.addRow(new Object[] {
				hospedes1.getId(), hospedes1.getNome(), hospedes1.getSobrenome(),hospedes1.getDataNasciemnto(),hospedes1.getNacionalidade(),
				hospedes1.getTelefone(), hospedes1.getIdReserva()
				});
				
			}
		}catch (Exception e ) {
			throw e;
		}
	}
	
			
	private void mostrarTabelasHospedesID() {
		
		
		List<hospedes> hospedes = buscarHospedesId();
		modelo.setRowCount(0);
		try {
			for(Modelo.hospedes hospedes1 : hospedes) {
				modelo.addRow(new Object[] {
				hospedes1.getId(), hospedes1.getNome(), hospedes1.getSobrenome(),hospedes1.getDataNasciemnto(),hospedes1.getNacionalidade(),
				hospedes1.getTelefone(), hospedes1.getIdReserva()
				});
				
			}
		}catch (Exception e ) {
			throw e;
		}
	}
	
	
	private void atualizarHospedes() {
		
		Optional.ofNullable(modelo.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn())).
		ifPresentOrElse(filaHospedes ->{
			
			String nome = (String) modelo.getValueAt(tbHospedes.getSelectedRow(), 1);
			String sobrenome = (String) modelo.getValueAt(tbHospedes.getSelectedRow(), 2);
			Date dataNasciemnto = Date.valueOf(modelo.getValueAt(tbHospedes.getSelectedRow(), 3).toString());
			String nacionalidade = (String) modelo.getValueAt(tbHospedes.getSelectedRow(), 4);
			String telefone = (String) modelo.getValueAt(tbHospedes.getSelectedRow(), 5);
			Integer idReserva = Integer.valueOf(modelo.getValueAt(tbHospedes.getSelectedRow(), 6).toString());
			Integer id = Integer.valueOf(modelo.getValueAt(tbHospedes.getSelectedRow(), 
					0).toString());
			
			if(tbHospedes.getSelectedColumn() == 6) {
				JOptionPane.showMessageDialog(this,"Não se pode modificar o Id");
			}else {
				
				this.hospedeControl.atualizarH(nome, sobrenome, null, nacionalidade, telefone, idReserva, id);
			JOptionPane.showMessageDialog(this, String.format("Registro modificado com êxito"));
			
			}
			
				
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, seu animal"));
	}
	
	
	private void limparTabela() {
		
		((DefaultTabelaModel) tbHospedes.getModel()).setRowCont(0));
		((DefaultTabelaModel) tbReservas.getModel()).setRowCont(0));
	}
	
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
