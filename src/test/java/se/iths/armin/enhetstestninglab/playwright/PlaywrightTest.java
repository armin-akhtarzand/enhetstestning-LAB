package se.iths.armin.enhetstestninglab.playwright;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class PlaywrightTest {

    private static Playwright playwright;
    private static Browser browser;

    private Page page;
    private static final String base_url = "http://localhost:8080/";

    @BeforeAll
    public static void setUpAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }

    @AfterAll
    public static void closeAll() {
        browser.close();
        playwright.close();

    }

    @BeforeEach
    public void setUp() {
        page = browser.newPage();
    }

    @AfterEach
    public void closeDown() {
        page.close();
    }

    @Test
    public void testPageHasTitleATM() {
        // Testar så att sidan har en titel
        page.navigate(base_url);
        assertThat(page).hasTitle("ATM");
    }

    @Test
    public void testAccountBalanceStartsWithZero() {
        // Testar att saldot börjar på 0
        page.navigate(base_url);
        Locator locator = page.locator("#balance");
        assertThat(locator).hasText("0");
    }

    @Test
    public void testPageHasHeaderATM() {
        // Testar att rubriken syns i sidan
        page.navigate(base_url);
        Locator locator = page.locator("h1");
        assertThat(locator).isVisible();

    }
}
