package com.example.fragmentsexample;

import java.util.Date;
import java.util.UUID;

public class TodoFragment {

    private UUID mTodoId;
    private String mTodoTitle;
    private String mTodoDetail;
    private Date mTodoDate;
    private boolean mTodoIsCompleted;

    public TodoFragment() {
    mTodoId = UUID.randomUUID();
    mTodoDate = new Date();
    }

    public UUID getmTodoId() {
        return mTodoId;
    }

    public void setmTodoId(UUID mTodoId) {
        this.mTodoId = mTodoId;
    }

    public String getmTodoTitle() {
        return mTodoTitle;
    }

    public void setmTodoTitle(String mTodoTitle) {
        this.mTodoTitle = mTodoTitle;
    }

    public String getmTodoDetail() {
        return mTodoDetail;
    }

    public void setmTodoDetail(String mTodoDetail) {
        this.mTodoDetail = mTodoDetail;
    }

    public Date getmTodoDate() {
        return mTodoDate;
    }

    public void setmTodoDate(Date mTodoDate) {
        this.mTodoDate = mTodoDate;
    }

    public boolean ismTodoIsCompleted() {
        return mTodoIsCompleted;
    }

    public void setmTodoIsCompleted(boolean mTodoIsCompleted) {
        this.mTodoIsCompleted = mTodoIsCompleted;
    }




}
