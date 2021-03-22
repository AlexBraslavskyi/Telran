package telran.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TitlesSecurityApplication {

	@Autowired
TitleService titleService;
	public static void main(String[] args) {
		SpringApplication.run(TitlesSecurityApplication.class, args);
	}
	
	
	@GetMapping(value = ApiConstants.URL)
	String getTitle(@PathVariable(ApiConstants.ID) int id) {
		return titleService.getTitle(id);
	}
	
	@PostMapping(ApiConstants.URL)
	String addTitle(@PathVariable(ApiConstants.ID)int id, @RequestBody String title) {
		titleService.addTitle(id, title);
		return title;
	}
	
	@PutMapping(ApiConstants.URL)
	String updateTitle(@PathVariable(ApiConstants.ID)int id, @RequestBody String title) {
		titleService.updateTitle(id, title);
		return title;
	}
	
	@DeleteMapping(value = ApiConstants.URL)
	String deleteTitle(@PathVariable(ApiConstants.ID) int id) {
	titleService.deleteTitle(id);
	return id + " was deleted";
	}
	
}
