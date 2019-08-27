package top.chorg.kernel.api.announce;

public class EditAnnounceInfo {

    public int id;
    public String title, compiledContent;

    public EditAnnounceInfo(int id, String title, String compiledContent) {
        this.id = id;
        this.title = title;
        this.compiledContent = compiledContent;
    }
}
