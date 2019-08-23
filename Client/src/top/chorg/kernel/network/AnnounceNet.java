package top.chorg.kernel.network;

import top.chorg.kernel.api.announce.AnnounceListQueryInfo;
import top.chorg.kernel.api.announce.AnnouncementInfo;
import top.chorg.kernel.api.announce.AnnouncementListInfo;

import java.util.Date;

public class AnnounceNet {

    public AnnouncementInfo getAnnounceInfo(int id) {
        // TODO
        return new AnnouncementInfo(id, "TEST",
                "[\"icon\",\"fd6746802ea93b5ea345515a91c1a3f1\",\"icon\",\"d131400f9debda3e071b591e326503c9\",\"icon\",\"0b7d24d850104d14cb21e2049e7a1c52\",\"icon\",\"be4db0e91d0fe56d0394b5b4615e33ea\",\"icon\",\"d1fda401566fd58238447599cbac6ad2\",\"icon\",\"4fc86e9a8a4cccda0c047ea58b6a19af\"]",
                new Date(), new Date(), 1, 1
        );
    }

    public AnnounceListQueryInfo getAnnounceList(int classId, int page) {
        // TODO
        System.out.println("Loaded announce list page " + page);
        return new AnnounceListQueryInfo(page, 3,
                new AnnouncementListInfo(1, 1, "PAGE" + page, new Date(), new Date(), 1, 2),
                new AnnouncementListInfo(2, 1, "PAGE" + page, new Date(), new Date(), 1, 1),
                new AnnouncementListInfo(3, 1, "PAGE" + page, new Date(), new Date(), 1, 1),
                new AnnouncementListInfo(4, 1, "PAGE" + page, new Date(), new Date(), 1, 1),
                new AnnouncementListInfo(5, 1, "PAGE" + page, new Date(), new Date(), 1, 1),
                new AnnouncementListInfo(6, 1, "PAGE" + page, new Date(), new Date(), 1, 1),
                new AnnouncementListInfo(7, 1, "PAGE" + page, new Date(), new Date(), 1, 1),
                new AnnouncementListInfo(8, 1, "PAGE" + page, new Date(), new Date(), 1, 1),
                new AnnouncementListInfo(9, 1, "PAGE" + page, new Date(), new Date(), 1, 1),
                new AnnouncementListInfo(10, 1, "PAGE" + page, new Date(), new Date(), 1, 1)
        );
    }

    public String removeAnnounce(int id) {
        // TODO
        return "";
    }

}
