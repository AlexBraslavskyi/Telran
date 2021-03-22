package telran.spring.security;

public interface TitleService {

	String getTitle(int id);
	void addTitle(int id, String title);
	void updateTitle(int id, String title);
	void deleteTitle(int id);
}
