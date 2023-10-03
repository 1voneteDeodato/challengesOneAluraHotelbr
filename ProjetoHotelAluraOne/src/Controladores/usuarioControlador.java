package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Modelo.Usuarios;
import Modelo.usuarios;
import views.Login;
import views.MenuUsuario;


public class usuarioControlador implements ActionListener {
	
	private Login vista;
	
	public usuarioControlador(Login vista) {
		this.vista = vista;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String nome = vista.getName();
		String senha = vista.getSenha();
		
		if(usuarios.validarUsuario(nome, senha)) {
			MenuUsuario menu = new MenuUsuario();
			menu.setVisible(true);
			vista.dispose();		
		}else {
			JOptionPane.showConfirmDialog(vista, "Cliente senha inválida");
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	

}
