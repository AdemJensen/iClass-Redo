package top.chorg.kernel.api.vote;

public class VoteListQueryInfo {

    public int curPage, totalPage;
    public VoteListInfo[] infos;

    public VoteListQueryInfo(int curPage, int totalPage, VoteListInfo...infos) {
        this.curPage = curPage;
        this.totalPage = totalPage;
        this.infos = infos;
    }
}
