package persistance;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import exceptions.LoadException;
import model.Save;

public class GameLoader {

	public GameLoader() {
		
	}
	
	public Save load(String filePath) throws LoadException {
		Save result = null;
		try {
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			result = (Save) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoadException();
		}
		return result;
	}

}
