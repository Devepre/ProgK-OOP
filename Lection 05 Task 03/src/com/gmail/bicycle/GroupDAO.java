package com.gmail.bicycle;

import java.io.IOException;

public interface GroupDAO {
	public void saveGroup(Group group) throws IOException;

	public Group loadGroup() throws IOException;
}
