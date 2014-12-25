import com.innoppl.outreach.AppContext;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.Messages;
import java.util.Locale;
import org.apache.commons.lang.LocaleUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Jerald Mejarla
 */
@ContextConfiguration("/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ResourceBundleEntryTest {

    final Locale[] locale = new Locale[]{LocaleUtils.toLocale("en")};

    public ResourceBundleEntryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void encode() {
        String password = "sal#1234";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        //System.out.println(hashedPassword);
    }

    @Test
    public void verifyResourceBundles() {
        StringBuilder errorBuilder = new StringBuilder();

        final Errors[] errorValues = Errors.values();
        for (Locale y : locale) {
            for (Errors x : errorValues) {
                try {
                    String message = AppContext.getApplicationContext().getMessage(x.name(),
                            new Object[]{}, y);
                    if (message == null) {
                        errorBuilder.append("Error Resouce Bundle with name [")
                                .append(x.name())
                                .append("] not available for Locale [")
                                .append(y.toString())
                                .append("]")
                                .append("\n");
                    }
                } catch (Exception ex) {
                    errorBuilder.append("Error Resouce Bundle with name [")
                            .append(x.name())
                            .append("] not available for Locale [")
                            .append(y.toString())
                            .append("]")
                            .append("\n");
                }
            }
        }

        final Messages[] messageValues = Messages.values();
        for (Locale y : locale) {
            for (Messages x : messageValues) {
                try {
                    String message = AppContext.getApplicationContext().getMessage(x.name(),
                            new Object[]{}, y);
                    if (message == null) {
                        errorBuilder.append("Message Resouce Bundle with name [")
                                .append(x.name())
                                .append("] not available for Locale [")
                                .append(y.toString())
                                .append("]")
                                .append("\n");
                    }
                } catch (Exception ex) {
                    errorBuilder.append("Message Resouce Bundle with name [")
                            .append(x.name())
                            .append("] not available for Locale [")
                            .append(y.toString())
                            .append("]")
                            .append("\n");
                }
            }
        }

        if (errorBuilder.length() > 0) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("      Resource Bundle Missing");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(errorBuilder.toString());
            fail("\n" + errorBuilder.toString());
        }
    }
}