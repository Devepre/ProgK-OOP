package com.gmail.bicycle.dao.realization;

import java.io.IOException;

import com.gmail.bicycle.dao.factories.JSONDAOFactory;
import com.gmail.bicycle.dao.interfaces.GroupDAO;
import com.gmail.bicycle.json.JsonParser;
import com.gmail.bicycle.model.Group;

public class JSONGroupDAO implements GroupDAO {
		
	public JSONGroupDAO() {
		super();	
	}

	@Override
	public void saveGroup(Group group) throws IOException {
		JsonParser jsonParser = JSONDAOFactory.createConnection();
		try {
			jsonParser.serialize(group, JSONDAOFactory.FILE_NAME);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Group loadGroup() throws IOException {
		Group group = null;
		
		JsonParser jsonParser = JSONDAOFactory.createConnection();
		try {
			group = (Group) jsonParser.deserialize(Group.class, JSONDAOFactory.FILE_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return group;
	}

}
