package dao.factories;

import dao.interfaces.GroupDAO;
import dao.interfaces.StudentDAO;

public abstract class DAOFactory {
	// List of DAO types supported by the factory
	public static final int PLAIN_TEXT = 1;
	public static final int JSON = 2;

	// There will be a method for each DAO that can be
	// created. The concrete factories will have to
	// implement these methods.
	public abstract StudentDAO getStudentDAO();

	public abstract GroupDAO getGroupDAO();

	public static DAOFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
		case PLAIN_TEXT:
			return new PlainTextDAOFactory();
		case JSON:
			return new JSONDAOFactory();
		default:
			return null;
		}
	}
}