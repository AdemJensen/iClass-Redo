package top.chorg.kernel.api.announce;

public class AnnounceListQueryInfo {

    public int curPage, totalPage;
    public AnnouncementListInfo[] infos;

    public AnnounceListQueryInfo(int curPage, int totalPage, AnnouncementListInfo...infos) {
        this.curPage = curPage;
        this.totalPage = totalPage;
        this.infos = infos;
    }
}
