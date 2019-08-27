package top.chorg.kernel.api.vote;

public class MakeVoteInfo {

    int id;
    int[] choices;

    public MakeVoteInfo(int id, int[] choices) {
        this.id = id;
        this.choices = choices;
    }
}
