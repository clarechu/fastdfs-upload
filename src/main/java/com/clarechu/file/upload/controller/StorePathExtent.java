package com.clarechu.file.upload.controller;

import com.luhuiguo.fastdfs.domain.StorePath;


public class StorePathExtent extends StorePath {
    private String path;

    private StorePath storePath;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    public StorePath getStorePath() {
        return storePath;
    }

    public void setStorePath(StorePath storePath) {
        this.storePath = storePath;
    }

    public StorePathExtent() {
    }

    public StorePathExtent(String path) {
        this.path = path;
    }

    public StorePathExtent(String group, String path, String path1) {
        super(group, path);
        this.path = path1;
    }
}
