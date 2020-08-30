package spring.security.model;

public enum Permission {
    EDITORS_READ("editors:read"),
    EDITORS_WRITE("editors:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}