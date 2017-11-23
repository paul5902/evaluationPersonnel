package evaluationPersonnel;

import java.sql.SQLException;

public class Committer extends Visiteur {
	public void visiter(Personne p) {
		System.out.println("Committer: On visite un objet de type Personne!");
		try {
			PersonneMapper.getInstance().update(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
}
