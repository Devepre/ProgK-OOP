package com.gmail.bicycle.dao.interfaces;

import java.io.IOException;

import com.gmail.bicycle.Group;

public interface GroupDAO {
	public void saveGroup(Group group) throws IOException;

	public Group loadGroup() throws IOException;
}
