package top.chorg.kernel.foundation;

import static top.chorg.kernel.Variable.self;

public class MasterNetwork extends Network {
    public MasterNetwork() {
        super("localhost", 8888);
    }

    @Override
    public void disconnect() {
        super.disconnect();
        self = null;
    }
}
