package org.example;

import com.codeborne.selenide.Configuration;

public class BaseTest {

    public BaseTest() {
        Configuration.startMaximized = true;
        Configuration.holdBrowserOpen = true;
    }
}
