package top.chorg.kernel.network;

import top.chorg.kernel.foundation.Message;

public class NetDispatcher {

    /*
     * 本方法一般用于进行来自服务器的数据处理
     * post和send主动信息方法得到的回复不会经由此方法调度
     */
    public void dispatch(Message msg) {
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
    }

}
