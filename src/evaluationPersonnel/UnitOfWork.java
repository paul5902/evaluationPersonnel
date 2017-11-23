package evaluationPersonnel;

import java.util.HashSet;
import java.util.Set;

public class UnitOfWork implements Observateur{
	Set<IDomainObject> dirty; 
	static UnitOfWork inst = null;
	public UnitOfWork() {
		dirty = new HashSet<IDomainObject>();
	}
	public static UnitOfWork getInstance() {
		if (inst == null)
			inst = new UnitOfWork();
		return inst;
	}
	public void action(IDomainObject o) {
		System.out.println("UnitOfWork: reception d'un evenement! on rajoute l'objet a la liste dirty");
		dirty.add(o);
	}
	void commit() {
		System.out.println("UnitOfWork: commit! pour chaque objet dirty, on enregistre les modifications...");
		Visiteur v = new Committer();
		for (IDomainObject o : dirty) {
			v.visiter(o);
		}
		System.out.println("UnitOfWork: Fin du commit.");
		dirty.clear();
	}
}
