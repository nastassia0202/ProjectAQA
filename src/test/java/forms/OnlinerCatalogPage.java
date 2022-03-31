package forms;

public enum OnlinerCatalogPage {
    MOBILE,
    NOTEBOOK,
    PHOTO,
    TV;

    public static final String CATALOG_URL = "https://catalog.onliner.by/";

    private final String postfix;

    OnlinerCatalogPage(){
        this.postfix = name().toLowerCase();
    }

    OnlinerCatalogPage(String postfix){
        this.postfix = postfix;
    }

    public String getAddress() {
        return CATALOG_URL.concat(postfix);
    }
}
