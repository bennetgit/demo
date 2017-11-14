package spring.demo.persistence.primary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.Instant;
import java.util.Date;

/**
 * Created by feng on 17/11/14.
 */
@Entity
@Table(name = "login_audit")
@SequenceGenerator(name = "seq_login_audit", sequenceName = "seq_login_audit")
public class LoginAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_login_audit")
    private Long id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String userAgent;

    @Column
    private String ipAddress;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime = Date.from(Instant.now());

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public static final LoginAudit of(User loginUser, String userAgent, String ipAddress, Date loginTime) {

        LoginAudit loginAudit = new LoginAudit();
        loginAudit.setUser(loginUser);
        loginAudit.setUserAgent(userAgent);
        loginAudit.setIpAddress(ipAddress);
        loginAudit.setLoginTime(loginTime);
        return loginAudit;
    }
}
