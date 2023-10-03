package Modelo;




import java.sql.Date;
import java.time.LocalDate;



	public class hospedes {
		
		private Integer id;
		private String nome;
		private String sobrenome;
		private LocalDate dataNasciemnto;
		private String nacionalidade;
		private String telefone;
		private Integer idReserva;
		
		
		public hospedes (String nome, String sobrenome, LocalDate dataNasciemnto, String nacionalidade, String telefone, Integer idReserva) {
			
			super();
			
			this.nome = nome;
			this.sobrenome = sobrenome;
			this.dataNasciemnto = dataNasciemnto;
			this.nacionalidade = nacionalidade;
			this.telefone = telefone;
			this.idReserva = idReserva;
			
		}
		
		

		public hospedes(Integer id, String nome, String sobrenome, LocalDate dataNasciemnto, String nacionalidade,
				String telefone, int idReserva) {
			
			super();
			
			this.id = id;
			this.nome = nome;
			this.sobrenome = sobrenome;
			this.dataNasciemnto = dataNasciemnto;
			this.nacionalidade = nacionalidade;
			this.telefone = telefone;
			this.idReserva = idReserva;
			
		}



		public Integer getId() {
			return id;
		}



		public void setId(Integer id) {
			this.id = id;
		}



		public String getNome() {
			return nome;
		}



		public void setNome(String nome) {
			this.nome = nome;
		}



		public String getSobrenome() {
			return sobrenome;
		}



		public void setSobrenome(String sobrenome) {
			this.sobrenome = sobrenome;
		}



		public LocalDate getDataNasciemnto() {
			return dataNasciemnto;
		}



		public void setDataNasciemnto(LocalDate dataNasciemnto) {
			this.dataNasciemnto = dataNasciemnto;
		}



		public String getNacionalidade() {
			return nacionalidade;
		}



		public void setNacionalidade(String nacionalidade) {
			this.nacionalidade = nacionalidade;
		}



		public String getTelefone() {
			return telefone;
		}



		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}



		public Integer getIdReserva() {
			return idReserva;
		}



		public void setIdReserva(Integer idReserva) {
			this.idReserva = idReserva;
		}


	}

