package top.chorg.kernel.server;

import top.chorg.kernel.foundation.Message;

public class RequestDispatcher {

    /*
     * 本方法用于分发来自客户端的数据查询请求
     */
    public Message dispatch(Message msg) {
        switch(msg.getOpsId()) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            default:
                // 无效来信
        }
        return null;
    }

}
