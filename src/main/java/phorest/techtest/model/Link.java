package phorest.techtest.model;

public class Link {

    private String href;
    private String rel;
    private boolean templated;

    public Link(String href, String rel, boolean templated) {
        this.href = href;
        this.rel = rel;
        this.templated = templated;
    }

    public String getHref() {
        return href;
    }

    public String getRel() {
        return rel;
    }

    public boolean isTemplated() {
        return templated;
    }
}
