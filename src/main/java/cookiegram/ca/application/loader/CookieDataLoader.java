package cookiegram.ca.application.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import cookiegram.ca.application.model.Cookie;
import cookiegram.ca.application.repository.CookieRepository;

/**
 * Loads initial cookie data into the database upon application startup.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 * 
 * This class implements the CommandLineRunner interface to ensure that
 * predefined cookies are inserted into the database when the application starts.
 */
@Component
public class CookieDataLoader implements CommandLineRunner {

    /**
     * Repository for managing cookie-related database operations.
     */
    @Autowired
    private CookieRepository cookieRepository;

    /**
     * Populates the database with predefined cookie entries if no records exist.
     *
     * @param args Command-line arguments (not used in this context)
     * @throws Exception if an error occurs during database operations
     */
    @Override
    public void run(String... args) throws Exception {
        if (cookieRepository.count() == 0) {
            cookieRepository.save(new Cookie("Austrian Linzer Cookies", 2.99, "/images/AustrianLinzerCookies.jpg", 100));
            cookieRepository.save(new Cookie("Chocolate Chip Cookies", 5.99, "/images/ChocolateChipCookies.jpg", 100));
            cookieRepository.save(new Cookie("Gingerbread Cookies", 2.40, "/images/GingerbreadCookies.jpg", 100));
            cookieRepository.save(new Cookie("Lime Cookies", 4.00, "/images/LimeCookies.jpg", 100));
            cookieRepository.save(new Cookie("Oatmeal Raisin Cookie", 1.99, "/images/OatmealRaisinCookie.jpg", 100));
            cookieRepository.save(new Cookie("PeanutButterBlossoms Cookie", 3.39, "/images/PeanutButterBlossoms.jpg", 100));
            cookieRepository.save(new Cookie("Pineapple Semi Sweet", 2.99, "/images/PineappleSemiSweet.jpg", 100));
            cookieRepository.save(new Cookie("Pistachio Cookies", 2.99, "/images/PistachioCookies.jpg", 100));
            cookieRepository.save(new Cookie("Raspberry Cookies Icing", 2.99, "/images/RaspberryCookiesIcing.jpg", 100));
            cookieRepository.save(new Cookie("Sugar Cookie", 2.50, "/images/SugarCookiee.jpg", 100));
            cookieRepository.save(new Cookie("White Chocolate Cookies", 1.99, "/images/WhiteChocolateCookies.jpg", 100));
            cookieRepository.save(new Cookie("White Chocolate Strawberry", 3.99, "/images/WhiteChocolateStrawberry.jpg", 100));
        }
    }
}
