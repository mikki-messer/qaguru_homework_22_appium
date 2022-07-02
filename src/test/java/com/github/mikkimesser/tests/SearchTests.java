package com.github.mikkimesser.tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class SearchTests extends TestBase {
    @Test
    @Owner("mikki_messer")
    @DisplayName("display search results test")
    void searchTest() {
        back();
        step("Type search", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    @Owner("mikki_messer")
    @DisplayName("open article from search results test")
    void openArticleFromSearchTest() {
        back();
        step("Type search", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).shouldBe(visible, Duration.ofSeconds(25));
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Porsche 959");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));

        step("Click search result", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click());

        step("Check article opened, header image is displayed", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/view_page_header_image")).shouldBe(visible, Duration.ofSeconds(25)));
    }

    @Test
    @Owner("mikki_messer")
    void testInitialScreenSequence() {
        step("Check first onboarding screen content", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .shouldHave(text("The Free Encyclopedia\n" +
                            "…in over 300 languages"));
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Check second onboarding screen content", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .shouldHave(text("New ways to explore"));
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Check third onboarding screen content", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .shouldHave(text("Reading lists with sync"));
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Check fourth onboarding screen content", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .shouldHave(text("Send anonymous data"));
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).shouldBe(visible);
        });
    }
}