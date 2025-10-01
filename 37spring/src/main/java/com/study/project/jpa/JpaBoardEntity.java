package com.study.project.jpa;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="board")
public class JpaBoardEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_num")
    private Integer boardNum;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "board_subject")
    private String boardSubject;

    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "reg_date")
    private Date regDate;

    @Column(name = "upt_date")
    private Date uptDate;

    @Column(name = "view_cnt")
    private Integer viewCnt;

    // === getter / setter ===
    public Integer getBoardNum() { return boardNum; }
    public void setBoardNum(Integer boardNum) { this.boardNum = boardNum; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getBoardSubject() { return boardSubject; }
    public void setBoardSubject(String boardSubject) { this.boardSubject = boardSubject; }

    public String getBoardContent() { return boardContent; }
    public void setBoardContent(String boardContent) { this.boardContent = boardContent; }

    public Date getRegDate() { return regDate; }
    public void setRegDate(Date regDate) { this.regDate = regDate; }

    public Date getUptDate() { return uptDate; }
    public void setUptDate(Date uptDate) { this.uptDate = uptDate; }

    public Integer getViewCnt() { return viewCnt; }
    public void setViewCnt(Integer viewCnt) { this.viewCnt = viewCnt; }
}
