package top.chorg.kernel.database;

import top.chorg.kernel.api.vote.*;
import top.chorg.support.TimeUtils;

import java.util.Date;

public class VoteData {

    public VoteInfo getVoteInfo(int id) {
        // TODO
        return new VoteInfo(id, "TEST" + id,
                "[\"icon\",\"fd6746802ea93b5ea345515a91c1a3f1\",\"icon\",\"d131400f9debda3e071b591e326503c9\",\"icon\",\"0b7d24d850104d14cb21e2049e7a1c52\",\"icon\",\"be4db0e91d0fe56d0394b5b4615e33ea\",\"icon\",\"d1fda401566fd58238447599cbac6ad2\",\"icon\",\"4fc86e9a8a4cccda0c047ea58b6a19af\"]",
                true, TimeUtils.strToDateLong("2019-09-07 11:00:00"),
                new String[] {"CCCCCCCCCCCCCCCCCCCCCCCCCC1", "CCCCCCCCCCCCCCCCCCCCCCCCCCC2", "CCCCCCCCCCCCCCCCCCCCCCCCCCC3", "CCCCCCCCCCCCCCCCCCCCCCCCCCC4", "CCCCCCCCCCCCCCCCCCCCCCCCCCC4", "CCCCCCCCCCCCCCCCCCCCCCCCCCC4", "CCCCCCCCCCCCCCCCCCCCCCCCCCC4", "CCCCCCCCCCCCCCCCCCCCCCCCCCC4", "CCCCCCCCCCCCCCCCCCCCCCCCCCC4", "CCCCCCCCCCCCCCCCCCCCCCCCCCC4"},
                new Date(), new Date(), 1, 1
        );
    }

    public VoteListQueryInfo getVoteList(int groupId, int page) {
        // TODO
        System.out.println("Loaded announce list page " + page);
        return new VoteListQueryInfo(page, 3,
                new VoteListInfo(1, 1, "PAGE" + page, TimeUtils.strToDateLong("2019-09-07 11:00:00"), new Date(), new Date(), 1, 2),
                new VoteListInfo(2, 1, "PAGE" + page, TimeUtils.strToDateLong("2019-09-07 11:00:00"), new Date(), new Date(), 1, 1),
                new VoteListInfo(3, 1, "PAGE" + page, TimeUtils.strToDateLong("2019-09-07 11:00:00"), new Date(), new Date(), 1, 1),
                new VoteListInfo(4, 1, "PAGE" + page, TimeUtils.strToDateLong("2019-09-07 11:00:00"), new Date(), new Date(), 1, 1),
                new VoteListInfo(5, 1, "PAGE" + page, TimeUtils.strToDateLong("2019-09-07 11:00:00"), new Date(), new Date(), 1, 1),
                new VoteListInfo(6, 1, "PAGE" + page, TimeUtils.strToDateLong("2019-09-07 11:00:00"), new Date(), new Date(), 1, 1),
                new VoteListInfo(7, 1, "PAGE" + page, TimeUtils.strToDateLong("2019-09-07 11:00:00"), new Date(), new Date(), 1, 1)
        );
    }

    public VoteStatisticInfo getVoteStatisticInfo(int id) {
        // TODO
        int[][] temp = new int[4][];
        temp[0] = new int[] {1, 2};
        temp[1] = new int[] {0, 1};
        temp[2] = new int[] {0, 3};
        temp[3] = new int[] {1, 2, 3};
        return new VoteStatisticInfo(new int[] {1, 2, 3, 4}, temp);
    }

    public String makeVote(MakeVoteInfo info) {
        // TODO
        return "";
    }

    public String removeVote(int id) {
        // TODO
        return "";
    }

    public int[] getSelfVoteInfo(int voteId) {
        return new int[] {1, 2, 3};
    }

}
