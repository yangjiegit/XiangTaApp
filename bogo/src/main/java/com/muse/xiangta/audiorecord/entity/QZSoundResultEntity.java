package com.muse.xiangta.audiorecord.entity;

/**
 * Created by Fridge on 17/5/23.
 */

public class QZSoundResultEntity {
    public String audioFileName;
    public long pageId;

    public QZSoundResultEntity(String audioFileName, long pageId) {
        this.audioFileName = audioFileName;
        this.pageId = pageId;
    }
}
