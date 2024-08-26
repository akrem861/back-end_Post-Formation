package tn.post.client;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tn.post.client.enumerate.Permission;
import tn.post.client.model.Role;
import tn.post.client.repository.RoleRepository;

import java.lang.reflect.Array;

@SpringBootApplication
@EnableFeignClients
@AllArgsConstructor
public class ClientApplication implements CommandLineRunner {

	private final RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		if(!roleRepository.existsByName("admin"))
			roleRepository.save(new Role(1L,"admin", Permission.ListAdminPermission(),null));
		if(!roleRepository.existsByName("formateur"))
			roleRepository.save(new Role(2L,"formateur", Permission.ListFormateurPermission(),null));
		if(!roleRepository.existsByName("candidat"))
			roleRepository.save(new Role(3L,"candidat", Permission.ListCandiatPermission(),null));
	}
}
