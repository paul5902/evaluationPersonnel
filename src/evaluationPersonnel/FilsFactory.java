package evaluationPersonnel;

import java.sql.SQLException;
import java.util.List;

public class FilsFactory implements Factory<List<Personne>> {
	
	private int idPersonne;
	private PersonneMapper mapper;
	
	public FilsFactory(int idPersonne,PersonneMapper mapper) {
		this.idPersonne = idPersonne;
		this.mapper = mapper;
	}

	@Override
	public List<Personne> create() {
		try {
			return mapper.getLesFils(this.idPersonne);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
