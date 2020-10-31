package org.example;

public abstract class BasePage {

    public BasePage() {
        waitForPageToFinishLoading();
        init();
    }

    public abstract void waitForPageToFinishLoading();

    public abstract void init();
}
