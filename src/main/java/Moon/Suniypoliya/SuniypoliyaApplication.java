package Moon.Suniypoliya;

import Moon.Suniypoliya.entity.Lavozim;
import Moon.Suniypoliya.entity.TulovTuri;
import Moon.Suniypoliya.entity.User;
import Moon.Suniypoliya.entity.XabarTuri;
import Moon.Suniypoliya.repository.TulovTuriRepo;
import Moon.Suniypoliya.repository.UserRepository;
import Moon.Suniypoliya.repository.XabarTuriRepo;
import Moon.Suniypoliya.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SuniypoliyaApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SuniypoliyaApplication.class, args);
	}
	@Autowired
	JwtUtil jwtUtil;

	@Value("${system.admin.login}")
	private String login;

	@Value("${system.admin.parol}")
	private String parol;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	XabarTuriRepo xabarTuriRepo;
	@Autowired
	TulovTuriRepo tulovTuriRepo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Optional<User> ur = userRepository.findByUsername(login);
		Optional<User> people=userRepository.findByUsername("people1234");
		Optional<User> creator=userRepository.findByUsername("creator2021");
		if(!ur.isPresent()){
			 User u = new User();
			u.setId(1l);
			u.setName("boss1234");
			u.setUsername(login);
			u.setPassword(passwordEncoder.encode(parol));
			Set<Lavozim> lavozimlar = new HashSet<>();
			lavozimlar.add(Lavozim.BOSS);
			u.setAktiv(1l);
			u.setNumber("+998917777777");
			u.setQushilganVaqti(LocalDateTime.now());
			u.setLavozimlar(lavozimlar);
			userRepository.save(u);
		}
		xabarTuriRepo.save(new XabarTuri(2l,"Buyurtma To'liq Qabul Qilindi",1));
		xabarTuriRepo.save(new XabarTuri(3l,"Buyurtma Bepul Qabul Qilindi",1));
		xabarTuriRepo.save(new XabarTuri(4l,"Buyurtma Arzon Qabul Qilindi",1));
		xabarTuriRepo.save(new XabarTuri(5l,"Buyurtma Qimmat Qabul Qilindi",1));
		xabarTuriRepo.save(new XabarTuri(6l,"Maosh",1));
		xabarTuriRepo.save(new XabarTuri(7l,"Soliq",1));
		xabarTuriRepo.save(new XabarTuri(8l,"Boshqa harajatlar",1));
		xabarTuriRepo.save(new XabarTuri(9l,"To'liq to'landi",1));
		xabarTuriRepo.save(new XabarTuri(10l,"Bir qismi tulandi",1));
		tulovTuriRepo.save(new TulovTuri(11l,"Maosh",1));
		tulovTuriRepo.save(new TulovTuri(12l,"Soliq",1));
		tulovTuriRepo.save(new TulovTuri(13l,"Boshqa harajatlar",1));
		xabarTuriRepo.save(new XabarTuri(14l,"Mijoz qo'shishdi",1));
		xabarTuriRepo.save(new XabarTuri(15l,"Mijoz o'chirildi",1));
		xabarTuriRepo.save(new XabarTuri(16l,"Buyurtma bekor qilindi",1));
		xabarTuriRepo.save(new XabarTuri(17l,"Poliyaga oid.",1));
		if(!people.isPresent()){
			User u = new User();
			u.setId(18l);
			u.setName("people1234");
			u.setUsername("people1234");
			u.setPassword(passwordEncoder.encode("people1234"));
			Set<Lavozim> lavozimlar = new HashSet<>();
			lavozimlar.add(Lavozim.PEOPLE);
			u.setAktiv(1l);
			u.setNumber("");
			u.setQushilganVaqti(LocalDateTime.now());
			u.setLavozimlar(lavozimlar);
			userRepository.save(u);
		}
		if(!creator.isPresent()){
			User u = new User();
			u.setId(19l);
			u.setName("creator2021");
			u.setUsername("creator2021");
			u.setPassword(passwordEncoder.encode("creator2021"));
			Set<Lavozim> lavozimlar = new HashSet<>();
			lavozimlar.add(Lavozim.CREATOR);
			u.setAktiv(1l);
			u.setNumber("");
			u.setQushilganVaqti(LocalDateTime.now());
			u.setLavozimlar(lavozimlar);
			userRepository.save(u);
		}
	}
}
