package dao.realization;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.gmail.bicycle.Student;

import dao.factories.PlainTextDAOFactory;
import dao.interfaces.StudentDAO;

//PlainTextStudentDAO implementation of the 
//StudentDAO interface. This class can contain all
//PlainText specific code. 
//The client is thus shielded from knowing 
//these implementation details.
public class PlainTextStudentDAO implements StudentDAO {

	public PlainTextStudentDAO() {
		super();
	}

	@Override
	public void saveStudent(Student student) throws IOException {
		try (RandomAccessFile raf = PlainTextDAOFactory.createConnection()) {
			if (student != null) {
				raf.seek(raf.length());
				raf.writeUTF(student.getName());
				raf.writeUTF(student.getSurname());
				raf.writeUTF(student.getDateOfBirth().toString());
				raf.writeUTF(String.valueOf(student.isMale()));
				raf.writeUTF(String.valueOf(student.getStipend()));
				raf.writeUTF(System.lineSeparator());
			}

		} catch (IOException e) {
			throw e;
		}

	}

	@Override
	public Student loadStudent(int position) throws IOException {
		Student student = null;
		try (RandomAccessFile raf = PlainTextDAOFactory.createConnection()) {
			for (int i = 0; i < position; i++) {
				String tempStr = raf.readLine();
			}
			if (raf.getFilePointer() == raf.length()) {
				return null;
			}
			String stName = raf.readUTF();
			String stSurname = raf.readUTF();
			String stDate = raf.readUTF();
			String stIsMan = raf.readUTF();
			String stStipend = raf.readUTF();

			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'EET' yyyy", Locale.ENGLISH);
			Date stBirth = sdf.parse(stDate);

			student = new Student(stName, stSurname, stBirth, Boolean.valueOf(stIsMan), Double.valueOf(stStipend));

		} catch (IOException e) {
			throw e;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return student;
	}

}
