package com.usthe.sureness.sample.tom.sureness.subject;

import com.usthe.sureness.subject.Subject;
import com.usthe.sureness.subject.support.JwtSubject;

import java.util.List;

/**
 * custom define token subject
 * @author tomsun28
 * @date 2020-12-03 22:08
 */
public class CustomTokenSubject implements Subject {

    private static final long serialVersionUID = 1L;

    /** 用户的标识 **/
    private String appId;

    /** token : admin--issueTime--refreshPeriodTime--uuid **/
    private String token;

    /** 访问用户的IP **/
    private String remoteHost;

    /** 访问用户的设备信息 **/
    private String userAgent;

    /** 所拥有的角色 在解析完jwt之后把用户角色放到这里 **/
    private List<String> ownRoles;

    /** 所访问资源地址 **/
    private String targetUri;

    /** 所访问资源他支持的角色 **/
    private List<String> supportRoles;

    private CustomTokenSubject(Builder builder) {
        this.appId = builder.appId;
        this.token = builder.token;
        this.remoteHost = builder.remoteHost;
        this.userAgent = builder.userAgent;
        this.ownRoles = builder.ownRoles;
        this.supportRoles = builder.supportRoles;
        this.targetUri = builder.targetUri;
    }

    @Override
    public Object getPrincipal() {
        return this.appId;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getOwnRoles() {
        return this.ownRoles;
    }

    @Override
    public Object getTargetResource() {
        return this.targetUri;
    }

    @Override
    public Object getSupportRoles() {
        return this.supportRoles;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setSupportRoles(Object var1) {
        this.supportRoles = (List<String>) var1;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public static JwtSubject.Builder builder(String jwt) {
        return new JwtSubject.Builder(jwt);
    }

    public static JwtSubject.Builder builder(Subject auToken) {
        return new JwtSubject.Builder(auToken);
    }

    public static class Builder {

        private String appId;
        private String token;
        private String remoteHost;
        private String userAgent;
        private List<String> ownRoles;
        private String targetUri;
        private List<String> supportRoles;

        public Builder(String token) {
            this.token = token;
        }

        @SuppressWarnings("unchecked")
        public Builder(Subject auToken) {
            this.appId = String.valueOf(auToken.getPrincipal());
            this.token = String.valueOf(auToken.getCredentials());
            this.ownRoles = (List<String>) auToken.getOwnRoles();
            this.targetUri = String.valueOf(auToken.getTargetResource());
            this.supportRoles = (List<String>) auToken.getSupportRoles();
        }

        public Builder setPrincipal(String appId) {
            this.appId = appId;
            return this;
        }

        public Builder setCredentials(String token) {
            this.token = token;
            return this;
        }

        public Builder setTargetResource(String targetUri) {
            this.targetUri = targetUri;
            return this;
        }

        public Builder setOwnRoles(List<String> ownRoles) {
            this.ownRoles = ownRoles;
            return this;
        }

        public Builder setSupportRoles(List<String> supportRoles) {
            this.supportRoles = supportRoles;
            return this;
        }

        public Builder setRemoteHost(String remoteHost) {
            this.remoteHost = remoteHost;
            return this;
        }

        public Builder setUserAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public CustomTokenSubject build() {
            return new CustomTokenSubject(this);
        }

    }
}
