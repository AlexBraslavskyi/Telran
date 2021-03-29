package telran.spring.security;

import java.util.HashMap;

import org.springframework.stereotype.Service;
@Service
public class TitlesServiceImpl implements TitleService {
HashMap<Integer, String> titles = new HashMap<>();
	@Override
	public String getTitle(int id) {
		String title = titles.getOrDefault(id, "unknown");
		return title;
	}

	@Override
	public void addTitle(int id, String title) {
		String res = titles.putIfAbsent(id, title);
		if (res != null) {
			throw new RuntimeException(id + " already exists");
		}

	}

	@Override
	public void updateTitle(int id, String title) {
		if(!titles.containsKey(id)) {
			throw new RuntimeException(id + " doesn't exist");
		}
		titles.put(id, title);

	}

	@Override
	public void deleteTitle(int id) {
		String res = titles.remove(id);
		if (res == null) {
			throw new RuntimeException(id + " doesn't exist");
		}

	}

}
