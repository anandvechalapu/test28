
@SpringBootApplication
public class ForgetPasswordApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForgetPasswordApplication.class, args);
	}

}

@Service
public class PasswordResetService {

	@Autowired
	private UserRepository userRepository;

	public void resetPassword(String emailAddress, String newPassword) {
		User user = userRepository.findByEmailAddress(emailAddress);
		user.setPassword(newPassword);
		userRepository.save(user);
	}

}

@RestController
public class ForgetPasswordController {

	@Autowired
	private PasswordResetService passwordResetService;

	@PostMapping("/forgetPassword")
	public void resetPassword(@RequestParam String emailAddress,@RequestParam String newPassword) {
		passwordResetService.resetPassword(emailAddress, newPassword);
	}

}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailAddress(String emailAddress);

}