package projectgui_1;

public class TenantLetter {

    private String id;

    public TenantLetter(Space space) {
        this.id = space.getId();
    }

    public String getId() {
        return id;
    }

}
