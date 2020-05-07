package aplicacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.Statement;

import db.DB;

public class Programa {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conexao = null;
		PreparedStatement st = null;
		
		try {
			conexao = DB.getConnection();
			
			// INSERINDO UM NOVO VENDEDOR
			/*
			st = conexao.prepareStatement(
					"INSERT INTO vendedor "
					+ "(Nome, Email, DataDeNascimento, SalarioBase, IdDepartamento) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS); // utilizado para receber a chave da linha inserida
			
			st.setString(1, "Carl Purple");
			st.setString(2, "carl@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4,  3000.0);
			st.setInt(5, 4);
			*/
			
			// INSERINDO DOIS NOVOS DEPARTAMENTOS
			st = conexao.prepareStatement("insert into departamento (Nome) values ('D1'),('D2')",
					Statement.RETURN_GENERATED_KEYS);
			
			int linhasAfetadas = st.executeUpdate();
			
			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Linha inserida, Id = " + id);
				}
			}
			else {
				System.out.println("Nenhuma linha afetada!");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
/*		catch (ParseException e) {
			e.printStackTrace();
		}
*/		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
