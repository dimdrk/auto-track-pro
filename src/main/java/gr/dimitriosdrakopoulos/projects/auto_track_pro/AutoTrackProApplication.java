package gr.dimitriosdrakopoulos.projects.auto_track_pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AutoTrackProApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoTrackProApplication.class, args);
	}

}
