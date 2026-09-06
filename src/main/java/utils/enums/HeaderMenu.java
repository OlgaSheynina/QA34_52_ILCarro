package utils.enums;

public enum HeaderMenu {
    LOG0("//img[@alt='logo']"),
    SEARCH("//a[text()=' Search ']"),
    LET_THE_CAR_WORK("//a[@href='/let-car-work']"),
    TERMS_OF_USE("//a[@href='/terms-of-use']"),
    SING_UP("//a[text()=' Sign up ']"),
    LOGIN("//a[@href='/login?url=%2Fsearch']"),
    LOGOUT("//a[text()=' Logout ']"),
    DELETE_ACCOUNT("//a[text()='Delete account']");

    private final String locator;

    HeaderMenu(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
