package com.py.agw.config;

import com.py.agw.chains.*;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.impl.ChainBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description：责任连中对应的bean初始化
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/13
 */
@Configuration
public class CommonsChainConfig {

    @Bean
    public Command flowControlCommand() {
        return new FlowControlCommand();
    }

    @Bean
    public Command blackIPCheckCommand() {
        return new BlackIPCheckCommand();
    }

    @Bean
    public Command signatureCheckCommand() {
        return new SignatureCheckCommand();
    }

    @Bean
    public Command decodeCommand() {
        return new DecodeCommand();
    }

    @Bean
    public Command apiCheckCommand() {
        return new APICheckCommand();
    }

    @Bean
    public Command paramCheckCommand() {
        return new ParamCheckCommand();
    }

    @Bean
    public Command invokeServiceCommand() {
        return new InvokeServiceCommand();
    }

    @Bean
    public ChainBase chains() {
        ChainBase chainBase = new ChainBase();

        chainBase.addCommand(flowControlCommand());
        chainBase.addCommand(blackIPCheckCommand());
        chainBase.addCommand(signatureCheckCommand());
        chainBase.addCommand(decodeCommand());
        chainBase.addCommand(apiCheckCommand());
        chainBase.addCommand(paramCheckCommand());
        chainBase.addCommand(invokeServiceCommand());

        return chainBase;
    }

}
