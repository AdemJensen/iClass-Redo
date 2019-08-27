package top.chorg.kernel.api.vote;

public class VoteStatisticInfo {

    public int[] users;
    public int[][] userChoices;

    public VoteStatisticInfo(int[] users, int[][] userChoices) {
        this.users = users;
        this.userChoices = userChoices;
    }
}
