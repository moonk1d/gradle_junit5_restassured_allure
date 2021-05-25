package api.configuration;

public enum Environment {
    PROD("https://api.thecatapi.com"),
    TEST("https://api.test.thecatapi.com");

    private String url;

    Environment(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
