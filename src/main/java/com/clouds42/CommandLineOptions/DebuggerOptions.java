package com.clouds42.CommandLineOptions;

import com._1c.g5.v8.dt.debug.model.base.data.DebugTargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Option;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DebuggerOptions {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Option(names = {"-p", "--password"}, description = "Dbgs password", interactive = true)
    private String password;

    @Option(names = {"-p:env", "--password:env"}, description = "Password environment variable name", defaultValue = "")
    private String passwordEnv;

    @Option(names = {"-n", "--areanames"}, description = "Debug area names (not for general use!)", arity = "0..*")
    private List<String> debugAreaNames;

    @Option(names = {"-a", "--autoconnectTargets"}, description = "Autoconnect debug targets (not for general use!): ${COMPLETION-CANDIDATES}", arity = "0..*")
    private List<DebugTargetType> autoconnectTargets;

    @Option(names = {"-t", "--timeout"}, description = "Ping timeout. Default - ${DEFAULT-VALUE}", defaultValue = "1000")
    private Integer pingTimeout;

    public String getPassword() {
        if (password != null) {
            if (password.trim().isEmpty()) {
                if (!passwordEnv.isEmpty()) {
                    password = System.getenv(passwordEnv);
                    passwordEnv = "";
                }
            }
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getDebugAreaNames() {
        if (debugAreaNames == null) {
            return new ArrayList<>();
        } else {
            return debugAreaNames;
        }
    }

    public void setDebugAreaNames(List<String> debugAreaNames) {
        this.debugAreaNames = debugAreaNames;
    }

    public Integer getPingTimeout() {
        return pingTimeout;
    }

    public void setPingTimeout(Integer pingTimeout) {
        this.pingTimeout = pingTimeout;
    }

    public void setAutoconnectTargets(List<DebugTargetType> autoconnectTargets) { this.autoconnectTargets = autoconnectTargets; }

    public List<DebugTargetType> getAutoconnectTargets() {
        if (autoconnectTargets == null || autoconnectTargets.isEmpty()) {
            autoconnectTargets = new LinkedList<DebugTargetType>();
            autoconnectTargets.addAll(DebugTargetType.VALUES);
            autoconnectTargets.remove(DebugTargetType.UNKNOWN);
        }
        return autoconnectTargets;
    }

}
