package project.sys.util.embeds;

public enum PresetType {
    DEFAULT(0x2f3136),
    NOTIFICATION(0x5dadec),
    ERROR(0xdd2e44, "Error"),
    WARNING(0xffcc4d, "Warning"),
    SUCCESS(0x4ccd6a, "Success");

    private final int color;
    private final String title;

    PresetType(int color) {
        this(color, null);
    }
    PresetType(int color, String title) {
        this.color = color;
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }
}
